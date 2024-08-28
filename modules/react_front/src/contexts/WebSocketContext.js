import React, { createContext, useContext, useEffect, useRef, useState } from 'react';

const WebSocketContext = createContext(null);

export const WebSocketProvider = ({ children }) => {
  const ws = useRef(null); // 소켓을 저장할 인스턴스 변수

  useEffect(() => {

    console.log('랜더링..')
    // 웹소켓 연결 함수
    const connectWebSocket = () => {
      ws.current = new WebSocket('ws://localhost:8081/ws_chating');

      // 소켓이 열리면 동작
      ws.current.onopen = function (e) {
        console.log('웹소켓 연결 성공');
      };

      // 서버로부터 데이터 수신 (메세지를 전달 받음)
      ws.current.onmessage = function (e) {
        // 수신한 메세지 처리
        var msgJson = e.data;
        var msg = JSON.parse(msgJson);
        console.log('msg', msg);
      };

      // 연결이 끊어지면 자동으로 재연결 시도
      ws.current.onclose = function () {
        console.log('웹소켓 연결 종료, 재연결 시도 중...');
        setTimeout(() => {
          connectWebSocket(); // 재연결 시도
        }, 3000); // 3초 후 재연결 시도
      };

      // 연결 에러 처리
      ws.current.onerror = function (error) {
        console.error('웹소켓 에러:', error);
        ws.current.close(); // 에러 발생 시 연결 종료
      };
    };

    // 초기 웹소켓 연결
    connectWebSocket();

    // 컴포넌트 언마운트 시 웹소켓 연결 해제
    return () => {
      if (ws.current) {
        ws.current.close();
      }
    };
  }, []);


  return (
    <WebSocketContext.Provider value={ws}>
      {children}
    </WebSocketContext.Provider>
  );
};

export const useWebSocket = () => {
  return useContext(WebSocketContext);
};
