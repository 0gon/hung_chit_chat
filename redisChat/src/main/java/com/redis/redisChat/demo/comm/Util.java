package com.redis.redisChat.demo.comm;


import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.server.ServerHttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.net.http.HttpHeaders;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 재사용 될 것 같은 메소드들 넣어놓는 클래스
 * 나중에 모듈 분리할때나 관련성 있는 메소드들끼리 분리해서 정리해야 됨
 */
@Slf4j
public class Util {


    /**
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
     *
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
     *
     * 로딩 오래걸리는 문제: UnknownHostException 또는 dns 설정이 잘못되어 있어서 로딩이 오래 걸린다면
     * HttpURLConnection.setConnectTimeout 이 적용되지 않는다.
     * 해결법
     * 1. 강제로 1초 이내로 동작하도록 설정하기
     * 2. 외부ip 찾는 모듈 개발하기
     *
     * @return
     */
    public static String getExternalIP() {
        log.info("외부ip 조회 시작");

        // 방화벽 등의 설정에 의해 조회 못할수도 있으므로 여러 서비스로 조회해본다.
        String[] services = {
                "https://ifconfig.me/ip",
                "https://api.ipify.org",
                "https://icanhazip.com",
                "https://api.my-ip.io/ip"
        };

        for (String service : services) {
            try {
                // 외부 웹 서비스를 통해 IP 주소를 조회
                URL url = new URL(service);
                log.info("{} 조회 서비스", service);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(2000); // 1초 연결 타임아웃
                connection.setReadTimeout(2000);    // 1초 읽기 타임아웃
                connection.setRequestMethod("GET");

                int status = connection.getResponseCode();
                if (status != 200) {
                    throw new IOException("Failed to get IP from " + service + ": " + status);
                }

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String externalIp = in.readLine().trim();
                in.close();
                log.info("외부 ip 조회 성공. {}", externalIp);
                return externalIp;
            } catch (SocketTimeoutException e) {
                log.warn("{} 서비스에서 타임아웃 발생", service);
                // 연결 타임아웃. 반복문 처음으로 넘어감
            } catch (UnknownHostException e) {
                log.error("{} 에 연결할 수 없습니다. 방화벽 또는 라우터를 확인하세요.", service);
                return null;
            } catch (IOException e) {
                log.error("{} 에서 IP 조회 중 오류가 발생했습니다. {}", service, e.getMessage());
            }
        }

        return null;
    }


    /**
     * ServerHttpRequest 를 이용해 쿠키맵 가져오기
     * @param request
     * @return
     */
    public Map<String , String > cookiesMap(ServerHttpRequest request) {

        org.springframework.http.HttpHeaders headers = request.getHeaders();
        Map<String, String> headersMap = headers.toSingleValueMap();

        return Util.getCookieMap(headersMap);
    }

    public static String getCookieValue(ServerHttpRequest request, String cookieName) {

        org.springframework.http.HttpHeaders headers = request.getHeaders();
        Map<String, String> headersMap = headers.toSingleValueMap();
        Map<String, String> cookiesMap = Util.getCookieMap(headersMap);

        return cookiesMap.get(cookieName);
    }
}
