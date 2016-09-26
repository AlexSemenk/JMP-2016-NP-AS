package com.epam.jmp.jdbc.copy;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class DbMetaDao {

	private static final Logger LOG = Logger.getLogger(DbMetaDao.class);
	
	private Connection connection;
	private DatabaseMetaData dbMetadata;

	public DbMetaDao(Connection connection) {
		this.connection = connection;
	}

	public List<Table> getDbTables() throws SQLException {
		List<String> tableNames = getDbTableNames();
		ArrayList<Table> tables = new ArrayList<>(tableNames.size());
		for(String tableName : tableNames) {
			tables.add(getTable(tableName));
		}
		return tables;
	}
	
	public List<String> getDbTableNames() throws SQLException {
		LinkedList<String> tableNames = new LinkedList<>();
		try (ResultSet tables = databaseMetaData().getTables(null, null, "", new String[] { "TABLE" })) {
			while (tables.next()) {
				tableNames.add(tables.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			throw e;
		}
		return tableNames;
	}

	public Table getTable(String tableName) throws SQLException {
		Table table = new Table();
		table.setName(tableName);
		table.setColumns(getTableColumns(tableName));
		return table;
	}

	public void createTable(Table table) throws SQLException {
		StringBuilder sqlBuilder = new StringBuilder(16 * (table.getColumns().size() + 1));
		sqlBuilder.append("CREATE TABLE ").append(table.getName()).append("(");
		List<Column> columns = table.getColumns();
		Iterator<Column> itertator = columns.iterator();
		Column column = itertator.next();
		sqlBuilder.append(column.getName()).append(" ").append(column.getType());
		if (column.getType().equalsIgnoreCase("VARCHAR")) {
			sqlBuilder.append("(").append(column.getType()).append(")");
		}
		while(itertator.hasNext()) {
			column = itertator.next();
			sqlBuilder.append(",");
			sqlBuilder.append(column.getName()).append(" ").append(column.getType());
			if (column.getType().equalsIgnoreCase("VARCHAR")) {
				sqlBuilder.append("(").append(column.getSize()).append(")");
			}
		}
		sqlBuilder.append(")");
		String sql = sqlBuilder.toString();
		execute(sql);
	}

	public void dropTable(String tableName) throws SQLException {
		execute("DROP TABLE " + tableName);
	}
	
	private boolean execute(String sql) throws SQLException {
		try (Statement statement = connection.createStatement()) {
			LOG.info("[SQL]: " + sql);
			return statement.execute(sql);
		}
	}
	
	public List<Column> getTableColumns(String tableName) throws SQLException {
		List<Column> columns = new LinkedList<>();
		try (ResultSet result = databaseMetaData().getColumns(null, null, tableName, "")) {
			while (result.next()) {
				String name = result.getString("COLUMN_NAME");
				String type = result.getString("TYPE_NAME");
				int size = result.getInt("COLUMN_SIZE");
				columns.add(new Column(name, type, size));
			}
			return columns;
		} catch (SQLException e) {
			throw e;
		}
	}

	private DatabaseMetaData databaseMetaData() throws SQLException {
		if (dbMetadata == null) {
			dbMetadata = connection.getMetaData();
		}
		return dbMetadata;
	}

}
