import { useState, useEffect } from "react";
import HomeLayout from "layouts/HomeLayout";
import { useNavigate } from "react-router-dom";
import { HeaderButton } from "components/Header";
import HomeHeader from "components/home/HomeHeader";
import HomeFooter from "components/home/HomeFooter";
import { getApiUrl } from "common/apiUtil";

const ChatPage = () => {
  const [roomList, setRoomList] = useState([]);

  useEffect(() => {
    const fetchRoomList = async () => {
      const apiUrl = getApiUrl();
      const apiPath = '/api/v1/getRoomList';

      try {
        // todo: 방리스트 호출 api
        const dummyData = [
          { roomId: 1, joinedUsers: ['alice', 'bob'] },
          { roomId: 2, joinedUsers: ['charlie', 'dave', 'eve'] },
          { roomId: 3, joinedUsers: ['frank', 'grace'] },
          { roomId: 4, joinedUsers: ['heidi', 'ivan', 'judy'] },
          { roomId: 5, joinedUsers: ['karl', 'lisa'] },
          { roomId: 6, joinedUsers: ['mike', 'nina', 'olivia'] },
          { roomId: 7, joinedUsers: ['alice', 'frank'] },
          { roomId: 8, joinedUsers: ['bob', 'grace', 'heidi'] },
          { roomId: 9, joinedUsers: ['dave', 'judy'] },
          { roomId: 10, joinedUsers: ['eve', 'karl', 'lisa'] },
          { roomId: 11, joinedUsers: ['mike', 'olivia'] },
          { roomId: 12, joinedUsers: ['nina', 'alice', 'bob'] },
          { roomId: 13, joinedUsers: ['charlie', 'dave'] },
          { roomId: 14, joinedUsers: ['frank', 'grace', 'heidi'] },
          { roomId: 15, joinedUsers: ['ivan', 'judy', 'karl'] }
        ];

        setRoomList(dummyData);
      } catch (error) {
        console.error('방 목록 가져오기 오류:', error);
      }
    };

    fetchRoomList();
  }, []); // 컴포넌트 마운트 시 실행

  const navigate = useNavigate();

  const handleRoomClick = (roomId) => {
    navigate(`/home/chat/room?roomId=${roomId}`); // roomId를 URL 파라미터로 포함
    console.log('roomId', roomId)
  };

  return (
    <HomeLayout
      header={<HomeHeader>
        <div>
          <HeaderButton onClick={() => navigate('/home/chat/createRoom')}>방생성</HeaderButton>
        </div>
      </HomeHeader>}
      footer={<HomeFooter />}
    >
      <div style={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
        <div style={{
          flex: '1',
          overflowY: 'auto',
          border: '1px solid #ccc',
          borderRadius: '4px',
          padding: '10px',
          background: '#f9f9f9',
        }}>
          <ul style={{ listStyleType: 'none', padding: '0' }}>
            {roomList.length > 0 ? (
              roomList.map((room) => (
                <li
                  key={room.roomId}
                  onClick={() => handleRoomClick(room.roomId)} // 클릭 시 navigate 호출
                  style={{
                    padding: '10px',
                    borderBottom: '1px solid #ddd',
                    display: 'flex',
                    justifyContent: 'space-between',
                    cursor: 'pointer', // 클릭 가능한 요소임을 명시
                  }}
                >
                  <span>방 ID: {room.roomId}</span>
                  <span>참여자: {room.joinedUsers.join(', ')}</span>
                </li>
              ))
            ) : (
              <li style={{ textAlign: 'center', color: '#aaa' }}>No rooms available.</li>
            )}
          </ul>
        </div>
      </div>
    </HomeLayout>
  );
};

export default ChatPage;
