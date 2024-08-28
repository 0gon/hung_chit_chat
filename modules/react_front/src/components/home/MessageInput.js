// components/MessageInput.js
import Footer from 'components/Footer';
import React, { useState } from 'react';

const MessageInput = ({ onSend }) => {
  const [message, setMessage] = useState(''); // 메시지 입력값 상태

  const handleInputChange = (e) => {
    setMessage(e.target.value); // 입력값 상태 업데이트
  };

  const handleSend = () => {
    if (message.trim() === '') {
      return; // 빈 메시지일 경우 전송하지 않음
    }
    onSend(message); // 메시지 전송 로직 호출
    setMessage(''); // 입력값 초기화
  };

  const handleKeyDown = (e) => {
    if (e.key === 'Enter') {
      e.preventDefault(); // 기본 엔터 동작 방지 (예: 줄 바꿈)
      handleSend(); // 메시지 전송
    }
  };

  return (
    <Footer>
      <div style={{
        display: 'flex',
        alignItems: 'center',
        padding: '10px',
        borderTop: '1px solid #ddd',
        background: '#f9f9f9',
        width: '100%',
        boxSizing: 'border-box',
        position: 'absolute',
        bottom: '0',
        left: '0',
      }}>
        <input
          type="text"
          value={message}
          onChange={handleInputChange}
          onKeyDown={handleKeyDown}
          placeholder="메시지를 입력하세요"
          style={{
            flex: '1',
            padding: '10px',
            borderRadius: '4px',
            border: '1px solid #ccc',
            marginRight: '10px',
          }}
        />
        <button
          onClick={handleSend}
          style={{
            padding: '10px 20px',
            borderRadius: '4px',
            border: '1px solid #007bff',
            background: '#007bff',
            color: '#fff',
            cursor: 'pointer',
          }}
        >
          전송
        </button>
      </div>
    </Footer>
  );
};

export default MessageInput;
