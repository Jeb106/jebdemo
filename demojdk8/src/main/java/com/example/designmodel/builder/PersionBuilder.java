package com.example.designmodel.builder;

/**
 * @author jinBiaoHu
 * @date 2019-02-12 18:11
 */
public interface PersionBuilder {
	void buildHead();
	void buildBody();
	void buildFoot();
	Persion  buildPersion();//组装对象
}
