package com.jeb.arithmetic.base64;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @ClassName：Base64Test
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-12-07 16:42
 */
public class Base64Test {
	private static String src = "jeb sercurity base64";

	public static void main(String[] args) {
//		jdkBase64();
		codecBase64();
	}


	public static void codecBase64() {
		try {//加密
			byte[] base64 = Base64.encodeBase64(src.getBytes());
			System.out.println("encode:"+new String(base64));

			//解密
			byte[] decodeBase64 = Base64.decodeBase64(base64);
			System.out.println("decode:"+new String(decodeBase64));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void jdkBase64() {
		try {//加密
			BASE64Encoder encoder = new BASE64Encoder();
			String encode = encoder.encode(src.getBytes());
			System.out.println("encode:" + encode);

			//解密
			BASE64Decoder decoder = new BASE64Decoder();
			System.out.println("decoder:"+new String(decoder.decodeBuffer(encode)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
