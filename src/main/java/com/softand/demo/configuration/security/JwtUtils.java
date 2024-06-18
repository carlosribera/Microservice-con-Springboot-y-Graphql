package com.softand.demo.configuration.security;

import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtils {

    // @Value("${security.jwt.key.private}")
    @Value("4ebb181f77c0d38a19da08485188cb2f847f23f449ba24447437ff8b18d11f82")
    private String privateKey;
    
    // @Value("${security.jwt.user.generator}")
    @Value("AUTHOJWT-BACKEND")
    private String userGenerator;


    public String createToken(Authentication authentication){
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

        String username = authentication.getPrincipal().toString();
        String authorities = authentication.getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(",")); // READ,WRITE,

        String jwtToken = JWT.create()
            .withIssuer(this.userGenerator)
            .withSubject(username)
            .withClaim("authorities", authorities)
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + 1800000))
            .withJWTId(UUID.randomUUID().toString())
            .withNotBefore(new Date(System.currentTimeMillis()))
            .sign(algorithm);
        return jwtToken;
    }


    public DecodedJWT validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

            JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(this.userGenerator)
                .build();

            DecodedJWT decodedJWT = verifier.verify(token);   
            return decodedJWT;

        } catch (JWTVerificationException e) {
            System.out.println("ValidateToken: " + e.getMessage());
            throw new JWTVerificationException("Token invalid, not Authorized");
        }
    }

    public String extractUsername(DecodedJWT decodedJWT){
        return decodedJWT.getSubject().toString();
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName){
        return decodedJWT.getClaim(claimName);
    }

    public Map<String, Claim> returnAllClaim(DecodedJWT decodedJWT){
        return decodedJWT.getClaims();
    }
}
