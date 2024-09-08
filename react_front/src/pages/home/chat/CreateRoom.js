import React, { useState, useEffect } from 'react';
import HomeLayout from 'layouts/HomeLayout';
import BackHeader from 'components/home/BackHeader';
import { getApiUrl } from 'common/apiUtil';

const CreateRoom = () => {
  const [username, setUsername] = useState('');
  const [userList, setUserList] = useState([]); // 초기 더미 데이터 저장
  const [filteredUsers, setFilteredUsers] = useState([]); // 필터링된 사용자 목록
  const [selectedUsers, setSelectedUsers] = useState(new Set()); // 선택된 사용자 목록을 Set으로 저장

  // 더미 데이터
  const dummyData = [
    'alice', 'bob', 'charlie', 'dave', 'eve',
    'frank', 'grace', 'heidi', 'ivan', 'judy',
    'karl', 'lisa', 'mike', 'nina', 'olivia'
  ];

  useEffect(() => {
    // 페이지 로드 시 친구 목록을 가져오는 함수
    const fetchFriendList = async () => {
      // const apiUrl = getApiUrl(); // API URL 가져오기
      const apiPath = '/api/v1/getFriendList'; // API 경로 설정

      try {
        // const response = await fetch(`${apiUrl}${apiPath}`, {
        //   method: 'GET',
        //   headers: {
        //     'Content-Type': 'application/json',
        //   },
        // });

        // if (!response.ok) {
        //   throw new Error('친구 목록 가져오기 실패');
        // }

        // const data = await response.json();
        // setUserList(data);

        // 더미 데이터를 사용하여 리스트를 초기화
        setUserList(dummyData);
      } catch (error) {
        console.error('친구 목록 가져오기 오류:', error);
      }
    };

    fetchFriendList(); // 데이터 가져오기
  }, []); // 컴포넌트 마운트 시 실행

  useEffect(() => {
    // userList가 업데이트된 후 filteredUsers를 설정
    setFilteredUsers(userList);
  }, [userList]); // userList가 변경될 때마다 실행

  const handleChange = (e) => {
    const inputValue = e.target.value;
    setUsername(inputValue);

    // 사용자 이름에 따라 필터링
    const filtered = userList.filter(user =>
      user.toLowerCase().includes(inputValue.toLowerCase())
    );
    setFilteredUsers(filtered);
  };

  const handleCheckboxChange = (user) => {
    setSelectedUsers(prevSelected => {
      const newSelected = new Set(prevSelected);
      if (newSelected.has(user)) {
        // 이미 선택된 사용자라면 목록에서 제거
        newSelected.delete(user);
      } else {
        // 새로 선택된 사용자 추가
        newSelected.add(user);
      }
      return newSelected;
    });
  };

  const handleSubmit = async () => {
    try {
      // 선택된 사용자들을 서버에 보내는 API 호출
      const apiUrl = getApiUrl();
      const apiPath = '/api/v1/createRoom';
      // todo:

      // const response = await fetch(`${apiUrl}${apiPath}`, {
      //   method: 'POST',
      //   headers: {
      //     'Content-Type': 'application/json',
      //   },
      //   body: JSON.stringify({
      //     selectedUsers: Array.from(selectedUsers)
      //   }),
      // });

      // if (!response.ok) {
      //   throw new Error('방 생성 요청 실패');
      // }

      // const data = await response.json();
      console.log('방 생성 요청 성공:', Array.from(selectedUsers));
      alert('방 생성 요청이 완료되었습니다!');
    } catch (error) {
      console.error('방 생성 오류:', error);
      alert('방 생성 중 오류가 발생했습니다.');
    }
  };

  return (
    <HomeLayout header={<BackHeader />}>
      <div style={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
        {/* 입력 필드 */}
        <div style={{ marginBottom: '20px' }}>
          <input
            type="text"
            value={username}
            onChange={handleChange}
            placeholder="Search user..."
            style={{
              padding: '10px',
              fontSize: '16px',
              borderRadius: '4px',
              border: '1px solid #ccc',
              boxSizing: 'border-box',
              width: '100%',
            }}
          />
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
            {filteredUsers.length > 0 ? (
              filteredUsers.map((user, index) => (
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
                  <span>{user}</span>
                  <input
                    type="checkbox"
                    checked={selectedUsers.has(user)}
                    onChange={() => handleCheckboxChange(user)}
                    style={{
                      marginLeft: '10px',
                      cursor: 'pointer',
                    }}
                  />
                </li>
              ))
            ) : (
              <li style={{ textAlign: 'center', color: '#aaa' }}>No users found.</li>
            )}
          </ul>
        </div>

        {/* 방 생성 버튼 */}
        <div style={{ marginTop: '20px' }}>
          <button
            onClick={handleSubmit}
            style={{
              padding: '10px 20px',
              borderRadius: '4px',
              border: '1px solid #ccc',
              background: '#007bff',
              color: '#fff',
              cursor: 'pointer',
              width: '100%',
            }}
          >
            Create Room
          </button>
        </div>
      </div>
    </HomeLayout>
  );
};

export default CreateRoom;
