package com.epam.jmp.jdbc.copy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.epam.jmp.jdbc.copy.entity.Column;
import com.epam.jmp.jdbc.copy.entity.Table;

public class DataCopyTool {

	private static final Logger LOG = Logger.getLogger(DbMetaDao.class);
	
	private final String url;
	private final String username;
	private final String password;
	private final String srcDb; 
	private final String trgDb;
	
	public DataCopyTool(String url, String user, String password, String srcDb, String trgDb) {
		this.url = url.endsWith("/") ? url : url + "/";
		this.username = user;
		this.password = password;
		this.srcDb = srcDb;
		this.trgDb = trgDb;
	}
	
	public void copyDatabase() throws SQLException {
		try (Connection srcConnection = getConnection(srcDb);
		     Connection trgConnection = getConnection(trgDb)) {
			DbMetaDao srcDbMetaDao = new DbMetaDao(srcConnection);
			DbMetaDao dstDbMetaDao = new DbMetaDao(trgConnection);
			List<String> tableNames = srcDbMetaDao.getDbTableNames();
			Collections.sort(tableNames);
			for(String tableName : tableNames) {
				Table table = srcDbMetaDao.getTable(tableName);
				dstDbMetaDao.createTable(table);
				copyTableData(srcConnection, table);
			}
		} catch (SQLException e) {
			throw e;
		}
	}
	
	public void copyTableData(Connection connection, Table table) throws SQLException {
		List<Column> columns = table.getColumns();
		StringBuilder columnListBuilder = new StringBuilder(8 * columns.size());
		Iterator<Column> iterator = columns.iterator();
		columnListBuilder.append(iterator.next().getName());
		while (iterator.hasNext()) {
			columnListBuilder.append(",").append(iterator.next().getName());
		}
		String columnsList = columnListBuilder.toString();
		StringBuilder sqlBuilder = new StringBuilder(100 + 2 * columnsList.length());
		sqlBuilder.append("INSERT INTO ").append(trgDb).append(".").append(table.getName());
		sqlBuilder.append("(").append(columnsList).append(")");
		sqlBuilder.append("SELECT ").append(columnsList).append(" ");
		sqlBuilder.append("FROM ").append(srcDb).append(".").append(table.getName());
		String sql = sqlBuilder.toString();
		execute(connection, sql);
	}
	
	private boolean execute(Connection connection, String sql) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			LOG.info("[SQL]: " + sql);
			return statement.execute(sql);
		}
	}
	
	private Connection getConnection(String database) throws SQLException {
		Properties info = new Properties();
		info.setProperty("user", username);
		info.setProperty("password", password);
		return DriverManager.getConnection(url + database, info);
	}
	
}
