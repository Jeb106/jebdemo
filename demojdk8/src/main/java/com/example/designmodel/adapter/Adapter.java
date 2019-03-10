package com.example.designmodel.adapter;

/**
 * @author jinBiaoHu
 * @date 2019-02-12 20:59
 */
public class Adapter implements JP110VInterface {
	public CN220VInterface cn220VInterface;

	public Adapter(CN220VInterface cn220VInterface) {
		this.cn220VInterface = cn220VInterface;
	}

	@Override
	public void connection() {
		cn220VInterface.connection();
	}
}
