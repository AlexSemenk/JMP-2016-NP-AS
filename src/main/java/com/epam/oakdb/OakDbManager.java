package com.epam.oakdb;

import java.util.HashMap;
import java.util.Map;

class OakDbManager {

	static class OakDbAlreadyExistException extends RuntimeException {

		private static final long serialVersionUID = -3918412056502072524L;

		public OakDbAlreadyExistException(String dbName) {
			super(String.format("OakDB with name %s already exist", dbName));
		}
		
	}

	static class IncorrectOakDbType extends IllegalArgumentException {

		private static final long serialVersionUID = 7525982360153231276L;

		public IncorrectOakDbType(Class<?> type) {
			super(String.format("Incorrect Type %s for OakDb", type.getName()));
		}
		
	}
	
	private static class SingletonHolder {
		private static final OakDbManager INSTANCE = new OakDbManager();
	}

	private static class OakDbEntry<T> {
		public OakDb<T> db;
		public Class<T> type;
		public OakDbEntry(OakDb<T> db, Class<T> type) {
			this.db = db;
			this.type = type;
		}
	}
	
	private Map<String, OakDbEntry<?>> mapForOakDB;
	
	private OakDbManager() {
		this.mapForOakDB = new HashMap<>();
	}
		
	public static OakDbManager getInstance() {
		return SingletonHolder.INSTANCE;
	}

	public static <T> OakDb<T> createDb(String name, Class<T> type) {
		OakDbManager manager = getInstance();
		if(manager.mapForOakDB.containsKey(name)) {
			throw new OakDbAlreadyExistException(name);
		} else {
			OakDb<T> db = new OakDb<>(name, type);
			manager.mapForOakDB.put(name, new OakDbEntry<>(db, type));
			return db;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> OakDb<T> getDb(String name, Class<T> type) {
		OakDbEntry<T> entry = (OakDbEntry<T>)getInstance().mapForOakDB.get(name);
		if (entry == null) {
			return null;
		} else if (entry.type != type) {
			throw new IncorrectOakDbType(type);
		} else {
			return entry.db;
		}
	}
	
	public static void dropDb(String name) {
		OakDbEntry<?> dbEntry = getInstance().mapForOakDB.remove(name);
		if (dbEntry != null) {
			dbEntry.db.trancate();
		}
	}
	
}
