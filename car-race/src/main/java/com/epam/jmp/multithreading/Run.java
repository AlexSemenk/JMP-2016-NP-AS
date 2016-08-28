package com.epam.jmp.multithreading;

import java.util.concurrent.TimeUnit;

public class Run {

	private static final Car[] cars = new Car[] {
			new Car("McLaren", 5),
			new Car("Ferrari", 6),
			new Car("Porsche", 7),
			new Car("Maserati", 10),
			new Car("Mercedes", 15),
			new Car("Lamborghini", 20),
			new Car("Honda", 30)
		};
	
	public static void main(String[] args) throws InterruptedException {
		Race race = new Race(cars);
		race.start();
		TimeUnit.SECONDS.sleep(1);
		race.disqualify(6);
		race.await();
		System.out.println("Winner is " + race.getWinner() + "!");
	}
	
}
