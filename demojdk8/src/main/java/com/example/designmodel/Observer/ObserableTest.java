package com.example.designmodel.Observer;

/**
 * @ClassName：ObserableTest
 * @description：观察者模式
 * @author： huJb
 * @date：2019-06-22 22:07
 */
public class ObserableTest {
	public static void main(String[] args) {
		ObserableJeb obserableJeb = new ObserableJeb();
		obserableJeb.addObserver(new ObserverJeb());
		obserableJeb.addObserver(new ObserverJeb2());
		obserableJeb.setChanged();
		obserableJeb.notifyObservers("hello");
		obserableJeb.setChanged();
		obserableJeb.notifyObservers(1);

	}
}
