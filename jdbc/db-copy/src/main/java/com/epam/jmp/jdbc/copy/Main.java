package com.epam.jmp.jdbc.copy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		if (args.length < 1) {
			System.out.println("Source Database UEL is Requred");
		}
		if (args.length < 2) {
			System.out.println("Target Database URL is Requred");
		}
		String srcUrl = args[0];
		String dstUrl = args[1];
		try (Connection srcConnection = DriverManager.getConnection(srcUrl);
			 Connection dstConnection = DriverManager.getConnection(dstUrl);) {
			
		}
	}
	
}
