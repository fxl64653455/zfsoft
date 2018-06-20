/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.apimgr.util;

import java.io.UnsupportedEncodingException;
import java.util.UUID;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * 生成JWT格式的Token工具类
 * @className	： JwtToken
 * @description	： TODO(描述这个类的作用)
 * @author 		：樊新亮（1505）
 * @date		： 2017年9月19日 下午3:42:49
 * @version 	V1.0
 */
public class JwtToken{

	static final String secret = "secret";
	
	public static String createCode() throws IllegalArgumentException, JWTCreationException, UnsupportedEncodingException {
		return JWT.create().withClaim("tokenId", UUID.randomUUID().toString()).sign(Algorithm.HMAC256(secret));
	}
	
	public static String createCode(String clientId) throws IllegalArgumentException, UnsupportedEncodingException {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.create().withClaim("clientId", clientId).withClaim("tokenId", UUID.randomUUID().toString()).sign(algorithm);
	}
	
	public static String getClientId(String token) throws IllegalArgumentException, UnsupportedEncodingException {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		JWTVerifier verifier = JWT.require(algorithm).build();
		DecodedJWT jwt = verifier.verify(token);
		return jwt.getClaim("clientId").asString();
	}
	
}
