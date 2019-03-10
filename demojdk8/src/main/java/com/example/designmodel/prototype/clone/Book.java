package com.example.designmodel.prototype.clone;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author jinBiaoHu
 * @date 2019-02-12 22:07
 */
@Data
public class Book implements  Cloneable {
	private String title;
	private  int  age;
	private ArrayList<String> imgs = new ArrayList<>();

	public void add(String img){
		imgs.add(img);
	}
	@Override
	protected Object clone() throws CloneNotSupportedException {
		//默认前拷贝 之拷贝基本数据类型  引用类型 共享
		Book book = (Book) super.clone();
		//book.imgs=(ArrayList<String>) book.imgs.clone(); //深拷贝
		return book;
	}

	public void showBook() {
		System.out.println("----------------------Start----------------------");

		System.out.println("title：" + title);
		for (String img : imgs) {
			System.out.println("image name:" + img);
		}
		System.out.println("age:"+age);
		System.out.println("----------------------End----------------------");
	}

}
