package com.example.designmodel.builder;


/**
 * @author jinBiaoHu
 * @date 2019-02-12 18:15
 */
public class PersionDirector {

	public Persion buildPersion(PersionBuilder persionBuilder){
		persionBuilder.buildBody();
		persionBuilder.buildFoot();
		persionBuilder.buildHead();
		return   persionBuilder.buildPersion();

	}

	public static void main(String[] args) {
		PersionDirector persionDirector = new PersionDirector();
		Persion persion = persionDirector.buildPersion(new AmericaPersionBuilder());
		System.out.println(persion.getBody());

	}




}
