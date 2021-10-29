package com.cvdejan.booking.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cvdejan.booking.exception.InvalidTokenException;
import com.cvdejan.booking.model.AppUser;
import com.cvdejan.booking.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.sql.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtil {
    //@Value("${jwt.secret}")
/*    private static final String SECRET_KEY= "jwtsecret";
    private static final Integer ACCESS_TOKEN_EXPIRE_TIME=10*60*1000;
    private static final Integer REFRESH_TOKEN_EXPIRE_TIME=30*60*1000;
    private static final String ISSUER="SPRING_BOOT_CO";*/
    @Autowired
    private JwtConfig jwtConfig;


    public DecodedJWT verifyJWToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtConfig.getSecretKey());
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        }catch (Exception e){
            throw new InvalidTokenException(e.getMessage());
        }
    }

    public String createAccessToken(User user) {
        try {
        Algorithm algorithm=Algorithm.HMAC256(jwtConfig.getSecretKey());
        return JWT.create().withSubject(user.getUsername())
                .withExpiresAt(Date.valueOf(LocalDate.now().plusDays(jwtConfig.getAccessTokenExpirationDays()))) // System.currentTimeMillis()+ACCESS_TOKEN_EXPIRE_TIME))
                .withIssuer(jwtConfig.getIssuer())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
        }catch (Exception e){
            throw new InvalidTokenException(e.getMessage());
        }
    }

    public String createRefreshToken(User user){
        try {
        Algorithm algorithm=Algorithm.HMAC256(jwtConfig.getSecretKey());
        return JWT.create().withSubject(user.getUsername())
                .withExpiresAt(Date.valueOf(LocalDate.now().plusDays(jwtConfig.getAccessTokenExpirationDays())))//new Date(System.currentTimeMillis()+REFRESH_TOKEN_EXPIRE_TIME))
                .withIssuer(jwtConfig.getIssuer())
                .sign(algorithm);
        }catch (Exception e){
            throw new InvalidTokenException(e.getMessage());
        }
    }

    public String createAccessToken(AppUser user) {
        try {
            Algorithm algorithm=Algorithm.HMAC256(jwtConfig.getSecretKey());
            return JWT.create().withSubject(user.getUserName())
                    .withExpiresAt(Date.valueOf(LocalDate.now().plusDays(jwtConfig.getAccessTokenExpirationDays())))//new Date(System.currentTimeMillis()+ACCESS_TOKEN_EXPIRE_TIME))
                    .withIssuer(jwtConfig.getIssuer())
                    .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                    .sign(algorithm);
        }catch (Exception e){
            throw new InvalidTokenException(e.getMessage());
        }
    }
}
