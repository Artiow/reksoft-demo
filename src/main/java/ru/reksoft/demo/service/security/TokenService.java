package ru.reksoft.demo.service.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.TextCodec;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

@Service
public class TokenService implements Clock {

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.secret-key}")
    private String secretKey;

    private GzipCompressionCodec compressionCodec;

    private SignatureAlgorithm signatureAlgorithm;

    @PostConstruct
    private void init() {
        secretKey = TextCodec.BASE64.encode(secretKey);
        compressionCodec = new GzipCompressionCodec();
        signatureAlgorithm = SignatureAlgorithm.RS512;
    }

    /**
     * Returns JWT.
     *
     * @param attributes - map of token data
     * @return encoded json
     */
    public String generate(final Map<String, Object> attributes) {
        final Claims claims = Jwts
                .claims(attributes)
                .setIssuer(issuer)
                .setIssuedAt(now());

        return Jwts
                .builder()
                .setClaims(claims)
                .compressWith(compressionCodec)
                .signWith(signatureAlgorithm, secretKey)
                .compact();
    }

    /**
     * Returns encoded and parsed JWT.
     *
     * @param token - encoded json
     * @return map of token data
     */
    public Map<String, Object> verify(final String token) {
        try {
            return Jwts
                    .parser()
                    .requireIssuer(issuer)
                    .setSigningKey(secretKey)
                    .parseClaimsJwt(token)
                    .getBody();
        } catch (JwtException e) {
            return null;
        }
    }


    @Override
    public Date now() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }
}
