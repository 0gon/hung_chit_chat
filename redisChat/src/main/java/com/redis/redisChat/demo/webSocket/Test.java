package com.redis.redisChat.demo.webSocket;

import com.redis.redisChat.demo.comm.Util;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        String test = "{\"type\":\"message\",\"memberId\":\"asdf\",\"nickName\":\"asdf\",\"text\":\"asdf\",\"roomId\":\"1\",\"members\":[\"fdsa\",\"asdf\"]}";
        JSONObject jsonObject = Util.jsonToObjectParser(test);
        JSONArray jsonArray = (JSONArray) jsonObject.get("members");
        List<String> list = (List<String>) jsonArray;
        System.out.println(list);
    }
}
