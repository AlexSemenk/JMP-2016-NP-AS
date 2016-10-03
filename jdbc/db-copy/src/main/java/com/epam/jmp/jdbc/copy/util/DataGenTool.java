package com.epam.jmp.jdbc.copy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import com.epam.jmp.jdbc.copy.entity.Column;
import com.epam.jmp.jdbc.copy.entity.Table;

public class DataGenTool {

	private static final int BATCH_SIZE = 100;
	
	private String url;
	private String username;
	private String password;
	
	public DataGenTool(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public void fillDatabase() throws SQLException {
		try (Connection connection = getConnection();) {
			DbMetaDao dbMetaDao = new DbMetaDao(connection);
			List<String> tableNames = dbMetaDao.getDbTableNames();
			Collections.sort(tableNames);
			for(String tableName : tableNames) {
				Table table = dbMetaDao.getTable(tableName);
				fillTable(connection, table, 10000);
			}
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void fillTable(Connection connection, Table table, int size) throws SQLException {
		int tupleSize = table.getColumns().size();
		int[] types = getTypeCode(table.getColumns());
		Generator<Object[]> generator = Generator.forTable(table);
		String sql = SqlBuilder.getInsertSql(table);
		try (PreparedStatement statement = connection.prepareStatement(sql)) {
			for (int i=0; i<size;) {
				for (int j=0; j<BATCH_SIZE && i<size; j++, i++) {
					Object[] tuple = generator.next();
					for(int k=0; k<tupleSize; k++) {
						statement.setObject(k+1, tuple[k], types[k]);
					}
					statement.addBatch();
				}
				statement.executeBatch();
			}
		}
	}
	
	private int[] getTypeCode(List<Column> columns) {
		int[] codes = new int[columns.size()];
		int i = 0;
		for(Column column: columns) {
			codes[i++] = column.getTypeInfo().getJdbcTypeCode();
		}
		return codes;
	}
	
	private Connection getConnection() throws SQLException {
		Properties info = new Properties();
		info.setProperty("user", username);
		info.setProperty("password", password);
		return DriverManager.getConnection(url, info);
	}
	
}