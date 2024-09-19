import { Routes, Route, Navigate } from "react-router-dom";
import { lazy, Suspense } from "react";
import PrivateRoute from "./common/PrivateRoute";
import './App.css';

const Loading = <div>Loading...</div>;

// user
const Login = lazy(() => import("pages/user/Login"));
const Signup = lazy(() => import("pages/user/Signup"));

// home
const HomeRoutes = lazy(() => import("router/HomeRoutes"));

function App() {
  return (
    <Suspense fallback={Loading}>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />

        <Route element={<PrivateRoute />}>
          <Route path="/home/*" element={<HomeRoutes />} />
        </Route>

        <Route path="/" element={<Navigate to="/home/chat" />} />
        <Route path="*" element={<Navigate to="/home/chat" />} />
      </Routes>
    </Suspense>
  );
}

export default App;
