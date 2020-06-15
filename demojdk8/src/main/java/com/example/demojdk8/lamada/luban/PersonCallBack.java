package com.example.demojdk8.lamada.luban;

/**
 * @ClassName：PersonCallBack
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2020-05-01 19:19
 */
@FunctionalInterface
public  interface PersonCallBack<T> {

	void callBack(T t);
}
