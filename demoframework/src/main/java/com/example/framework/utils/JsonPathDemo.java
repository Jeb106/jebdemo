package com.example.framework.utils;


import com.jayway.jsonpath.JsonPath;

import java.util.List;

/**
 * @ClassName：JsonPathDemo
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2020-07-20 09:23
 */
public class JsonPathDemo {

	public static void main(String[] args) {
		String jsonStr = "{\n" +
				"    \"store\": {\n" +
				"        \"book\": [\n" +
				"            {\n" +
				"                \"category\": \"reference\",\n" +
				"                \"author\": \"Nigel Rees\",\n" +
				"                \"title\": \"Sayings of the Century\",\n" +
				"                \"price\": 8.95\n" +
				"            },\n" +
				"            {\n" +
				"                \"category\": \"fiction\",\n" +
				"                \"author\": \"Evelyn Waugh\",\n" +
				"                \"title\": \"Sword of Honour\",\n" +
				"                \"price\": 12.99\n" +
				"            },\n" +
				"            {\n" +
				"                \"category\": \"fiction\",\n" +
				"                \"author\": \"Herman Melville\",\n" +
				"                \"title\": \"Moby Dick\",\n" +
				"                \"isbn\": \"0-553-21311-3\",\n" +
				"                \"price\": 8.99\n" +
				"            },\n" +
				"            {\n" +
				"                \"category\": \"fiction\",\n" +
				"                \"author\": \"J. R. R. Tolkien\",\n" +
				"                \"title\": \"The Lord of the Rings\",\n" +
				"                \"isbn\": \"0-395-19395-8\",\n" +
				"                \"price\": 22.99\n" +
				"            }\n" +
				"        ],\n" +
				"        \"bicycle\": {\n" +
				"            \"color\": \"red\",\n" +
				"            \"price\": 19.95\n" +
				"        }\n" +
				"    },\n" +
				"    \"expensive\": 10\n" +
				"}";

		List<String> authors = JsonPath.read(jsonStr, "$.store.book[*].author");
		System.out.println(authors);
		List<String> authors2 =  JsonPath.read(jsonStr, "$..author");
		System.out.println(authors2);
		List<String> authors3 =  JsonPath.read(jsonStr, "$.store.*");
		System.out.println(authors3);

		List<String> authors4 =  JsonPath.read(jsonStr, "$.store..price");
		System.out.println(authors4);
		List<String> authors5 =  JsonPath.read(jsonStr, "$..book[2]");
		System.out.println(authors5);
		List<String> authors6 =  JsonPath.read(jsonStr, "$.store.book[?(@.price < 10)]");
		System.out.println(authors6);


//
//
//
//
//		Object read = JsonPath.read(jsonStr, "$.store.book[0].author");
//		System.out.println(read);
//		System.out.println((char[]) JsonPath.read(jsonStr,"$..auther", (Predicate[]) null));
//
//		Object read1 = JsonPath.read(jsonStr, "$.store.*");
//		System.out.println(read1);
//		Object read2 = JsonPath.read(jsonStr, "$.store..price");
//		System.out.println(read2);





	}
}
