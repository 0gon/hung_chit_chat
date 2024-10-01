import React from "react";

const data = {
  date: "2024.07.21",
  title: "Title",
  content: "This is a post content",
  imgUrl: "https://picsum.photos/200/300",
};

export default function Post() {
  return (
    <main className="w-[380px] h-[420px] border px-3">
      <div className="text-[15px] text-neutral-500">{data.date}</div>
      <div className="flex justify-center items-center">
        <img src={data.imgUrl} alt="post image" className="object-cover" />
      </div>
      <div>
        <h1 className="text-2xl font-bold">{data.title}</h1>
        <p className="text-[15px] text-neutral-500">{data.content}</p>
      </div>
      <div className="flex justify-between">
        <div className="flex gap-2">
          <div>likes</div>
          <div>comment</div>
        </div>
        <div>share</div>
      </div>
    </main>
  );
}
