package com.cvdejan.booking.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.cvdejan.booking.exception.InvalidTokenException;
import com.cvdejan.booking.model.AppUser;
import com.cvdejan.booking.service.AppUserService;
import com.cvdejan.booking.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping(value="/api")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;
    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping(path = "/token/refresh")
    public ResponseEntity<Map<String,String>> refreshToken(HttpServletRequest request, HttpServletResponse response) {
        String authorizationHeader=request.getHeader(AUTHORIZATION);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String refresh_token = authorizationHeader.substring("Bearer ".length());
            DecodedJWT decodedJWT = jwtUtil.verifyJWToken(refresh_token);
            String userName = decodedJWT.getSubject();
            AppUser user=appUserService.getUser(userName);
            String access_token= jwtUtil.createAccessToken(user);
            Map<String,String> tokens=new HashMap<>();
            tokens.put("access_token", access_token);
            tokens.put("refresh_token", refresh_token);
            return new ResponseEntity<Map<String,String>>(tokens, HttpStatus.OK);

        } else throw new InvalidTokenException("Refresh token is missing!");
    }
}