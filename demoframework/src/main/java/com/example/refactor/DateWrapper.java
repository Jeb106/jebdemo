package com.example.refactor;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName：DateWripper
 * @description： 日期包装类 需要为源类的每个方法建立一个包装函数，这样包装类就包含类源类的所有方法
 * @author： huJb
 * @date：2019-08-04 13:46
 */
@Data
public class DateWrapper {
	private Date originDate;

	public DateWrapper(String dateString) {
		originDate = new Date(dateString);
	}

	public DateWrapper(Date date) {
		originDate = date;
	}

	public int getYear() {
		return  originDate.getYear();
	}

	public Date getNextDay(){
		return new Date();
	}

	public static void main(String[] args) {
		DateWrapper dateWrapper = new DateWrapper(new Date());
		dateWrapper.getNextDay();
	}
}
