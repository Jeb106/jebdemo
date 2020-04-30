package com.jeb.https.rest.test;


import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;

/**
 * @ClassName：Test
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2020-02-26 15:13
 * 使用说明：
 *  token:过一段时间会过期 需要重新获取
 *  param: 必须是json格式字符串
 *  header:[{"name":"token","value":"111"}] 必须是json格式字符串，name:header参数对名字，value:header的值
 *  HttpUtils.doPost(url,param,header)  : 里面三个参数可以定义成变量，也可以直接写在调用方法中，如果批量调用，param 参数采用下面这种写法
 *  HttpUtils.doPost(url,"{\"page\":1,\"pageSize\":20,\"conditions\":{\"id\": \"0006af4e06ff4baaa57b533b9151197f\"}}",header);
 */
public class Test {
	public static void main(String[] args) {
		/**
		 * 下面三行用来设置日志级别，只需要写一次，请务删除
		 */
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.simplelog.defaultlog", "error");
		PropertyConfigurator.configure( "/Users/hujinbiao/workspace/IdeaProjects/demo/demohttps/src/main/resources/log4j.properties" );

		/**获取token:只需要执行一次即可：多一段时间会过期 需要在重新执行一次  需要的时候放开注释
		String tokenUrl = "https://2.211.38.98:8343/v1/apigw/oauth2/token";
		//根据实际情况填写
		String appKey =  "";
		String secretKey = "";
		HttpUtils.getToken(tokenUrl,appKey,secretKey);
		 */

		/**下面测试接口 */
		String url = "https://192.168.20.42/insightview/styw/listResultInfo";
		String param="";
		String header="";
//		String param="{\"page\":1,\"pageSize\":20,\"conditions\":{\"id\": \"0006af4e06ff4baaa57b533b9151197f\"}}";
//		String header="[{\"name\":\"x-auth-token\",\"value\":\"49B53928DE0B4311B7BA767F87CD07B0\"}]";



	//	HttpUtils.doGet("http://192.168.90.42:8085/getTenDataInfo?size=10","");
		HttpUtils.doPostSsl(url,param,header);

	}
}
