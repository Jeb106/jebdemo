package com.jeb.https.rest.test;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicHeader;

/**
 * @ClassName：TestUtil
 * @description：使用证书版本
 * @author： huJb
 * @date：2020-02-26 15:27
 */
public class HttpTestUtilSsl {
	public static String keyStorePath = "/Users/hujinbiao/workspace/service/src/com/main/resources/own-ssl-server.jks";
	public static String keyStorepass = "123456";
	public static void doGet(String url,String  param,String header){
		try {
			HCB hcb = HCB.custom()
					.timeout(5000)
					.pool(100, 10)
					.retry(5);
			HttpClient client = hcb.build();
			HttpConfig config = HttpConfig.custom()
					.url(url)
					.encoding("utf-8")
					.client(client)
					.json(JSON.toJSONString(param));
			String result = HttpClientUtil.get(config);
			System.out.println("请求结果："+result);

		} catch (Exception e) {

		}
	}

	public static void doPost(String url,String  param,String headers){
		BasicHeader[] basicHeaders = null;
		if (StringUtils.isNotEmpty(headers)) {
			JSONArray jsonArray = JSONArray.parseArray(headers);
			basicHeaders = new BasicHeader[jsonArray.size()];
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String name = jsonObject.getString("name");
				String value = jsonObject.getString("value");
				BasicHeader basicHeader = new BasicHeader(name, value);
				basicHeaders[i] = basicHeader;
			}
		}
		try {
			HCB hcb = HCB.custom()
					.timeout(5000)
					.pool(100, 10)
					.ssl(keyStorePath,keyStorepass)
					.retry(5);
			HttpClient client = hcb.build();
			HttpConfig config = HttpConfig.custom()
					.url(url)
					.encoding("utf-8")
					.client(client)
					.json(param);
//					.json(JSON.toJSONString(param));
			if (StringUtils.isNotEmpty(headers)) {
				config.headers(basicHeaders);
			}
			String result = HttpClientUtil.post(config);
			System.out.println("请求结果："+result);

		} catch (Exception e) {

		}
	}

}
