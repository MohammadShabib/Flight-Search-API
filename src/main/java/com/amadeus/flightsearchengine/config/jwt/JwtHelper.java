package com.amadeus.flightsearchengine.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtHelper
{
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private String secret =
            "773167d2f4c43d0f11a0159215efb62f0d66196c340ac0402e1cceb76c62acda149373d83d9eaf67ae070a787800df9eb650cc66b9456e2d3bb4f8fb81e69205";
    public String generateToken()
    {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
    public Boolean validateToken(String aInToken)
    {
        return ! isTokenExpired(aInToken);
    }

    private Claims getAllClaimsFromToken(String aInToken)
    {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(aInToken).getBody();
    }
    private Boolean isTokenExpired(String aInToken)
    {
        final Date expiration = getClaimFromToken(aInToken, Claims::getExpiration);
        return expiration.before(new Date());
    }

    private  <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
    {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
}