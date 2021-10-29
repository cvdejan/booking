package com.cvdejan.booking.util;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@ConfigurationProperties(prefix = "application.jwt")
@Component
public class JwtConfig {
    private String secretKey;
    private String issuer;
    private Integer accessTokenExpirationDays;
    private Integer refreshTokenExpirationDays;

}
