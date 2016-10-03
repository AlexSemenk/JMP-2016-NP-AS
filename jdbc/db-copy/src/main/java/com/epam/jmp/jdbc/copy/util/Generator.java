package com.epam.jmp.jdbc.copy.util;

import java.sql.Types;
import java.util.List;

import com.epam.jmp.jdbc.copy.entity.Column;
import com.epam.jmp.jdbc.copy.entity.Table;

@FunctionalInterface
public interface Generator<T> {

	T next();
	
	@SuppressWarnings("unchecked")
	public static Generator<Object[]> forTable(Table table) {
		List<Column> columns = table.getColumns();
		final int tupleSize = columns.size();
		Generator<Object>[] generators = new Generator[tupleSize];
		int idx = 0;
		for(Column column : columns) {
			int typeCode = column.getTypeInfo().getJdbcTypeCode();
			int typeSize = column.getSize();
			generators[idx++] = Generator.forType(typeCode, typeSize);
		}
		return () -> {
			Object[] tuple = new Object[tupleSize];
			for(int i=0; i<tupleSize; i++) {
				tuple[i] = generators[i].next();
			}
			return tuple;
		};
	}
	
	public static Generator<Object> forType(int typeCode, int typeSize) {
		SqlRandom random = new SqlRandom();
		switch (typeCode) {
		case Types.BIGINT:
			return () -> random.nextBigint();
		case Types.INTEGER:
			return () -> random.nextInt();
		case Types.SMALLINT:
			return () -> random.nextSmallInt();
		case Types.TINYINT:
			return () -> random.nextTinyInt();
		case Types.TIMESTAMP:
			return () -> random.nextTimestamp();
		case Types.DATE:
			return () -> random.nextDate();
		case Types.VARCHAR:
			return () -> random.nextVarchar(typeSize);
		default:
			return () -> null;
		}
	}
	
}
