import React, { Suspense } from "react";
import { HashRouter, Route, Routes } from "react-router-dom";
// import PrivateRoute from "./utility/privateRoute";
// import { useSelector } from "react-redux";
// import "../style/style.scss";

const loading = (
  <div className="pt-3 text-center">
    <div className="sk-spinner sk-spinner-pulse"></div>
  </div>
);

// // Containers
// const DefaultLayout = React.lazy(
//   () => import("./components/layout/defaultLayout")
// );

// Pages
const Login = React.lazy(() => import("./page/login/login"));

const App = () => {
  // const session = useSelector((state) => state.app.session);
  const session = "a";

  return (
    <HashRouter>
      <Suspense fallback={loading}>
        <Routes>
          <Route
            // exact
            path="/login"
            // name="Login Page"
            element={session ? <Login /> : <Login />}
            // element={<Login />}
          />
          {/* <Route
            // exact
            path="/"
            // name="Login Page"
            element={session ? <Login /> : <Login />}
            // element={<Login />}
          /> */}
          {/* <Route
            path="*"
            // name="Home"
            element={
              <PrivateRoute
                authenticated={session}
                component={
                  <DefaultLayout visible={undefined} setVisible={undefined} />
                }
              />
            }
          /> */}
        </Routes>
      </Suspense>
    </HashRouter>
  );
};

export default App;
