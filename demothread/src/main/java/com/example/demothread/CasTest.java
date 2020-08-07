package com.example.demothread;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @ClassName：CasTest
 * @description：〈一句话功能简述〉
 * @author： huJb
 * @date：2020-08-06 19:59
 */
public class CasTest {
	public static void main(String[] args) {
		// 添加版本号 比慢ABA问题
		String name = "洲洋";
		String newName = "Java";
		AtomicStampedReference<String> as = new AtomicStampedReference<String>(name, 1);
		System.out.println("值：" + as.getReference() + " | Stamp：" + as.getStamp());
		as.compareAndSet(name, newName, as.getStamp(), as.getStamp() + 1);
		System.out.println("值：" + as.getReference() + " | Stamp：" + as.getStamp());
	}
}
