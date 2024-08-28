## DB 설계

1. Member (멤버) 1 : N UserChatRoom (매핑테이블) 일대다 양방향 \
2. UserChatRoom(매핑텡블) N : 1 ChatRoom (채팅방) 다대일 양방향 \
3. chatRoom(채팅방) 1 : N chatMessage(채팅메시지) 일대다 단방향 \

3. 일대다 단방향인 이유 : chatRoom엔 여러 메시지를 가지고 있어야하지만 채팅메시지는 방 1개에 1개씩 나가기 떄문


Member.getUserChatRoom().getChatRoom().getChatMessage()


db
msg memberId asdf

조회
member chatroom id : 1 


member chatroom id : 1 , name : asdf 