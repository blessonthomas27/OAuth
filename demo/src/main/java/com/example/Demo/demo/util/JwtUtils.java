package com.example.Demo.demo.util;

import com.example.Demo.demo.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {

    private static String refresh_secret="Hi_just_a_secret_refresh_token";
    private static String access_secret="Hi_just_a_secret_access_token";
    private static  long refresh_expiryDuration =60*60;
    private static  long access_expiryDuration =30;

    public String generateAccessJwt(User user){

        long milliTime=System.currentTimeMillis();
        long expiryTime=milliTime+access_expiryDuration*1000;

        Date issuedat=new Date(milliTime);
        Date expiryAt=new Date(expiryTime);

        Claims claims= Jwts.claims().setIssuer(user.getId().toString()).
                setIssuedAt(issuedat).setExpiration(expiryAt);

        claims.put("id",user.getId());
        claims.put("name",user.getName());
        claims.put("emailId",user.getEmailId());
        claims.put("type","access-token");

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256,access_secret)
                .compact();
    }

    public  String generateRefreshToken(User user){
        long milliTime=System.currentTimeMillis();
        long expiryTime=milliTime+refresh_expiryDuration*1000;

        Date issuedat=new Date(milliTime);
        Date expiryAt=new Date(expiryTime);

        Claims claims= Jwts.claims().setIssuer(user.getId().toString()).
                setIssuedAt(issuedat).setExpiration(expiryAt);

        claims.put("id",user.getId());
        claims.put("name",user.getName());
        claims.put("emailId",user.getEmailId());
        claims.put("type","refresh-token");

        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256,refresh_secret)
                .compact();
    }

    public String verifyRefresh(String authorization){
        try {
            Jwts.parser().setSigningKey(refresh_secret).parseClaimsJws(authorization);
        }catch (ExpiredJwtException e){
            return "expire";
        }catch (Exception e){
            return null;
        }
        return "ok";
    }

    public String verifyAccess(String authorization){
        try {
            Jwts.parser().setSigningKey(access_secret).parseClaimsJws(authorization);
        }catch (ExpiredJwtException e){
            return "expire";
        }catch (Exception e){
            return null;
        }
        return "ok";
    }
}
