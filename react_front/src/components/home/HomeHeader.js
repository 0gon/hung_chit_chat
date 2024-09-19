import Header, { Logo } from 'components/Header';
import React from 'react';


// Header 컴포넌트
const HomeHeader = ({ children }) => {

  return (
    <Header>
      <Logo />
      {children}
    </Header>
  );
};

export default HomeHeader;
