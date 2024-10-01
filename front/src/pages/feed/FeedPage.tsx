import Post from "../../components/Post";

export default function FeedPage() {
  return (
    <main className="w-full min-h-screen flex flex-col items-center">
      <div className="my-10">
        <h1 className="text-3xl font-bold">피드 페이지</h1>
      </div>
      <div>
        <Post />
        <Post />
        <Post />
        <Post />
        <Post />
        <Post />
        <Post />
        <Post />
        <Post />
      </div>
    </main>
  );
}
