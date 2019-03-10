package com.example.designmodel.proxy.mayi;

/**
 * @author jinBiaoHu
 * @date 2019-02-12 16:20
 */
public class UserDaoImpl implements IUserDao {
	@Override
	public void add(String name) {
		System.out.println("============add:"+name+"============");
	}
}
