package com.kafka.kafkachat.chat.service;

import com.kafka.kafkachat.chat.dto.*;
import com.kafka.kafkachat.chat.entity.ChatMessage;
import com.kafka.kafkachat.chat.entity.ChatRoom;
import com.kafka.kafkachat.chat.entity.UserChatRoom;
import com.kafka.kafkachat.chat.repository.ChatRepository;
import com.kafka.kafkachat.chat.repository.ChatRoomRepository;
import com.kafka.kafkachat.member.dto.MemberDto;
import com.kafka.kafkachat.member.entity.Member;
import com.kafka.kafkachat.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    private final ChatRoomRepository chatRoomRepository;

    private final RedisTemplate<String, Object> redisTemplate;
    private final MemberRepository memberRepository;

    /** 메시지 보내기
     * @param chatMessageDto
     * chatMessageDto = senderId, senderName, message, chatRoomId
     * */
    @Transactional
    public void saveMessage(ChatMessageDto chatMessageDto) {

        Long roomId = chatMessageDto.getChatRoomId();
        Long senderId = chatMessageDto.getSenderId();

        ResponseResult result = checkCacheOrDb(roomId, senderId);

        ChatMessage chatMessage = ChatMessage.builder()
                .message(chatMessageDto.getMessage())
                .sender(result.getMember())
                .chatRoom(result.getChatRoom())
                .timestamp(chatMessageDto.getTimestamp())
                .build();

        chatRepository.save(chatMessage);
    }

    /** 방 생성 로직
     * @param chatCreatedDto
     * chatCreatedDto = senderId, recipientId, roomName
     * */
    @Transactional
    public ResponseChatCreatedDto createRoom(ChatCreatedDto chatCreatedDto){

        Member sender = memberRepository.findById(chatCreatedDto.getSenderId()).orElseThrow(() -> new IllegalArgumentException("SENDER NOT FOUND"));
        Member recipient = memberRepository.findById(chatCreatedDto.getRecipientId()).orElseThrow(() -> new IllegalArgumentException("RECIPIENT NOT FOUND"));

        String roomName = chatCreatedDto.getRoomName();

        if(roomName == null || roomName.isEmpty()){
            roomName = sender.getUsername() + "님의 방";
        }
        ChatRoom chatRoom = new ChatRoom(roomName);

        UserChatRoom userChatRoom = new UserChatRoom(sender, chatRoom);
        UserChatRoom userChatRoom2 = new UserChatRoom(recipient, chatRoom);

        chatRoom.addUserChatRoom(userChatRoom);
        chatRoom.addUserChatRoom(userChatRoom2);

        ChatRoom chatroom = chatRoomRepository.save(chatRoom);
        return ResponseChatCreatedDto.builder().roomId(chatroom.getId()).build();
    }

    /**
     * Redis 에서 채팅방 ID로 체크 후 없으면 객체 조회 후 레디스에 저장, 있으면 객체 반환
     * member 객체도 똑같이 저장
     *
     * @param chatRoomId 채팅 방 ID
     */
    private ResponseResult checkCacheOrDb(Long chatRoomId, Long memberId) {
        String roomKey = "chatRoom::" + chatRoomId;
        String memberKey = "member:" + memberId;
        // 레디스에서 탐색
        ChatRoomDto chatRoomDto = (ChatRoomDto) redisTemplate.opsForValue().get(roomKey);
        MemberDto memberDto = (MemberDto) redisTemplate.opsForValue().get(memberKey);

        // ChatRoom 이 레디스에 없을때
        if (chatRoomDto == null) {
            ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new IllegalArgumentException("EMPTY ROOM"));

            chatRoomDto = ChatRoomDto.builder()
                    .id(chatRoom.getId())
                    .name(chatRoom.getName())
                    .build();

            // 레디스에 저장
            insertRedis(roomKey, chatRoomDto);
        }

        // Member 가 레디스에 없을때
        if (memberDto == null) {
            Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("User Not Found"));

            memberDto = MemberDto.builder()
                    .id(member.getId())
                    .name(member.getUsername())
                    .build();

            // 레디스에 저장
            insertRedis(memberKey, memberDto);

        }
        ChatRoom chatRoom = ChatConverter.ChatRoomDtotoEntity(chatRoomDto);
        Member member = ChatConverter.MemberDtotoEntity(memberDto);
        return new ResponseResult(chatRoom, member);

    }

    /** 채팅방 메시지 전체를 불러오는 로직
     * @param roomId
     * */
    public List<ChatMessageResponseDto> getMessagesByRoomId(Long roomId) {
        List<ChatMessage> findMessage = chatRepository.findChatMessageByChatRoomId(roomId);
        return findMessage.stream().map(ChatMessageResponseDto::new).collect(Collectors.toList());
    }

    /** 레디스에 Key, Object 저장
     * TTL은 1시간으로 설정
     * */
    private void insertRedis(String key, Object object){
        //TTL 설정 : Redis 에서 1시간동안 관리
        redisTemplate.opsForValue().set(key, object, 1, TimeUnit.HOURS);
    }

}
