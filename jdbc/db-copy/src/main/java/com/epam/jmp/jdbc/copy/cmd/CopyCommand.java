package com.epam.jmp.jdbc.copy.cmd;

import java.sql.SQLException;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.ParametersDelegate;
import com.epam.jmp.jdbc.copy.util.DataCopyTool;

/**
 * Example:
 * copy -url jdbc:mysql://localhost:3306/ -src task2_src -trg task2_trg -user root -password pass
 */
@Parameters(commandDescription = "Copy database tables with data")
public class CopyCommand implements Command {
	
	@ParametersDelegate
	private ConnectionProperties connection = new ConnectionProperties();

	@Parameter(names = "-src", description = "Source database", required = true)
	private String srcDb;

	@Parameter(names = "-trg", description = "Target database", required = true)
	private String trgDb;

	public void execute() {
		System.out.println("Copying is Started...");
		String url = connection.getUrl();
		String user = connection.getUser();
		String password = connection.getPassword();
		DataCopyTool tool = new DataCopyTool(url, user, password, srcDb, trgDb);
		try {
			tool.copyDatabase();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Copying is Failed!");
			return;
		}
		System.out.println("Copying is Finished!");
	}

}