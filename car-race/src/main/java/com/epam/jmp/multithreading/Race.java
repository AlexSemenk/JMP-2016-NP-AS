package com.epam.jmp.multithreading;

import java.util.concurrent.CountDownLatch;

public class Race {
	
	private class Driver extends Thread {

		private Car car;

		@Override
		public synchronized void run() {
			if (car != null) {
				car.run();
				if (!disqualified()) {
					trySetWinner(car.getName());
				}
				finish.countDown();
			}
		}
		
		public synchronized void setCar(Car task) {
			this.car = task;
		}
		
		public boolean disqualified() {
			return Thread.interrupted();
		}
		
	}

	private Driver[] drivers;
	private volatile String winner;
	private CountDownLatch finish;
	
	public Race(Car[] cars) {
		this.finish = new CountDownLatch(cars.length);
		this.drivers = new Driver[cars.length];
		for (int i = 0; i < cars.length; i++) {
			drivers[i] = new Driver();
			drivers[i].setCar(cars[i]);
		}
	}

	public void start() {
		for (Driver driver : drivers) {
			driver.start();
		}
	}
	
	public void disqualify(int i) {
		drivers[i].interrupt();
	}
	
	public void await() throws InterruptedException {
		finish.await();
	}
	
	public String getWinner() {
		return winner;
	}

	private void trySetWinner(String winner) {
		if (this.winner == null) {
			synchronized(Race.this) {
				if (this.winner == null) {
					this.winner = winner;
				}
			}
		}
	}
	
}