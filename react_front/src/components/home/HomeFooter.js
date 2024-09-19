import React from 'react';
import { useNavigate } from "react-router-dom";
import Footer, { FooterButton } from 'components/Footer';

// Footer 컴포넌트
const HomeFooter = () => {

  const navigate = useNavigate();

  return (
    <Footer>
      <FooterButton onClick={() => navigate("/home/addFriends")}>
        친구추가
      </FooterButton>
      <FooterButton onClick={() => navigate("/home/chat")}>
        채팅
      </FooterButton>
      {/* 필요에 따라 FooterButton을 추가로 사용 */}
    </Footer>
  );
};

export default HomeFooter;
