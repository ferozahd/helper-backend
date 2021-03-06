package com.shippingoo.config;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import com.shippingoo.service.userdetails.CustomUserDetails;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenHelper {
	private Logger log = Logger.getLogger(JWTTokenHelper.class.getName());
	@Value("${jwt.auth.app}")
	private String appName;

	@Value("${jwt.auth.secret_key}")
	private String secretKey;

	@Value("${jwt.auth.expires_in}")
	private int expiresIn;

	private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

	private Claims getAllClaimsFromToken(String token) {
		Claims claims;
		try {
			claims = Jwts.parser()
					.setSigningKey(secretKey)
					.parseClaimsJws(token)
					.getBody();
		} catch (Exception e) {
			claims = null;
		}
		return claims;
	}

	public String getUsernameFromToken(String token) {
		String username;

		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			username = claims.getSubject();
		} catch (Exception e) {
			username = null;
		}
		return username;
	}

	public String generateToken(Authentication authentication)
			throws InvalidKeySpecException, NoSuchAlgorithmException {

		Map<String, Object> claims = new HashMap<>();
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication
				.getPrincipal();
		// claims.put("userId",customUserDetails.getId());

		return Jwts.builder()
				.setIssuer(appName)
				.setSubject(customUserDetails.getUsername())
				.addClaims(claims)
				.setIssuedAt(new Date())
				.setExpiration(generateExpirationDate())
				.signWith(SIGNATURE_ALGORITHM, secretKey)
				.compact();
	}

	private Date generateExpirationDate() {
		return new Date(new Date().getTime() + expiresIn * 1000);
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username != null &&
				username.equals(userDetails.getUsername()) &&
				!isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		Date expireDate = getExpirationDate(token);
		return expireDate.before(new Date());
	}

	private Date getExpirationDate(String token) {
		Date expireDate;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			expireDate = claims.getExpiration();
		} catch (Exception e) {
			expireDate = null;
		}
		return expireDate;
	}

	public Date getIssuedAtDateFromToken(String token) {
		Date issueAt;
		try {
			final Claims claims = this.getAllClaimsFromToken(token);
			issueAt = claims.getIssuedAt();
		} catch (Exception e) {
			issueAt = null;
		}
		return issueAt;
	}

	public String getToken(HttpServletRequest request) {

		String authHeader = getAuthHeaderFromHeader(request);
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.substring(7);

		}

		return null;
	}

	public String getAuthHeaderFromHeader(HttpServletRequest request) {
		return request.getHeader(HttpHeaders.AUTHORIZATION);
	}
}
