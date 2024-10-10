package com.hcc.socket.chat.config;

import com.hcc.socket.chat.mock.FakeHttpSerwvletReuqest;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ValidJWTFilterTest {


    @Test
    public void test() throws ServletException, IOException {
        ValidJWTFilter validJWTFilter = new ValidJWTFilter();
        validJWTFilter.doFilterInternal(new FakeHttpSerwvletReuqest(), null, null);



    }
}