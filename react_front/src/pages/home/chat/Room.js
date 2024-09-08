import React, { useEffect } from 'react';
import MessageInput from 'components/home/MessageInput';
import BackHeader from 'components/home/BackHeader';
import HomeLayout from 'layouts/HomeLayout';
import { useWebSocket } from 'contexts/WebSocketContext';
import { getUrlParam } from 'common/apiUtil';

const Room = () => {


  const ws = useWebSocket();

  const createMsg = (type, message) => {
    let msg = {
      type: type,
      memberId: null,
      text: message,
      roomId: getUrlParam('roomId'),
      members: null,
    }
    return JSON.stringify(msg);
  }

  const handleSendMessage = (message) => {
    ws.current.send(createMsg('message', message));
  };


  return (
    <HomeLayout
      header={<BackHeader />}
      footer={<MessageInput onSend={handleSendMessage} />}
    >
    </HomeLayout>
  );
};

export default Room;
