package com.kafka.kafkachat.chat.service;

import com.kafka.kafkachat.chat.dto.ChatConverter;
import com.kafka.kafkachat.chat.dto.ChatMessageDto;
import com.kafka.kafkachat.chat.dto.ChatRoomDto;
import com.kafka.kafkachat.chat.dto.ResponseResult;
import com.kafka.kafkachat.chat.entity.ChatMessage;
import com.kafka.kafkachat.chat.entity.ChatRoom;
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
    private final AdminClient adminClient;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveMessage(ChatMessageDto chatMessageDto) {

        Long roomId = chatMessageDto.getChatRoomId();
        Long senderId = chatMessageDto.getSenderId();

        ResponseResult result = checkCacheOrDb(roomId, senderId);

        ChatMessage chatMessage = ChatMessage.builder()
                .message(chatMessageDto.getMessage())
                .sender(result.getMember())
                .chatRoom(result.getChatRoom())
                .build();

        chatRepository.save(chatMessage);
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

    public List<ChatMessageDto> getMessagesByRoomId(Long roomId) {
        List<ChatMessage> findMessage = chatRepository.findChatMessageByChatRoomId(roomId);
        return findMessage.stream().map(ChatMessageDto::new).collect(Collectors.toList());
    }

    private void insertRedis(String key, Object object){
        //TTL 설정 : Redis 에서 1시간동안 관리
        redisTemplate.opsForValue().set(key, object, 1, TimeUnit.HOURS);
    }

}
