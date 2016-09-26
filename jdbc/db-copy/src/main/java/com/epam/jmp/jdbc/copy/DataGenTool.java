package com.epam.jmp.jdbc.copy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class DataGenTool {

	private static final int BATCH_SIZE = 100;
	
	@FunctionalInterface
	private interface ParameterSetter {
		public void set(PreparedStatement statement) throws SQLException;	
	}
	
	private class GroupParameterSetter implements ParameterSetter {
		private List<ParameterSetter> list = new LinkedList<>();
		public void addSetter(ParameterSetter setter) {
			list.add(setter);
		}
		public void set(PreparedStatement statement) throws SQLException {
			for(ParameterSetter setter : list) {
				setter.set(statement);
			}
		}
	}
	
	public void fillTable(Connection connection, Table table, int size) throws SQLException {
		String sql = SqlBuilder.getInsertSql(table);
		ParameterSetter setter = getColumnsSetter(table.getColumns());
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			for (int i=0; i<size;) {
				for (int j=0; j<BATCH_SIZE && i<size; j++, i++) {
					setter.set(statement);
					statement.addBatch();
				}
				statement.executeBatch();
			}
		}
	}
	
	public ParameterSetter getColumnsSetter(List<Column> columns) {
		GroupParameterSetter setter = new GroupParameterSetter();
		int c=1;
		for(Column column : columns) {
			final int fc = c;
			String type = column.getType();
			Random random = new Random();
			if ("BIGINT".equalsIgnoreCase(type)) {
				setter.addSetter((statement) -> {
					statement.setLong(fc, random.nextLong());
				});
			} else if ("INT".equalsIgnoreCase(type)) {
				setter.addSetter((statement) -> {
					statement.setInt(fc, random.nextInt());
				});
			} else if ("SMALLINT".equalsIgnoreCase(type)) {
				setter.addSetter((statement) -> {
					statement.setInt(fc, (Math.abs(random.nextInt()) % 0x8000));
				});
			} else if ("TINYINT".equalsIgnoreCase(type)) {
				setter.addSetter((statement) -> {
					statement.setInt(fc, (Math.abs(random.nextInt()) % 0x80));
				});
			} else if ("TIMESTAMP".equalsIgnoreCase(type)) {
				setter.addSetter((statement) -> {
					long millis = Math.abs(random.nextLong()) % System.currentTimeMillis();
					Instant instant = Instant.ofEpochMilli(millis);
					statement.setTimestamp(fc, java.sql.Timestamp.from(instant));
				});
			} else if ("DATE".equalsIgnoreCase(type)) {
				setter.addSetter((statement) -> {
					long millis = Math.abs(random.nextLong()) % System.currentTimeMillis();
					LocalDate localDate = LocalDate.ofEpochDay(millis);
					statement.setDate(fc, java.sql.Date.valueOf(localDate));
				});
			} else if ("VARCHAR".equalsIgnoreCase(type)) {
				setter.addSetter((statement) -> {
					StringBuilder builder = new StringBuilder();
					for(int i=0; i<column.getSize(); i++) {
						int irand = Math.abs(random.nextInt()) % ('z' - 'a' + 1) + 'a';
						builder.append((char)irand);
					}
					statement.setString(fc, builder.toString());
				});
			}
		}
		return setter;
	}
	
}
