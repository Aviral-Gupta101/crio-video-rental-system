package site.aviralgupta.video_rental_system.component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtUtil {

    private final SecretKey key;

    public JwtUtil() {
        String secret = "1c4b4ec78d12824642a0bddde81e72608a6ffd1265d6c65d072db3ffc77c80d4b85d66dffc0dd0e201d7ae19c715f018ed87142863d8fc8c9af88e74c43bb643cc5d559f13d241063c8aa7e71ceeb5b158590469857412deb53384679c2d6f8cd5d572ed5dea31a444c9005747b159fb52c28a12667446c70dd70e80720aea16ec5566c5f182e56c1fd670ba542a0323263a07370f3ae6ac5cf9cc0f49b3603214b12fafdc0880a2b6cb2e982d8fb050b625825337c7572e53e00857ce36d4e7829fe40291eab9f413497e5a63989fbfd8a6963ab14301fc80625b5ce1c064cfca5d7c426140243edbf39cc4b13a3364aa7b4d3fbf68f1a88654f96189b1b1f6"; // min 256-bit for HS256
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(1, ChronoUnit.HOURS)))
                .signWith(key)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().verifyWith((SecretKey) key).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername());
    }
}

