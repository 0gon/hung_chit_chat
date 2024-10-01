import { Link } from "react-router-dom";

export default function HomePage() {
  return (
    <main className="w-full min-h-screen flex flex-col justify-center items-center">
      <h1 className="text-3xl font-bold mb-10">페이지 목록</h1>
      <Link to="/feed" className="bg-red-500 p-2 text-white rounded-2xl">
        피드 페이지로 이동
      </Link>
    </main>
  );
}
