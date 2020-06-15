package com.jeb.https.rest.test;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;


public class ZwwHttpUtils {
	public static void getToken(String url,String appKey, String secretKey){
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new BasicNameValuePair("grant_type", "client_credentials"));
		pairs.add(new BasicNameValuePair("client_id", appKey));
		pairs.add(new BasicNameValuePair("client_secret", secretKey));

		CloseableHttpClient client;
		try
		{
			/** 获取client方式一：*/
//			sslContext = org.apache.http.conn.ssl.SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).useTLS().build();
//			SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
//					new AllowAllHostnameVerifier());
//			client = HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();

			/** 获取client方式二：绕过ssl校验 */
			//client =ZwwHttpUtils.acceptsUntrustedCertsHttpClient();
			/**获取client方法三 绕过ssl校验 */
			client = ZwwHttpUtils.getIgnoeSSLClient();
			URIBuilder uriBuilder = new URIBuilder(url);
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, "UTF-8"));
			httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
			HttpResponse response = client.execute(httpPost);
			execute(response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	private static void execute(HttpResponse response) throws IOException, HttpException {
		if (response.getStatusLine().getStatusCode() == 200) {
			String result = EntityUtils.toString((response).getEntity());
			System.out.println("接口返回值：" + result);
		} else {
			throw new HttpException(response.getStatusLine() + EntityUtils.toString((response).getEntity()));
		}
	}

	/**
	 * get请求，参数拼接在url后面
	 * @param url
	 * @param headers
	 */

	public static void doGetSsl(String url,String headers){

		CloseableHttpClient client;
		try
		{
			/** 获取client方式一：*/
//			sslContext = org.apache.http.conn.ssl.SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).useTLS().build();
//			SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
//					new AllowAllHostnameVerifier());
//			client = HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();

			/** 获取client方式二：绕过ssl校验 */
			//client =ZwwHttpUtils.acceptsUntrustedCertsHttpClient();
			/**获取client方法三 绕过ssl校验 */
			client = ZwwHttpUtils.getIgnoeSSLClient();
			URIBuilder uriBuilder = new URIBuilder(url);
			HttpGet httpGet = new HttpGet(uriBuilder.build());
			httpGet.addHeader("Content-Type", "application/json");
			if (StringUtils.isNotEmpty(headers)) {
				JSONArray jsonArray = JSONArray.parseArray(headers);
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String name = jsonObject.getString("name");
					String value = jsonObject.getString("value");
					httpGet.addHeader(name,value);
				}
			}
			HttpResponse response = client.execute(httpGet);
			execute(response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}


	public static void doPostSsl(String url,String  param,String headers){

		CloseableHttpClient client;
		try
		{
			/** 获取client方式一：*/
//			sslContext = org.apache.http.conn.ssl.SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).useTLS().build();
//			SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext,
//					new AllowAllHostnameVerifier());
//			client = HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();

			/** 获取client方式二：绕过ssl校验 */
			//client =ZwwHttpUtils.acceptsUntrustedCertsHttpClient();
			/**获取client方法三 绕过ssl校验 */
			client = ZwwHttpUtils.getIgnoeSSLClient();
			URIBuilder uriBuilder = new URIBuilder(url);
			HttpPost httpPost = new HttpPost(uriBuilder.build());
			httpPost.addHeader("Content-Type", "application/json");
			if (StringUtils.isNotEmpty(headers)) {
				JSONArray jsonArray = JSONArray.parseArray(headers);
				for (int i = 0; i < jsonArray.size(); i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String name = jsonObject.getString("name");
					String value = jsonObject.getString("value");
					httpPost.addHeader(name,value);
				}
			}
			httpPost.setEntity(new StringEntity(param));
			HttpResponse response = client.execute(httpPost);
			execute(response);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}


	/**
	 * 方法一
	 * @return
	 * @throws Exception
	 */
	public static CloseableHttpClient getIgnoeSSLClient() throws Exception {

		SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustStrategy() {

			@Override
			public boolean isTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
				return true;
			}

		}).build();

		//创建httpClient
		CloseableHttpClient client = HttpClients.custom().setSSLContext(sslContext).
				setSSLHostnameVerifier(new NoopHostnameVerifier()).build();
		return client;
	}

	/**
	 * 方法二
	 * @return
	 * @throws Exception
	 */

	public static CloseableHttpClient acceptsUntrustedCertsHttpClient() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		HttpClientBuilder b = HttpClientBuilder.create();

		// setup a Trust Strategy that allows all certificates.
		//
		SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				return true;
			}
		}).build();
		b.setSslcontext( sslContext);

		// don't check Hostnames, either.
		//      -- use SSLConnectionSocketFactory.getDefaultHostnameVerifier(), if you don't want to weaken
		HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;

		// here's the special part:
		//      -- need to create an SSL Socket Factory, to use our weakened "trust strategy";
		//      -- and create a Registry, to register it.
		//
		SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
		Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", sslSocketFactory)
				.build();

		// now, we create connection-manager using our Registry.
		//      -- allows multi-threaded use
		PoolingHttpClientConnectionManager connMgr = new PoolingHttpClientConnectionManager( socketFactoryRegistry);
		b.setConnectionManager( connMgr);

		// finally, build the HttpClient;
		//      -- done!
		CloseableHttpClient client = b.build();
		return client;
	}
}