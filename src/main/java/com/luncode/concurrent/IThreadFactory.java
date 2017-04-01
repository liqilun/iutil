package com.luncode.concurrent;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 李启仑 创建时间：2017年4月1日 下午4:55:06 说明：
 */
public class IThreadFactory implements ThreadFactory {
	private final String namePrefix;
	private static AtomicInteger threadNumber = new AtomicInteger(1);

	public IThreadFactory(String namePrefix) {
		this.namePrefix = namePrefix;
	}

	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r, namePrefix + " thread-"
				+ threadNumber.getAndIncrement());
	}

	public static void main(String[] args) {
		IThreadFactory factory = new IThreadFactory("IThreadFactory");
		Task task = new Task();
		Thread thread = null;
		for (int i = 0; i < 10; i++) {
			thread = factory.newThread(task);
			thread.start();
		}
		System.out.printf("end");
	}
}

class Task implements Runnable {

	@Override
	public void run() {
		System.out.printf("hello world");
	}

}
