package learn.organizer.security;

import io.jsonwebtoken.*;
import learn.organizer.models.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.stream.Collectors;

@Service
public class JWTUtil {
    private static final String secret = "thisisthesongthatwould'tend";

    public String buildJwt(AppUser user) {
        LocalDateTime exp = LocalDateTime.now().plusDays(7);
        ZonedDateTime zonedExp = exp.atZone(ZoneId.systemDefault());

        String authorities = user.getAuthorities().stream()
                .map(i -> i.getAuthority())
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(user.getUsername())
                .signWith(SignatureAlgorithm.HS256, secret.getBytes(StandardCharsets.UTF_8))
                .setExpiration(Date.from(zonedExp.toInstant()))
                .claim("authorities", authorities)
                .claim("userId", user.getAppUserId())
                .compact();
    }

    public boolean validateJwt(String jwt, UserDetails userDetails) {
        try {
            Jws<Claims> jwtClaims = Jwts.parser().setSigningKey(secret.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(jwt);
            String userName = jwtClaims.getBody().getSubject();
            LocalDateTime exp = LocalDateTime.ofInstant(jwtClaims.getBody().getExpiration().toInstant(), ZoneId.systemDefault());
            return userName.equals(userDetails.getUsername()) && exp.isAfter(LocalDateTime.now());
        } catch (SignatureException e) {
            return false;
        }
    }

    public String getSubjectFromToken(String jwt) {
        try {
            Jws<Claims> jwtClaims = Jwts.parser().setSigningKey(secret.getBytes(StandardCharsets.UTF_8)).parseClaimsJws(jwt);
            return jwtClaims.getBody().getSubject();

        } catch (SignatureException e) {
            return null;
        }
    }
}