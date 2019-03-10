package com.example.designmodel.strategy;

/**
 * @author jinBiaoHu
 * @date 2019-02-12 22:41
 */
abstract class Stragegy {
	abstract void algorithmInterface();
}

class StragegyA extends Stragegy {

	@Override
	void algorithmInterface() {
		System.out.println("算法A");
	}
}

class StragegyB extends Stragegy {

	@Override
	void algorithmInterface() {
		System.out.println("算法B");
	}
}

class StragegyC extends Stragegy {

	@Override
	void algorithmInterface() {
		System.out.println("算法C");
	}
}

/**
 * 上下文
 */
class Context {
	private Stragegy stragegy;

	public Context(Stragegy stragegy) {
		this.stragegy = stragegy;
	}
	public void algorithmInterface() {
		stragegy.algorithmInterface();
	}


}

public class StragetyTest {
	public static void main(String[] args) {
		StragegyC stragegyC = new StragegyC();
		Context context = new Context(stragegyC);
		context.algorithmInterface();
	}
}
