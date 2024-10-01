import { Route, Routes } from "react-router-dom";
import HomePage from "./pages/home/HomePage";
import FeedPage from "./pages/feed/FeedPage";

function App() {
  return (
    <Routes>
      <Route path="/" element={<HomePage />} />
      <Route path="/feed" element={<FeedPage />} />
    </Routes>
  );
}

export default App;
