package com.jeb.arithmetic.md;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @ClassName：Md5Test
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-12-07 17:04
 */
public class Md5Test {
	private static  String src = "jeb security md ";

	public static void main(String[] args) {
		jdkMd5();
		ccMD5();
	}

	public static void jdkMd5(){

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			//加密
			byte[] digest = md.digest(src.getBytes());
			//将数组转化为16进制
			System.out.println("JDK MD5:"+Hex.encodeHexString(digest));


		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

	public static void ccMD5(){
		System.out.println("cc MD5:"+DigestUtils.md5Hex(src.getBytes()));
	}
}
