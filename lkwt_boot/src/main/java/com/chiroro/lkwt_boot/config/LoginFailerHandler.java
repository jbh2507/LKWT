package com.chiroro.lkwt_boot.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * LoginFailerHandler
 */
@Slf4j
public class LoginFailerHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse res, AuthenticationException auth)
            throws IOException, ServletException {
        
        req.setAttribute("username", req.getParameter("username"));
        req.setAttribute("msg", "Login Fail");

        log.info("Login fail");
        
        req.getRequestDispatcher("/login").forward(req, res);

    }

}