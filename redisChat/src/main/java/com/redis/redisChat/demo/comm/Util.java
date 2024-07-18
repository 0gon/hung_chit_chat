package com.redis.redisChat.demo.comm;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 재사용 될 것 같은 메소드들 넣어놓는 클래스
 * 나중에 모듈 분리할때나 관련성 있는 메소드들끼리 분리해서 정리해야 됨
 */
public class Util {


    /**
     *
     * 헤더 맵 받아서 쿠키 이름 - 쿠키 값 쌍의 맵 반환
     *
     * @param headersMap
     * @return
     */
    public static Map<String, String> getCookieMap(Map<String, String> headersMap) {
        String cookieHeader = headersMap.getOrDefault("cookie", "");
        String[] cookieParts = cookieHeader.split(";");

        Map<String, String> cookiesMap = new HashMap<>();

        for (String cookiePart : cookieParts) {
            // 쿠키 이름과 값은 등호로 분리
            String[] cookie = cookiePart.trim().split("=");
            if (cookie.length == 2) {
                // 쿠키 이름과 값
                String cookieName = cookie[0];
                String cookieValue = cookie[1];

                cookiesMap.put(cookieName, cookieValue);
            }
        }

        return cookiesMap;
    }


    /**
     * JSON 형태의 문자열을 JSONObejct로 파싱
     */
    public static JSONObject jsonToObjectParser(String jsonStr) throws Exception {
        JSONParser parser = new JSONParser();
        JSONObject obj = null;
        obj = (JSONObject) parser.parse(jsonStr);
        return obj;
    }


    /**
     * 내부 ip 조회
     * @return
     */
    public static String getInternalIP() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue; // skip loopback, virtual, and inactive interfaces
                }

                Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (address.isSiteLocalAddress()) {
                        return address.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 외부 ip 조회
     * @return
     */
    public static String getExternalIP() {
        try {
            // 외부 웹 서비스를 통해 IP 주소를 조회
            URL url = new URL("https://api.ipify.org");
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String externalIp = in.readLine().trim();
            in.close();
            return externalIp;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
