package eshopping.demo.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import eshopping.demo.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    private static final String SECRET_KEY = "5Zu2xCmJIFwERg9pQz533b8e/k/MsBMBD8RjCnv86raxSipq8vH2G24BJfIyemYa";

    public String extractUsername(String token) {
        return extracatClaim(token, Claims::getSubject);

    }

    public <T> T extracatClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = userDetails.getUsername();
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extracatExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
        // Cast UserDetails to User to access user's id and role
        User user = (User) userDetails;

        // Include user ID and role in claims
        extraClaims.put("user_id", user.getId());
        extraClaims.put("role", user.getRole().toString());

        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();

    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private Date extracatExpiration(String token) {
        return extracatClaim(token, Claims::getExpiration);
    }

    public Integer extractUserId(String token) {
        Claims claims = extractAllClaims(token);
        return (Integer) claims.get("user_id");
    }

}
