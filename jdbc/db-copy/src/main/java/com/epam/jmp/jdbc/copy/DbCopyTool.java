package com.epam.jmp.jdbc.copy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

public class DbCopyTool {

	private static final Logger LOG = Logger.getLogger(DbMetaDao.class);
	
	private final String url;
	private final String username;
	private final String password;
	private final String srcDb; 
	private final String dstDb;
	
	DbCopyTool(String url, String user, String password, String srcDb, String dstDb) {
		this.url = url.endsWith("/") ? url : url + "/";
		this.username = user;
		this.password = password;
		this.srcDb = srcDb;
		this.dstDb = dstDb;
	}
	
	public void copyDatabase() throws SQLException {
		try (Connection srcConnection = getConnection(srcDb);
		     Connection dstConnection = getConnection(dstDb)) {
			DbMetaDao srcDbMetaDao = new DbMetaDao(srcConnection);
			DbMetaDao dstDbMetaDao = new DbMetaDao(dstConnection);
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
		sqlBuilder.append("INSERT INTO ").append(dstDb).append(".").append(table.getName());
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
		return DriverManager.getConnection(url + database, username, password);
	}
	
}
