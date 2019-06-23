package com.example.designmodel.Observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @ClassName：ObserverJeb
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2019-06-22 21:59
 */
public class ObserverJeb2 implements Observer {


	/**
	 * This method is called whenever the observed object is changed. An
	 * application calls an <tt>Observable</tt> object's
	 * <code>notifyObservers</code> method to have all the object's
	 * observers notified of the change.
	 *
	 * @param o   the observable object.
	 * @param arg an argument passed to the <code>notifyObservers</code>
	 */
	@Override
	public void update(Observable o, Object arg) {
		System.out.println("ObserverJeb2 收到change事件:"+arg);
	}



}
