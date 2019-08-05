package com.example.refactor;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName：RefactorTest
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-08-03 09:01
 */
public class RefactorTest {

	public static void main(String[] args) {
		List<String> lists = new ArrayList<>();
		lists.add("s");
		getNew(lists);
		System.out.println(lists.size()+",after");
	}

	private static void getNew(List<String> lists) {
		//方法一：
		//lists = new ArrayList<>();
        //方法二：
		lists.add("bb");
		System.out.println(lists.size()+",before");
	}


}
