import { defineConfig } from "vite";
import react from "@vitejs/plugin-react";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy: {
      // '/api'로 시작하는 요청을 타겟 서버로 프록시합니다.
      "/api": {
        target: "http://localhost:8000", // 백엔드 서버 URL
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""), // 경로에서 /api를 제거합니다.
      },
    },
  },
});
