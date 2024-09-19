import React from 'react';

const HomeLayout = ({ header, children, footer }) => {
  return (
    <div style={{
      display: 'flex',
      flexDirection: 'column',
      minHeight: '100vh', // 화면 전체 높이를 최소 높이로 설정
      background: 'gray',
    }}>
      <div
        id='mobile'
        style={{
          position: 'relative',
          width: '100%',
          background: 'white',
          display: 'flex',
          flexDirection: 'column',
          maxWidth: '400px',
          height: '700px',
          border: 'solid 1px',
          transform: 'translate(-50%, -50%)',
          top: '50%',
          left: '50%',
          position: 'absolute',
        }}>

        {header}

        <main style={{
          flex: '1',
          margin: '20px',
          overflowY: 'auto', // 내용이 넘칠 경우 스크롤 가능
        }}>
          {children}
        </main>

        {footer}

      </div>
    </div>
  );
};

export default HomeLayout;
