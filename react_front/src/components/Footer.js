import React from 'react';

// FooterButton 컴포넌트
export const FooterButton = ({ children, onClick }) => {
  return (
    <div style={{
      flex: '1',
      display: 'flex',
      justifyContent: 'center',
      alignItems: 'center',
      padding: '0 5px',
    }}>
      <button style={{
        padding: '10px',
        borderRadius: '4px',
      }} onClick={onClick}>
        {children}
      </button>
    </div>
  );
};

// Footer 컴포넌트
const Footer = ({ children }) => {

  return (
    <footer style={{
      display: 'flex',
      padding: '10px',
      borderTop: '1px solid #ddd',
      background: '#f8f9fa',
      bottom: '0',
      width: '100%',
      boxSizing: 'border-box',
    }}>
      {children}
    </footer>
  );
};

export default Footer;
