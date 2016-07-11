package com.epam.oakdb;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import java.util.Collection;

import org.junit.Test;

public class OakDbTest {

	@Test(expected = OakDbManager.OakDbAlreadyExistException.class)
	public void createDbDiplicat() {
		OakDbManager.createDb("TwinDB", Integer.class);
		OakDbManager.createDb("TwinDB", Integer.class);
	}
	
	@Test
	public void getNewDb() {
		OakDb<Integer> newDb = OakDbManager.createDb("NewDB", Integer.class);
		OakDb<Integer> retDb = OakDbManager.getDb("NewDB", Integer.class);
	    assertSame(newDb, retDb);
	}
	
	@Test(expected = OakDbManager.IncorrectOakDbType.class)
	public void getDbWithIncorrectType() {
		OakDbManager.createDb("IntegerDB", Integer.class);
		OakDbManager.getDb("IntegerDB", Long.class);
	}
	
	@Test
	public void selectDataFromDb() {
		OakDb<Integer> db = OakDbManager.createDb("IntegerDB2", Integer.class);
		db.insert(1, 2, 3, 4, 5);
		Collection<Integer> result = db.selectWhere(i -> i % 2 == 0);
		assertThat(result, hasSize(2));
		assertThat(result, contains(2, 4));
	}
	
	@Test
	public void selectAllWithConcurrentModification() {
		OakDb<Integer> db = OakDbManager.createDb("IntegerDB3", Integer.class);
		db.insert(1, 2, 3, 4, 5);
		Collection<Integer> result = db.selectAll();
		db.insert(6);
		assertThat(result, hasSize(5));
	}
	
}
