package com.epam.jmp.troubleshooting;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RealisticDeadlock {

	public static void main(String[] args) {
		Lock lock1 = new ReentrantLock();
		Lock lock2 = new ReentrantLock();
		new Thread(() -> {
			println("acquiring lock 1");
			lock1.lock();
			println("acquired lock 1");
			trySleep(300);
			println("acquiring lock 2");
			lock2.lock();
			lock1.unlock();
			lock2.unlock();
		}).start();
		new Thread(() -> {
			println("acquiring lock 2");
			lock2.lock();
			println("acquired lock 2");
			trySleep(300);
			println("acquiring lock 1");
			lock1.lock();
			lock2.unlock();
			lock1.unlock();
		}).start();
		for(int i=0; i<3; i++) {
			new Thread(() -> {
				trySleep(300);
				println("acquiring lock 1");
				lock1.lock();
			}).start();
			new Thread(() -> {
				trySleep(300);
				println("acquiring lock 2");
				lock2.lock();
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
