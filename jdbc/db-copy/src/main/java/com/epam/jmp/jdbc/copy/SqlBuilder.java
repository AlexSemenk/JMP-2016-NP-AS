package com.epam.jmp.jdbc.copy;

import java.util.Iterator;
import java.util.List;

public class SqlBuilder {

	public static String getInsertSql(Table table) {
		int columnsNumber = table.getColumns().size();
		StringBuilder builder = new StringBuilder(20 + 10 * columnsNumber);
		builder.append("INSERT INTO ").append(table.getName());
		builder.append("(").append(getColumnList(table)).append(")");
		builder.append("VALUES(").append("?");
		for(int i=1; i < columnsNumber; i++) {
			builder.append(",?");
		}
		builder.append(")");
		return builder.toString();
	}
	
	public static String getColumnList(Table table) {
		List<Column> columns = table.getColumns();
		StringBuilder columnListBuilder = new StringBuilder(8 * columns.size());
		Iterator<Column> iterator = columns.iterator();
		columnListBuilder.append(iterator.next().getName());
		while (iterator.hasNext()) {
			columnListBuilder.append(",").append(iterator.next().getName());
		}
		return columnListBuilder.toString();
	}
	
}
