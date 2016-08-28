package com.epam.jmp.troubleshooting;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleDeadlock {

	public static void main(String[] args) {
		Lock lock1 = new ReentrantLock();
		Lock lock2 = new ReentrantLock();
		new Thread(() -> {
			lock1.lock();
			println("acquired lock 1");
			trySleep(300);
			lock2.lock();
			println("acquired lock 2");
			lock1.unlock();
			println("relesed lock 1");
			lock2.unlock();
			println("relesed lock 2");
		}).start();
		new Thread(() -> {
			lock2.lock();
			println("acquired lock 2");
			trySleep(300);
			lock1.lock();
			println("acquired lock 1");
			lock2.unlock();
			println("relesed lock 2");
			lock1.unlock();
			println("relesed lock 1");
		}).start();
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
