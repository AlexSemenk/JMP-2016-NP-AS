package com.epam.jmp.jdbc.copy.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.jmp.jdbc.copy.entity.Column;
import com.epam.jmp.jdbc.copy.entity.Table;
import com.epam.jmp.jdbc.copy.entity.TypeInfo;

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
		String columnName = column.getName();
		String columnType = column.getTypeInfo().getTypeName();
		sqlBuilder.append(columnName).append(" ").append(columnType);
		if (column.getTypeInfo().getJdbcTypeCode() == Types.VARCHAR) {
			sqlBuilder.append("(").append(column.getSize()).append(")");
		}
		while(itertator.hasNext()) {
			column = itertator.next();
			columnName = column.getName();
			columnType = column.getTypeInfo().getTypeName();
			sqlBuilder.append(",");
			sqlBuilder.append(columnName).append(" ").append(columnType);
			if (column.getTypeInfo().getJdbcTypeCode() == Types.VARCHAR) {
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
		try (ResultSet result = databaseMetaData().getColumns(null, null, tableName, "%")) {
			while (result.next()) {
				String name = result.getString("COLUMN_NAME");
				String typeName = result.getString("TYPE_NAME");
				int typeSize = result.getInt("COLUMN_SIZE");
				TypeInfo typeInfo = getTypeInfo(typeName);
				columns.add(new Column(name, typeInfo, typeSize));
			}
			return columns;
		}
	}
	
	public HashSet<TypeInfo> getTypeInfo() throws SQLException {
		HashSet<TypeInfo> typeInfoSet = new HashSet<>();
		try (ResultSet result = databaseMetaData().getTypeInfo()) {
			while(result.next()) {
				TypeInfo typeInfo = new TypeInfo();
				typeInfo.setTypeName(result.getString(1));
				typeInfo.setJdbcTypeCode(result.getInt(2));
				typeInfo.setPrecision(result.getInt(3));
				typeInfo.setLiteralPrefix(result.getString(4));
				typeInfo.setLiteralSuffix(result.getString(5));
				typeInfo.setCaseSensitive(result.getBoolean(8));
				typeInfo.setUnsigned(result.getBoolean(10));
				typeInfo.setFixedPrecisionScale(result.getBoolean(11));
				typeInfo.setAutoIncrement(result.getBoolean(12));
				typeInfo.setMinimumScale(result.getShort(14));
				typeInfo.setMaximumScale(result.getShort(15));
				typeInfoSet.add(typeInfo);
			}
		}
		return typeInfoSet;
	}
	
	private HashMap<String, TypeInfo> typeNameMap;
	
	private HashMap<String, TypeInfo> typeNameMap() throws SQLException {
		if (typeNameMap == null) {
			typeNameMap = new HashMap<>();
			for (TypeInfo typeInfo : getTypeInfo()) {
				typeNameMap.put(typeInfo.getTypeName(), typeInfo);
			}
		}
		return typeNameMap;
	}
	
	private TypeInfo getTypeInfo(String typeName) throws SQLException {
		return typeNameMap().get(typeName);
	}
	
	private DatabaseMetaData databaseMetaData() throws SQLException {
		if (dbMetadata == null) {
			dbMetadata = connection.getMetaData();
		}
		return dbMetadata;
	}

}
