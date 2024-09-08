import { Routes, Route, Navigate } from "react-router-dom";
import { lazy } from "react";
import { WebSocketProvider } from 'contexts/WebSocketContext';

const Chat = lazy(() => import("pages/home/chat/index"));
const Room = lazy(() => import("pages/home/chat/Room"));
const CreateRoom = lazy(() => import("pages/home/chat/CreateRoom"));
const AddFriends = lazy(() => import("pages/home/AddFriends"));

const HomeRoutes = () => (
  <WebSocketProvider>
    <Routes>
      <Route path="/chat" element={<Chat />} />
      <Route path="/chat/room" element={<Room />} />
      <Route path="/chat/createRoom" element={<CreateRoom />} />
      <Route path="/addFriends" element={<AddFriends />} />

      <Route path="/" element={<Navigate to="/home/chat" />} />
      <Route path="*" element={<Navigate to="/home/chat" />} />
    </Routes>
  </WebSocketProvider>
);

export default HomeRoutes;
