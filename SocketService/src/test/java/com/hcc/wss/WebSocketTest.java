package com.hcc.wss;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcc.socket.Message;
import com.hcc.socket.webSocket.config.WebSocketConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.web.socket.*;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.IntStream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketTest {

    @LocalServerPort
    int port;

    @Autowired
    WebSocketConfig webSocketConfig;


    @Test
    public void 웹소켓_테스트() throws Exception {
        // 준비
        String roomId = "1";
        Client client1 = new Client("1");
        Client client2 = new Client("2");
        List<String> members = Arrays.asList(client1.memberId, client2.memberId);

        // 행동
        client1.sendMessage("message", "hi, i am c1", roomId, members);
        client1.sendMessage("message", "hi too. i am c2", roomId, members);
        Thread.sleep(1000);

        // 결과
        Assertions.assertTrue(client1.session.isOpen());
        Assertions.assertTrue(client2.session.isOpen());
        Assertions.assertEquals(client1.savedMsgs.size(), 2);
        Assertions.assertEquals(client2.savedMsgs.size(), 2);
        boolean b = IntStream.range(0, client1.savedMsgs.size())
                .allMatch(i -> Objects.equals(client1.savedMsgs.get(i).getPayload(), client2.savedMsgs.get(i).getPayload()));
        Assertions.assertTrue(b);
    }


    class Client {

        String memberId;
        List<TextMessage> savedMsgs = new ArrayList<>();
        WebSocketSession session;

        public Client(String memberId) throws ExecutionException, InterruptedException, TimeoutException {
            this.memberId = memberId;
            URI webSocketUri = URI.create("ws://localhost:" + port + webSocketConfig.PATH);

            TextWebSocketHandler clientWebSocketHandler = new TextWebSocketHandler() {
                @Override
                public void afterConnectionEstablished(WebSocketSession session) throws Exception {
                }

                @Override
                public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
                    savedMsgs.add(message);
                }

                @Override
                public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
                }
            };

            WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
            headers.add(HttpHeaders.COOKIE, "memberId=" + memberId);

            // 클라이언트 생성. !! 클라이언트에 들어가는 핸들러는 클라이언트가 메세지를 받았을 때 처리하는 방식을 정의하는 것이지,
            // config에 설정한 핸들러와는 전혀 별개이다.
            StandardWebSocketClient client = new StandardWebSocketClient();
            CompletableFuture<WebSocketSession> future = client.execute(clientWebSocketHandler, headers, webSocketUri);
            session = future.get(5, TimeUnit.SECONDS);
        }

        public void sendMessage(String type, String text, String roomId, List<String> members) throws IOException {
            Message msg = Message.builder().type(type).memberId(memberId).text(text).roomId(roomId).members(members).build();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonMsg = objectMapper.writeValueAsString(msg);
            TextMessage textMessage = new TextMessage(jsonMsg);
            session.sendMessage(textMessage);
        }
    }
}

