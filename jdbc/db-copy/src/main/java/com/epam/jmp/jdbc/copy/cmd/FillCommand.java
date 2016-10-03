package com.epam.jmp.jdbc.copy.cmd;

import java.sql.SQLException;

import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.epam.jmp.jdbc.copy.util.DataGenTool;

/**
 * Example:
 * fill -url jdbc:mysql://localhost:3306/task2_src -user root -password pass
 */
@Parameters(commandDescription = "Fill database tables with data")
public class FillCommand implements Command {

	@ParametersDelegate
	private ConnectionProperties connection = new ConnectionProperties();
		
	public void execute() {
		System.out.println("Table Filling is Started...");
		String url = connection.getUrl();
		String user = connection.getUser();
		String password = connection.getPassword();
		DataGenTool tool = new DataGenTool(url, user, password);
		try {
			tool.fillDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Copying is Failed!");
			return;
		}
		System.out.println("Table Filling is Finished!");
	}

}
