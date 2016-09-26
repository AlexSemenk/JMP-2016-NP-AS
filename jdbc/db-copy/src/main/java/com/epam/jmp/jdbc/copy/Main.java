package com.epam.jmp.jdbc.copy;

import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException {
		DbCopyTool tool = new DbCopyTool("jdbc:mysql://localhost:3306/", "root", "pass", "task2_src", "task2_dst");
		tool.copyDatabase();
	}
	
}
