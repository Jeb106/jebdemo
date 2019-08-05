package com.example.refactor;

import java.util.Date;

/**
 * @ClassName：SubDate
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-08-04 14:15
 */
public class SubDate extends Date {

	public SubDate() {
	}
	public SubDate(String date) {
		super(date);
	}
	public Date getNextDay(){
		return new Date();
	}

	public static void main(String[] args) {
		SubDate subDate = new SubDate();
		subDate.getNextDay();
	}
}
