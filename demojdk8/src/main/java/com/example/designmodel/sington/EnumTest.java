package com.example.designmodel.sington;

/**
 * @author jinBiaoHu
 * @date 2019-02-10 16:35
 */
public enum  EnumTest {
	HTTP_200;

	private String getMsg(){
		return "success";
	}

	public static void main(String[] args) {

		System.out.println(EnumTest.HTTP_200.getMsg());
	}
}
