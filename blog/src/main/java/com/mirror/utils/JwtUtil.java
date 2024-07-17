package com.mirror.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mirror.common.Constants;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * JWT工具类
 */
@Data
@Component
public class JwtUtil {


	/**
	 * 创建token
	 * @param name
	 * @return
	 */
	public static String CreateToken(String name) {
		Date nowDate = new Date();
		Date expireDate = new Date(nowDate.getTime() + 1000 * Constants.EXPIRE);
		JWTCreator.Builder builder = JWT.create();
		builder.withClaim("name",name);
		String token = builder.withExpiresAt(expireDate).sign(Algorithm.HMAC256(Constants.SECRET));
		return token;
	}
	/**
	 * 验证token
	 */
	public static void verify(String token){
		JWT.require(Algorithm.HMAC256(Constants.SECRET)).build().verify(token);
	}

	/**
	 * 获取token信息
	 * @param token
	 * @return
	 */
	public static DecodedJWT getTokenInfo(String token){
		return JWT.require(Algorithm.HMAC256(Constants.SECRET)).build().verify(token);
	}


}