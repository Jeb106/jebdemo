package com.example.test.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件名：Test
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-16 18:09
 */
public class Test {
	public static void main(String[] args)  throws Exception{
		String content = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:def=\"DefaultNamespace\">\n" +
				"   <soapenv:Header>\n" +
				"      <tongtechheader>\n" +
				"         <!--Optional:-->\n" +
				"         <gjgxjhpt_rid>?</gjgxjhpt_rid>\n" +
				"         <!--Optional:-->\n" +
				"         <gjgxjhpt_sid>?</gjgxjhpt_sid>\n" +
				"         <!--Optional:-->\n" +
				"         <jsjhpt_sign>?</jsjhpt_sign>\n" +
				"      </tongtechheader>\n" +
				"   </soapenv:Header>\n" +
				"   <soapenv:Body>\n" +
				"      <def:getTenDataInfoWithHeadWebService>\n" +
				"         <!--Optional:-->\n" +
				"         <size>10</size>\n" +
				"         <!--Optional:-->\n" +
				"         <name>?</name>\n" +
				"      </def:getTenDataInfoWithHeadWebService>\n" +
				"   </soapenv:Body>\n" +
				"</soapenv:Envelope>";


		String label = "tongtechheader";
		String rgex = "<"+label+">(.*?)</"+label+">";
		Pattern p = Pattern.compile(rgex);
		//Pattern p = Pattern.compile("<(.*?)>");
		Matcher m = p.matcher(content);
		String headerName = "";
		if (m.find()) {
			headerName = m.group(1);
			System.out.println(headerName);
		}

//
//			String headerSrc = "<"+headerName +">?</"+headerName +"/>";
//		System.out.println(headerSrc);
//
//
//
//
//		String headers = "\t{\n" +
//				"\t\t\"jsjhpt_sid\":\"123\",\n" +
//				"\t\t\"jsjhpt_rid\":\"456\",\n" +
//				"\t\t\"jsjhpt_sign\":\"789\"\n" +
//				"\t}";
//
//		JSONObject jsonObject = JSON.parseObject(headers);
//		Iterator<String> iterator = jsonObject.keySet().iterator();
//
//		while (iterator.hasNext()) {
//			String arg = iterator.next();
//			System.out.println(arg);
//			String argtmp = jsonObject.getString(arg);
//			argtmp = argtmp.trim().replaceAll("	", "");
//			System.out.println(argtmp);
//			System.out.println("      ");
//
//			String oldHeader = arg + ">?";
//			String newHeader = arg + ">" + argtmp;
//
//			content = content.replace(oldHeader,newHeader);
//			System.out.println(content);
//
//		}
	}
}
