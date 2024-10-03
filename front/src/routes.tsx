import React from "react";

const AuctionPage = React.lazy(() => import("./page/auction/auction"));
const FeddPage = React.lazy(() => import("./page/feed/feed"));

const routes = [
  { path: "/auction", name: "모임 통합 관리", element: AuctionPage },
  { path: "/feed", element: FeddPage },
];

export default routes;
