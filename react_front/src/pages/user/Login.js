import React, { useState } from 'react';
import { Link, useNavigate } from "react-router-dom";
import UserLayout from "layouts/UserLayout";
import { getApiUrl } from 'common/apiUtil';
import { setCookie } from 'common/cookie';

const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [rememberMe, setRememberMe] = useState(false);
  const [isLoading, setIsLoading] = useState(false); // 요청 진행 상태를 관리하는 상태

  const navigate = useNavigate(); // 페이지 이동을 위한 훅
  const apiUrl = getApiUrl(); // API URL 가져오기

  // 로그인 핸들러 함수
  const handleLogin = async () => {
    setIsLoading(true); // 요청 시작 시 로딩 상태로 설정

    const apiPath = 'http://localhost:8000/members/auth/signIn';

    try {
      
      // 로그인 성공 시 필요한 작업 (예: 토큰 저장, 사용자 상태 업데이트 등)
      // todo: 서버 완료되면 수정하기
      const response = await fetch(apiPath, {
        method: 'POST',
        headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({
              email,
              password,
            }),
            credentials: 'same-origin'
      });

      console.log(response); 
      
      if (!response.ok) { 
        throw new Error('로그인 요청 실패');
      }

      const data = await response.json();
      console.log('로그인 성공:', data);
      console.log('로그인 성공:', data.accessToken);
      console.log('로그인 성공:', data.refreshToken);

      {
        setCookie('access token', data.accessToken);
        setCookie('refresh token', data.refreshToken);
      }

      alert("로그인 성공!");
      navigate('/asdf'); // 로그인 후 메인 페이지로 이동
      setEmail('완료');
      setPassword('완료');
    } catch (error) {
      console.error('로그인 오류:', error);
      alert("로그인 중 오류가 발생했습니다.");
      setPassword('');
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
          value={email}
          onChange={(e) => setEmail(e.target.value)}
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
          onClick={handleLogin}
          disabled={isLoading} // 요청 중에는 버튼 비활성화
          style={{ marginBottom: '10px', padding: '10px', width: '100%', borderRadius: '4px', background: '#007bff', color: 'white', border: 'User' }}
        >
          {isLoading ? '로그인 중...' : '로그인'}
        </button>
        <div style={{ marginBottom: '20px' }}>
          <label>
            <input
              type="checkbox"
              checked={rememberMe}
              onChange={() => setRememberMe(!rememberMe)}
              style={{ marginRight: '5px' }}
            />
            로그인 유지
          </label>
        </div>
        <Link to={'/signup'} style={{ fontSize: '0.8em', color: '#007bff' }}>
          회원가입
        </Link>
      </div>
    </UserLayout>
  );
};

export default LoginPage;
