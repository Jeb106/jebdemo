package com.example.designmodel.prototype.clone;

/**
 * @author jinBiaoHu
 * @date 2019-02-12 22:10
 */
public class Client {
	public static void main(String[] args) throws CloneNotSupportedException {
		Book book1 = new Book();
		book1.setAge(10);
		book1.setTitle("标题1");
		book1.add("图片1");
		book1.showBook();
		Book book2 = (Book) book1.clone();
		book2.setAge(20);
		book2.setTitle("标题2");
		book2.add("图片2");
		book2.showBook();
		book1.showBook();
	}
}
