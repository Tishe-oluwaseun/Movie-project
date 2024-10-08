package com.movie_project.movie_Base.Jwt.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "j6MlnDOyzZh8EqD5rZKPl4hZlzCvDyRSO8SO26VZfAmug6riEvDRESxun5YILpo5bd3fSNNIM3aPF/7pWHQbudb+w08kF9VQbEbkHxBSoCBW344Fmdl8RsTpVjwqwhaiik6qI88aK4yhCZC3RfVQc4mONDG3pilssZv8w3KURxBtsqhtOnUJefCqYWY8QSu98k49aabOoMleV10uG7Ykvatm4SktHmXiNQaQuwTnM/KkL0qDU0PCh043aAKLJdWSgCyBtQ7YnHSj863wiyxZ3qdGYrKOeIUF8IZjh3IxWwCn8fmZguRtVmBI+wp5z8w4PtDrqFkWjeadfiDIwOgzCgiMCt8/j2VBLBCoQlwBbsikTiYPLe+E5CZtZv1xRajUG/CfgERnugckXjMzFyFS+9Fl6ftrHhfbB28sK3k9PKCEdZjTXJ/jSB+iOxI4CX/E3USw5Sv35KOjOnW1MRd/ioPPPhkaPsv3LVA8fStgk7KI8soSGY9NnXQegMHzRZjuGtwpUhRE04Ji0457TPMVhBKD6v08HUZmB+/PLP5FkaBMK7Py8PMI13RVRqX6sgImLsqDRw4jMqbjPTAEjMQZgO6Izljwvk2zLNuACH8NlNZK8NubV2yDb3n+WXlACn/7XTmSQJBO4Kl22pt+CKkxFDQl8SNsnva134VRDAshGsXUPDIracCoTsyla3huNmJR";

    public String extractUsername(String token){
        return extractClaim(token ,Claims::getSubject);

    }

    public <T> T extractClaim(String token , Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }

    public  String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);

    }

    public boolean isTokenValid(String token , UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()))&& !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token ,Claims::getExpiration);
    }

    public static String generateToken(Map<String,Object> extraClaims, UserDetails userDetails)
    {
        return Jwts.builder()
                .claims().empty().add(extraClaims).and()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey())
                .compact();

    }


    //Claims are used to verify that the user is who he/she claims to be(if the user details is in the database)
    private Claims extractAllClaims (String token){
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private static SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
