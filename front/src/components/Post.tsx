import React from "react";

import { FaRegHeart, FaRegComment } from "react-icons/fa";
import { IoShareOutline } from "react-icons/io5";

const data = {
  date: "2024.07.21",
  title: "Title",
  content: "This is a post content",
  imgUrl: "https://picsum.photos/200/300",
};

export default function Post() {
  return (
    <main className="w-[380px] min-h-[420px] border px-4 my-10">
      <div className="text-[15px] text-neutral-500">{data.date}</div>
      <div className="flex justify-center items-center">
        <img
          src={data.imgUrl}
          alt="post image"
          className="w-full max-h-[420px] object-cover"
        />
      </div>
      <div className="my-[10px]">
        <h1 className="text-2xl font-bold">{data.title}</h1>
        <p className="text-[15px] text-neutral-500">{data.content}</p>
      </div>
      <div className="flex justify-between my-[20px]">
        <div className="flex gap-6 items-center">
          <div className="flex items-center gap-2">
            <button>
              <FaRegHeart size={20} />
            </button>
            <span>0</span>
          </div>
          <div className="flex items-center gap-2">
            <button>
              <FaRegComment size={20} />
            </button>{" "}
            <span>0</span>
          </div>
        </div>
        <div>
          <IoShareOutline size={20} />
        </div>
      </div>
    </main>
  );
}
