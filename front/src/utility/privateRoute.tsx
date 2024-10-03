import { Navigate } from "react-router-dom";

interface PrivateRouteProps {
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  authenticated: any;
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  component: any;
}

const PrivateRoute: React.FC<PrivateRouteProps> = ({
  authenticated,
  component: Component,
}) => {
  return authenticated ? <Component /> : <Navigate to="/login" />;
};

export default PrivateRoute;
