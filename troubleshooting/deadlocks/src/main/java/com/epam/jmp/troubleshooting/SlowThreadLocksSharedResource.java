package com.epam.jmp.troubleshooting;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SlowThreadLocksSharedResource {

	public static void main(String[] args) {
		Lock lock = new ReentrantLock();
		new Thread(() -> {
			lock.lock();
			println("acquired lock");
			trySleep(TimeUnit.HOURS.toMillis(1));
			lock.unlock();
			println("released lock");
		}).start();
		for(int i=0; i<4; i++) {
			new Thread(() -> {
				trySleep(300);
				println("acquiring lock");
				lock.lock();
				println("acquired lock");
				lock.unlock();
			}).start();
		}
	}

	private static void trySleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void println(String message) {
		System.out.println(Thread.currentThread().getName() + ": " + message);
	}
	
}
