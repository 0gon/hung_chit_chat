package com.hcc.socket.webSocket.config;

import com.hcc.socket.webSocket.mock.FakeHttpSerwvletReuqest;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class ValidJWTFilterTest {


    @Test
    public void test() throws ServletException, IOException {
        ValidJWTFilter validJWTFilter = new ValidJWTFilter();
        validJWTFilter.doFilterInternal(new FakeHttpSerwvletReuqest(), null, null);



    }
}