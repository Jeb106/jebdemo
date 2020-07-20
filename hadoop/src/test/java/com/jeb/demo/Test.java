package com.jeb.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName：Test
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2020-07-02 10:25
 */
public class Test {
	public static void main(String[] args) {
		String context = "{\"IP\": \"172.16.20.16\", \"Port\": \"2121\", \"Model\": \"被动模式\", \"id\": \"#1781729747\"}";
		int msIndex = 0;
		Matcher matcher = Pattern.compile("\"").matcher(context);
		while (matcher.find()) {
			if (msIndex == 2) {
				break;
			}
			msIndex++;
		}
		// 打印匹配对对象对index
		System.out.println(matcher.start());
	}
}
