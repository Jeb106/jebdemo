package com.example.test.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 文件名：Test
 * 版权：Copyright © Fable Data Technology NanJing Co , Ltd.
 * 描述：〈一句话功能简述〉
 * 创建人： huJb
 * 创建时间：2019-03-16 18:09
 */
public class TestXml2 {

	public static List getContext(String html) {
		List resultList = new ArrayList();
		//包含外层节点
		//Pattern p = Pattern.compile("(<entry>(\\S|\\s)*?</entry>)");
		//不包含外层节点
		String key = "def:entry";
		String regeist = "<"+key+">((\\S|\\s)*?)</"+key+">";
		Pattern p = Pattern.compile(regeist);
	//	Pattern p = Pattern.compile("<entry[^>]*/>|>((\\S|\\s)*?)</entry>");
		Matcher m = p.matcher(html );//
		while (m.find()) {
			resultList.add(m.group(1));//
		}
		return resultList;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String a = "<def:entry> \n" +
				" <key>id</key> \n" +
				" <value>320283199407096911</value>  \n" +
				" </def:entry>";
		String b = "<def:entry> \n" +
				" <key>?</key> \n" +
				" <value>?</value>  \n" +
				" </def:entry>";

		List<String> alist = getContext(a);
		List<String> blist = getContext(b);
		System.out.println(alist.get(0));
		System.out.println(blist.get(0));
		String oldHeader = blist.get(0);
		String  newHeader = alist.get(0);

		String c = b.replace(oldHeader,newHeader);
		System.out.println(c);

	}
}
