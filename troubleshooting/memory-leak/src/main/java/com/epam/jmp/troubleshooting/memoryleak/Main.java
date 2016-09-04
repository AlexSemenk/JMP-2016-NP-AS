package com.epam.jmp.troubleshooting.memoryleak;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		ArrayList<String> list = new ArrayList<String>();
		BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/data.txt"));
		String line = null;
		while ((line = reader.readLine()) != null) {
			list.add(line.substring(0, 3));
		}
		reader.close();
		TimeUnit.MINUTES.sleep(5);
	}

}
