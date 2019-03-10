package com.example.framework.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @author by wolf on 2017/3/28.
 */
@Slf4j
public class HttpClientUtil {

	/**
	 * 构建一个HttpClient请求
	 *
	 * @return
	 */
	public static CloseableHttpClient buildHttpClient() {
		return buildHttpClient(false);
	}

	/**
	 * 构建一个HttpClient请求
	 *
	 * @param sslEnable
	 * @return
	 */
	public static CloseableHttpClient buildHttpClient(boolean sslEnable) {
		CloseableHttpClient httpClient;//创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		if (sslEnable) {
			try {
				SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
					//信任所有
					public boolean isTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
						return true;
					}
				}).build();
				HostnameVerifier defaultVerifier = new HostnameVerifier() {
					@Override
					public boolean verify(String s, SSLSession sslSession) {
						return true;
					}
				};

				SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext,defaultVerifier);
				httpClient = HttpClients.custom()
						.setSSLSocketFactory(sslsf)
						.build();
			}
			catch (Exception e) {
				log.error("create ssl HttpClient error, use default HttpClient. error:", e);
				httpClient = httpClientBuilder.build();
			}
		}
		else {
			httpClient = httpClientBuilder.build();
		}
		return httpClient;
	}

}
