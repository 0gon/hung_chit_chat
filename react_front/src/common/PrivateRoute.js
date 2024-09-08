import { Navigate, Outlet } from "react-router-dom";
import { getCookie } from "common/cookie";

const isLogin = !!getCookie('access token') || !!getCookie('refresh token');
console.log('isLogin', isLogin)

const PrivateRoute = () => {
  return isLogin ? <Outlet /> : <Navigate to="/login" />;
};

export default PrivateRoute;