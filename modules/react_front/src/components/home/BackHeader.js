import Header, { BackButton } from 'components/Header';
import React from 'react';


// Header 컴포넌트
const BackHeader = ({ children }) => {

  return (
    <Header>
      <BackButton />
      {children}
    </Header>
  );
};

export default BackHeader;
