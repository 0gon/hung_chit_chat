// import React, { Suspense } from "react";
// import { Navigate, Route, Routes } from "react-router-dom";
// // import { CContainer, CSpinner } from "@coreui/react";
// // import { useLocation } from "react-router-dom";

// import routes from "../../routes";

// // eslint-disable-next-line @typescript-eslint/no-explicit-any
// const AppContent = ({ headerAlart, setHeaderAlart }: any) => {
//   // const currentLocation = useLocation().pathname;

//   // const getRouteName = (pathname, routes) => {
//   //   const currentRoute = routes.find((route) => route.path === pathname);
//   //   return currentRoute ? currentRoute.name : false;
//   // };

//   return (
//     <div>
//       <Suspense>
//         <Routes>
//           {routes.map((route, idx) => {
//             return (
//               route.element && (
//                 <Route
//                   key={idx}
//                   path={route.path}
//                   // exact={route.exact}
//                   // name={route.name}
//                   element={
//                     <route.element
//                       headerAlart={headerAlart}
//                       setHeaderAlart={setHeaderAlart}
//                     />
//                   }
//                 />
//               )
//             );
//           })}
//           {/* <Route path="/" element={<Navigate to="dashboard" replace />} /> */}
//           <Route path="/" element={<Navigate to="/feed" replace />} />
//         </Routes>
//       </Suspense>
//     </div>
//   );
// };

// export default AppContent;
