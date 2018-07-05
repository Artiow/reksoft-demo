package ru.reksoft.demo.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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

    public String getToken(final Map<String, Object> attributes) {
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

    public Map<String, Object> verify(final String token) {
        return Jwts
                .parser()
                .requireIssuer(issuer)
                .setSigningKey(secretKey)
                .parseClaimsJwt(token)
                .getBody();
    }

    @Override
    public Date now() {
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }
}
