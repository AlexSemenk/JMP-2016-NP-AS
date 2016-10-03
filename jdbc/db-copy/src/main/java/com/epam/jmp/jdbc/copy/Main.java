package com.epam.jmp.jdbc.copy;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.beust.jcommander.JCommander;
import com.epam.jmp.jdbc.copy.cmd.Command;
import com.epam.jmp.jdbc.copy.cmd.CopyCommand;
import com.epam.jmp.jdbc.copy.cmd.FillCommand;

public class Main {

	private static Map<String, Command> commands = new HashMap<>();
	static {
		commands.put("copy", new CopyCommand());
		commands.put("fill", new FillCommand());
	}
	
	public static void main(String[] args) throws SQLException {
		JCommander jc = new JCommander(new Main());
		for(Map.Entry<String, Command> entry : commands.entrySet()) {
			jc.addCommand(entry.getKey(), entry.getValue());
		}
		jc.parse(args);
		String command = jc.getParsedCommand();
		if (command == null) {
			jc.usage();
		} else {
			commands.get(command).execute();
		}
	}
	
}
