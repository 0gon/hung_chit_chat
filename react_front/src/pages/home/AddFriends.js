import React, { useRef, useState } from 'react';
import HomeLayout from 'layouts/HomeLayout';
import { getApiUrl } from 'common/apiUtil';
import HomeHeader from 'components/home/HomeHeader';
import HomeFooter from 'components/home/HomeFooter';

const AddFriends = () => {
  const [username, setUsername] = useState('');
  const [isLoading, setIsLoading] = useState(false); // 요청 진행 상태를 관리하는 상태
  const [friendList, setFriendList] = useState([]); // 조회된 친구 리스트를 저장하는 상태
  const buttonRef = useRef(null); // 버튼을 참조하기 위한 useRef 생성

  const apiUrl = getApiUrl(); // API URL 가져오기

  const handleKeyDown = (event) => {
    if (event.key === 'Enter') {
      buttonRef.current.click(); // Enter 키를 눌렀을 때 버튼 클릭
    }
  };

  const handleFindUserByUsername = async () => {
    setIsLoading(true); // 요청 시작 시 로딩 상태로 설정
    const apiPath = '/api/v1/findUserByUsername';

    try {
      // const response = await fetch(`${apiUrl}${apiPath}`, {
      //   method: 'POST',
      //   headers: {
      //     'Content-Type': 'application/json',
      //   },
      //   body: JSON.stringify({
      //     username
      //   }),
      // });

      // if (!response.ok) {
      //   throw new Error('조회 요청 실패');
      // }

      // const data = await response.json();

      const data = ['asdf', 'qwer']; // 더미 데이터
      console.log('조회 성공:', data);

      // 조회된 데이터를 상태에 저장
      setFriendList(data);

    } catch (error) {
      console.error('조회 오류:', error);
    } finally {
      setIsLoading(false); // 요청 완료 후 로딩 상태 해제
    }
  };

  const handleAddFriend = async (friendUsername) => {
    try {
      // 친구 추가 API 호출
      const apiPath = '/api/v1/addFriend';

      // const response = await fetch(`${apiUrl}${apiPath}`, {
      //   method: 'POST',
      //   headers: {
      //     'Content-Type': 'application/json',
      //   },
      //   body: JSON.stringify({
      //     friendUsername,
      //   }),
      // });

      // if (!response.ok) {
      //   throw new Error('친구 추가 요청 실패');
      // }

      // const data = await response.json();
      alert(`${friendUsername} 추가됨`);
    } catch (error) {
      console.error('친구 추가 오류:', error);
    }
  };

  return (
    <HomeLayout header={<HomeHeader />} footer={<HomeFooter />}>

      <div style={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
        {/* 입력 필드와 버튼을 감싸는 div */}
        <div style={{ display: 'flex', marginBottom: '20px' }}>
          <input
            type="text"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            placeholder="Enter friend's ID"
            onKeyDown={handleKeyDown} // 입력 필드에 onKeyDown 핸들러 추가
            style={{
              flex: '1',
              padding: '10px',
              fontSize: '16px',
              borderRadius: '4px',
              border: '1px solid #ccc',
              boxSizing: 'border-box',
            }}
          />
          <button
            ref={buttonRef} // 버튼을 ref로 연결
            onClick={handleFindUserByUsername}
            disabled={isLoading} // 요청 중에는 버튼 비활성화
            style={{
              marginLeft: '10px',
              padding: '10px 20px',
              borderRadius: '4px',
              border: '1px solid #ccc',
              background: '#007bff',
              color: '#fff',
              cursor: 'pointer',
            }}
          >
            조회
          </button>
        </div>

        {/* 스크롤 가능한 리스트 영역 */}
        <div style={{
          flex: '1',
          overflowY: 'auto',
          border: '1px solid #ccc',
          borderRadius: '4px',
          padding: '10px',
          background: '#f9f9f9',
        }}>
          <ul style={{ listStyleType: 'none', padding: '0' }}>
            {friendList.length > 0 ? (
              friendList.map((friend, index) => (
                <li
                  key={index}
                  style={{
                    padding: '10px',
                    borderBottom: '1px solid #ddd',
                    display: 'flex',
                    justifyContent: 'space-between',
                    alignItems: 'center',
                  }}
                >
                  <span>{friend}</span>
                  <button
                    onClick={() => handleAddFriend(friend)}
                    style={{
                      padding: '5px 10px',
                      borderRadius: '4px',
                      border: '1px solid #ccc',
                      background: '#28a745',
                      color: '#fff',
                      cursor: 'pointer',
                    }}
                  >
                    친구 추가
                  </button>
                </li>
              ))
            ) : (
              <li style={{ textAlign: 'center', color: '#aaa' }}>No friends found.</li>
            )}
          </ul>
        </div>
      </div>
    </HomeLayout>
  );
}

export default AddFriends;
