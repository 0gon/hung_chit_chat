package com.redis.redisChat.demo.comm;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class CookieHandler {

    private String getCookieValue(HttpServletRequest request, String name) {

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (cookieName.equals(name)) {
                return cookie.getValue();
            }
        }

        return null;
    }

    public static String getCookieValue(Cookie[] cookies, String name) {
        for (Cookie cookie : cookies) {
            String cookieName = cookie.getName();
            if (cookieName.equals(name)) {
                return cookie.getValue();
            }
        }

        return null;
    }

}
