package com.mercadolibre.desafiofinalbootcampgrupo2.config;

import java.util.Date;

import com.mercadolibre.desafiofinalbootcampgrupo2.model.Representative;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;


    public String generateToken(Authentication authentication) {
        Representative loggedInUser = (Representative) authentication.getPrincipal();
        Date today = new Date();
        //TODO Ajustar depreciacao
        Long exp = new Long(expiration);
        Date expiration = new Date(today.getTime() + exp);

        return Jwts.builder()
                .setIssuer("Nossa APP")
                .setSubject(loggedInUser.getUsername())
                .setIssuedAt(today)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public boolean validToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    public String getUsername(String token) {
        Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
        Claims body = jwsClaims.getBody();
        return body.getSubject();
    }

}