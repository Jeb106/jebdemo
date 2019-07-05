package com.example.test.http;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName：HttpController
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-07-05 10:27
 */
@RestController
@Data
@Slf4j
public class HttpController {

	@Autowired
	Environment environment;

	public volatile    boolean flag = true;
	/**
	 * 发送 GET 请求（HTTP），K-V形式
	 *
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doGet(String url, Map<String, Object> headers, Map<String, Object> params) {
		StopWatch stopWatch = new StopWatch("stopwatch test");
		stopWatch.start("执行本地方法");
		JSONObject jsonObject = null;
		String apiUrl = url;
		StringBuffer param = new StringBuffer();
		int i = 0;
		for (String key : params.keySet()) {
			if (i == 0)
				param.append("?");
			else
				param.append("&");

			try {
				param.append(key).append("=").append(URLEncoder.encode(params.get(key).toString(), "UTF-8"));
			} catch (Exception e) {
				log.error("拼接查询参数失败:", e);
			}

			i++;
		}
		apiUrl += param;
		log.debug("请求地址：" + apiUrl);
		String result = null;
		HttpClient httpClient = null;
		if (apiUrl.startsWith("https")) {

		} else {
			httpClient = HttpClients.createDefault();
		}
		try {
			HttpGet httpGet = new HttpGet(apiUrl);
			// 添加请求头
			for (Map.Entry<String, Object> entry : headers.entrySet()) {
				httpGet.setHeader(entry.getKey(), entry.getValue().toString());
			}
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = IOUtils.toString(instream, "UTF-8");
			}

		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		stopWatch.stop();
		log.info(stopWatch.prettyPrint());
		return result;
	}

	@GetMapping("/api/start")
	public void start() throws InterruptedException {
		this.flag = true;
		while (flag) {
			String url = environment.getProperty("test.url");
			String param = environment.getProperty("test.param");
			String value = environment.getProperty("test.value");
			String sleep = environment.getProperty("test.sleep");

			Map<String, Object> headers = new HashMap<>();
			Map<String, Object> params = new HashMap<>();
			if (!StringUtils.isEmpty(param)) {
				params.put(param, value);
			}
			String doGet = HttpController.doGet(url, headers, params);
			log.info(doGet);
			TimeUnit.MILLISECONDS.sleep(Long.parseLong(sleep));
		}

	}

	@GetMapping("/api/stop")
	public void stop() {
		this.flag = false;
	}

}