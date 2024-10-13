// import React, { useState, useEffect } from "react";
// import { NavLink, useLocation } from "react-router-dom";
// import PropTypes from "prop-types";

// export const SidebarNav = ({ items }) => {
//   const location = useLocation();
//   const [activeGroup, setActiveGroup] = useState(null); //수정한 부분
//   const [activeItem, setActiveItem] = useState(null); //수정한 부분
//   const navLink = (name, icon) => {
//     return (
//       <>
//         {icon && icon}
//         {name && name}
//       </>
//     );
//   };

//   const navItem = (item, index) => {
//     const { component, name, icon, ...rest } = item;
//     const Component = component;

//     return (
//       <Component
//         {...(rest.to &&
//           !rest.items && {
//             component: NavLink,
//           })}
//         key={index}
//         onClick={() => setActiveItem(index)} //수정한 부분
//         style={{
//           backgroundColor: location.pathname === rest.to ? "#f8f8f8" : "white",
//         }} //수정한 부분
//         {...rest}
//       >
//         {navLink(name, icon)}
//       </Component>
//     );
//   };

//   const navGroup = (item, index) => {
//     const { component, name, icon, to, ...rest } = item;
//     const Component = component;
//     const isActiveGroup = location.pathname.startsWith(to); //수정한 부분

//     return (
//       <Component
//         idx={String(index)}
//         key={index}
//         toggler={navLink(name, icon)}
//         onClick={() => setActiveGroup(index)} // 수정된 부분
//         visible={location.pathname.startsWith(to)}
//         style={{
//           backgroundColor:
//             isActiveGroup && index === activeGroup ? "#f8f8f8" : "white",
//         }} //수정한 부분
//         {...rest}
//       >
//         {item.items?.map((item, index) =>
//           item.items ? navGroup(item, index) : navItem(item, index)
//         )}
//       </Component>
//     );
//   };

//   useEffect(() => {
//     const activeGroupIndex = items.findIndex((item) =>
//       location.pathname.startsWith(item.to)
//     );
//     if (activeGroupIndex !== -1) {
//       setActiveGroup(activeGroupIndex);
//     }
//   }, [items, location.pathname]); //수정한 부분
//   return (
//     <React.Fragment>
//       {items &&
//         items.map((item, index) =>
//           item.items ? navGroup(item, index) : navItem(item, index)
//         )}
//     </React.Fragment>
//   );
// };

// SidebarNav.propTypes = {
//   items: PropTypes.arrayOf(PropTypes.any).isRequired,
// };
