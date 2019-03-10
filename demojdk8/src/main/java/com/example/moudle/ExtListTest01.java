package com.example.moudle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author jinBiaoHu
 * @date 2019-02-18 22:13
 */
public class ExtListTest01 {

	public static void main(String[] args) {
		//syscopy();
		//arraysCopy();
		List<String> list = new ArrayList<>(1);
		list.add("s");
		list.add("ddb");
		list.add("ff");
	list.remove(1);
		//sort();
	}
	public static void sort(){
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return 0;
			}
		};
//		int[] intlist = {3,7,2,4,8,0};
		List<Integer> integers = new ArrayList<>();
		integers.add(3);
		integers.add(9);
		integers.add(8);
		integers.add(5);
		integers.add(2);
		integers.sort((o1,o2)-> o1>o2?1:-1);
		for (int i = 0; i < integers.size(); i++) {
			System.out.print(integers.get(i)+"_");
		}

		List<String> strList = new ArrayList<>();
		strList.add("ff");
		strList.add("bb");
		strList.add("cc");
		strList.add("jj");
		strList.sort(String::compareTo);
		for (int i = 0; i < strList.size(); i++) {
			System.out.print(strList.get(i)+"_");
		}
/*
		Collections.sort(strList);
		for (int i = 0; i < strList.size(); i++) {
			System.out.print(strList.get(i)+"_");
		}*/
	}
	public  static  void arraysCopy(){
		String[] src = { "0", "1", "2", "3", "4", "5" };
		String[] copyOf = Arrays.copyOf(src, 10);
		for (int i = 0; i < copyOf.length; i++) {
			System.out.println(copyOf[i]+"   ");
		}


	}


	public static void  syscopy(){
		String[] src = {"0","1","2","3","4","5"};
		String[] desc = {"6","7","8","9","10","11"};
		System.arraycopy(src,0,desc,2,3);
		for (int i = 0; i < desc.length; i++) {
			System.out.println(desc[i]+"   ");
		}
	}

}
