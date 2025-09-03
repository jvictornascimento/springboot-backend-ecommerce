package com.jvictornascimento.buynowdotcom.security.jwt;

import com.jvictornascimento.buynowdotcom.security.user.ShopUserDetail;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;


@Component
public class JwtUtils {

    @Value("${auth.token.jwtSecret}")
    private String jwrSecret;

    @Value("${auth.token.accessExpirationInMils}")
    private String expiationTime;

    @Value("${auth.token.refreshExpirationInMils}")
    private String refreshExpirationTime;

    public String generateAccessTokenForUser(Authentication authentication) {
        ShopUserDetail userPrincipal = (ShopUserDetail) authentication.getPrincipal();

        List<String> roles = userPrincipal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).toList();
        return Jwts.builder()
                .setSubject(userPrincipal.getEmail())
                .claim("id", userPrincipal.getId())
                .claim("role", roles)
                .setIssuedAt(new Date())
                .setExpiration(calculateExpirationDate(expiationTime))
                .signWith(key(), SignatureAlgorithm.ES256)
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(calculateExpirationDate(refreshExpirationTime))
                .signWith(key(), SignatureAlgorithm.ES256)
                .compact();
    }
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwrSecret));
    }

    public Date calculateExpirationDate(String expiationTimeString) {
        long expirationTime = Long.parseLong(expiationTimeString);
        return new Date(System.currentTimeMillis() + expirationTime);
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody().getSubject();
    }
    public boolean validateToken(String token) {
        try{
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (JwtException e) {
            throw new JwtException(e.getMessage());
        }
    }

}
