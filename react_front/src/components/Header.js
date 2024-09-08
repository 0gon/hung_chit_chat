import React from 'react';
import { useNavigate } from 'react-router-dom';

// HeaderButton 컴포넌트
export const HeaderButton = ({ children, onClick }) => {
  return (
    <button
      style={{
        margin: '0 5px',
        padding: '5px 10px',
        cursor: 'pointer',
        borderRadius: '4px',
      }}
      onClick={onClick}
    >
      {children}
    </button>
  );
};

// Logo 컴포넌트
export const Logo = () => {
  return (
    <div style={{ fontSize: '1.5em', fontWeight: 'bold' }}>
      hcc
    </div>
  );
};

// BackButton 컴포넌트
export const BackButton = () => {
  const navigate = useNavigate(); // useNavigate 훅을 사용하여 navigate 함수 얻기

  return (
    <button
      onClick={() => navigate(-1)} // 버튼 클릭 시 이전 페이지로 이동
      style={{
        padding: '5px 10px',
        cursor: 'pointer',
        borderRadius: '4px',
        background: 'transparent',
        border: 'none',
        fontSize: '16px',
      }}
    >
      {/* &#8592; */}
      ◀️
    </button>
  );
};

// Header 컴포넌트
const Header = ({ children }) => {
  return (
    <header style={{
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'center',
      padding: '10px',
      borderBottom: '1px solid #ddd',
      background: '#f8f9fa',
    }}>
      {children}
    </header>
  );
};

export default Header;
