package com.example.designmodel.builder;

/**
 * @author jinBiaoHu
 * @date 2019-02-12 18:12
 */
public class AmericaPersionBuilder implements PersionBuilder
{
	Persion persion;
	public  AmericaPersionBuilder(){
		persion = new Persion();
	}

	@Override
	public void buildHead() {
		persion.setHead("美国人 头部  鼻子大");
	}

	@Override
	public void buildBody() {
		persion.setBody("美国人 身体高");
	}

	@Override
	public void buildFoot() {
		persion.setFoot("美国人 尾部  腿长");
	}

	@Override
	public Persion buildPersion() {
		return persion;
	}
}
