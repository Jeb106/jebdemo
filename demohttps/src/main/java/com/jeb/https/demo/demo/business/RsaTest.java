package com.jeb.https.demo.demo.business;

import com.example.framework.utils.RSAUtil;
import org.bouncycastle.jcajce.provider.asymmetric.RSA;
import org.springframework.cache.annotation.EnableCaching;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author jinBiaoHu
 * @date 2019-01-21 22:17
 */
@EnableCaching
public class RsaTest {
	public static void main(String[] args) {
		RSAPublicKey defaultPublicKey = RSAUtil.getDefaultPublicKey();
		RSAPrivateKey defaultPrivateKey = RSAUtil.getDefaultPrivateKey();
		System.out.println(defaultPublicKey.toString());
		System.out.println(defaultPrivateKey.toString());
		String text = "hello,iam lhx";
		String mi = RSAUtil.encryptString(defaultPublicKey, text);
		System.out.println(mi);
		String ming = RSAUtil.decryptString(defaultPrivateKey, mi);
		System.out.println(ming);


	}
}
