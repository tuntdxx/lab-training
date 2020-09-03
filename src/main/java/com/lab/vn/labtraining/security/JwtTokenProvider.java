package com.lab.vn.labtraining.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lab.vn.labtraining.controller.BaseController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider extends BaseController {

	// Thời gian có hiệu lực của chuỗi jwt
	private final long JWT_EXPIRATION = 500 * 60 * 60;

	@Value("${spring.jwt.secret}")
	// Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
	private String JWT_SECRET;

	// Tạo ra jwt từ thông tin user
	public String generateToken(CustomUserDetails userDetails) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
		// Tạo chuỗi json web token từ id của user.
		// setSubject co the truyen bat ky cai gi de gen ra token
		// sau khi co token truoc khi chay vao cac api se chay vao filter
		// (doFilterInternal)
		// de check token hop le ko thi moi cho truy cap vao api
		return Jwts.builder().setSubject(Long.toString(userDetails.getUser().getID())).setIssuedAt(now)
				.setExpiration(expiryDate).signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
	}

	// Lấy thông tin user từ jwt
	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();

		return Long.parseLong(claims.getSubject());
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token" + ex.getMessage());
			ex.printStackTrace();
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token" + ex.getMessage());
			ex.printStackTrace();
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token" + ex.getMessage());
			ex.printStackTrace();
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty." + ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}
}
