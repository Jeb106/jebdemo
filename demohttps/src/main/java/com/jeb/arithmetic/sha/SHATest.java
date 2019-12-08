package com.jeb.arithmetic.sha;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName：SHATest
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-12-07 17:29
 */
public class SHATest {
	private  static String src = "jeb security sha";

	public static void main(String[] args) {
		jdkSHA1();
		ccSHA1();
	}
	public static void jdkSHA1(){
		try {
			MessageDigest md = MessageDigest.getInstance("SHA");
			md.update(src.getBytes());
			System.out.println("jdk sha-a:" + Hex.encodeHexString(md.digest()));



		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public static void ccSHA1(){
		System.out.println("ccSHA1:"+DigestUtils.sha1Hex(src.getBytes()));
		System.out.println("ccSHA1:"+DigestUtils.sha1Hex(src));

	}
}
