import React, { useState } from 'react';
import { Link, useNavigate } from "react-router-dom";
import UserLayout from "layouts/UserLayout";
import { getApiUrl } from 'common/apiUtil';

const SignupPage = () => {
  const [id, setId] = useState('');
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [isLoading, setIsLoading] = useState(false); // 요청 진행 상태를 관리하는 상태

  const navigate = useNavigate(); // useNavigate 훅 사용

  const apiUrl = getApiUrl();

  // 회원가입 핸들러 함수
  const handleSignup = async () => {
    setIsLoading(true); // 요청 시작 시 로딩 상태로 설정

    const apiPath = '/api/v1/signup';

    try {
      // const response = await fetch(`${apiUrl}${apiPath}`, {
      //   method: 'POST',
      //   headers: {
      //     'Content-Type': 'application/json',
      //   },
      //   body: JSON.stringify({
      //     id,
      //     username,
      //     password,
      //   }),
      // });

      // if (!response.ok) {
      //   throw new Error('회원가입 요청 실패');
      // }

      // const data = await response.json();
      // console.log('회원가입 성공:', data);


      // todo: 서버 구현 시 주석 해제 


      alert("회원가입이 완료되었습니다.");
      navigate('/login');
    } catch (error) {
      console.error('회원가입 오류:', error);
      alert("회원가입 중 오류가 발생했습니다.");
    } finally {
      setIsLoading(false); // 요청 완료 후 로딩 상태 해제
    }
  };

  return (
    <UserLayout>
      <div style={{
        width: '100%',
        maxWidth: '300px',
        textAlign: 'center' // 중앙 정렬을 위한 스타일
      }}>
        <input
          type="text"
          placeholder="아이디"
          value={id}
          onChange={(e) => setId(e.target.value)}
          style={{ marginBottom: '10px', padding: '10px', width: '100%', borderRadius: '4px', boxSizing: 'border-box' }}
        />
        <input
          type="text"
          placeholder="이름"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          style={{ marginBottom: '10px', padding: '10px', width: '100%', borderRadius: '4px', boxSizing: 'border-box' }}
        />
        <input
          type="password"
          placeholder="비밀번호"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          style={{ marginBottom: '20px', padding: '10px', width: '100%', borderRadius: '4px', boxSizing: 'border-box' }}
        />
        <button
          onClick={handleSignup}
          disabled={isLoading} // 요청 중에는 버튼 비활성화
          style={{ marginBottom: '10px', padding: '10px', width: '100%', borderRadius: '4px', background: '#007bff', color: 'white', border: 'none' }}
        >
          {isLoading ? '회원가입 중...' : '회원가입'}
        </button>
        <Link to={'/login'} style={{ fontSize: '0.8em', color: '#007bff' }}>
          로그인 페이지로
        </Link>
      </div>
    </UserLayout>
  );
}

export default SignupPage;
