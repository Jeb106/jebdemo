package com.example.moudle.collection;

/**
 * @author jinBiaoHu
 * @date 2019-02-21 22:19
 */
public class HashMapTest {
	public static void main(String[] args) {
		ExtHashMap<String, String> map = new ExtHashMap<>();
		map.put("1号","jeb");
		map.put("2号","jeb");
		map.put("3号","jeb");
		map.put("4号","jeb");
		map.put("14号","jeb14");
		map.print();
		System.out.println(map.get("号"));

	}
}
