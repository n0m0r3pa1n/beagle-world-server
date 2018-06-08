package com.nmp90.beagleworld.security

import com.nmp90.beagleworld.exception.CustomException
import com.nmp90.beagleworld.users.Role
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
class JwtTokenProvider {

    private var secretKey: String = "MySecret"

    private val validityInMilliseconds: Long = 3600000; // 1h

    @Autowired
    private lateinit var myUserDetails: MyUserDetails

    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray());
    }

    fun createToken(username: String, roles: List<Role>): String {
        val claims = Jwts.claims().setSubject(username);
        claims.put("auth", roles.stream()
                .map({ SimpleGrantedAuthority(it.authority) })
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));

        val now = Date()
        val validity = Date(now.time + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails: UserDetails = myUserDetails.loadUserByUsername(getUsername(token));
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities);
    }

    fun getUsername(token: String): String {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    fun resolveToken(req: HttpServletRequest): String? {
        val bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length);
        }

        return null;
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (ex: Exception) {
            when (ex) {
                is JwtException,
                is IllegalArgumentException ->
                    throw CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
                else -> throw ex
            }
        }
    }

}