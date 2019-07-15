package com.carl.springmvc.common;

import com.carl.springmvc.beans.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class PasswordHelper {
	private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

	@Value("md5")
	private String algorithmName = "md5";

	@Value("1024")
	private int hashIterations = 1024;

	public void setRandomNumberGenerator(
			RandomNumberGenerator randomNumberGenerator) {
		this.randomNumberGenerator = randomNumberGenerator;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	public String encryptPassword(User user) {
		String password = user.getUserPassword() == null ? "" : user.getUserPassword();
		ByteSource passwordSalt = ByteSource.Util.bytes(password);
		String newPassword = new SimpleHash(algorithmName, password,
				passwordSalt, hashIterations).toHex();
		return newPassword;
	}
}
