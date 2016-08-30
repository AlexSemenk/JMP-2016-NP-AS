package com.epam.jmp.troubleshooting;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadlockWithFourSharedResources {

	public static void main(String[] args) {
		Lock lock1 = new ReentrantLock();
		Lock lock2 = new ReentrantLock();
		Lock lock3 = new ReentrantLock();
		Lock lock4 = new ReentrantLock();
		new Thread(() -> {
			lock1.lock();
			println("acquired lock 1");
			trySleep(300);
			println("acquiring lock 2");
			lock2.lock();
			println("acquired lock 2");
			lock1.unlock();
			lock2.unlock();
		}).start();
		new Thread(() -> {
			lock2.lock();
			println("acquired lock 2");
			trySleep(300);
			println("acquiring lock 3");
			lock3.lock();
			println("acquired lock 3");
			lock2.unlock();
			lock3.unlock();
		}).start();
		new Thread(() -> {
			lock3.lock();
			println("acquired lock 3");
			trySleep(300);
			println("acquiring lock 4");
			lock4.lock();
			println("acquired lock 4");
			lock3.unlock();
			lock4.unlock();
		}).start();
		new Thread(() -> {
			lock4.lock();
			println("acquired lock 4");
			trySleep(300);
			println("acquiring lock 1");
			lock1.lock();
			println("acquired lock 1");
			lock4.unlock();
			lock1.unlock();
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
