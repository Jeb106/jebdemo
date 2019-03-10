package com.example.designmodel.sington;

/**
 * @author jinBiaoHu
 * @date 2019-02-10 16:19
 */
public enum  HttpEnum {
	HTTP_200(200,"success"),HTTP_500(500,"false");

	private  Integer code;
	private String msg;
	HttpEnum(Integer code,String msg){
		this.code = code;
		this.msg = msg;
	}

	public static void main(String[] args) {

		System.out.println(HttpEnum.HTTP_200.msg);
		System.out.println(HttpEnum.HTTP_200.code);
		System.out.println(HttpEnum.HTTP_500.code);
		System.out.println(HttpEnum.HTTP_500.msg);
	}
}
