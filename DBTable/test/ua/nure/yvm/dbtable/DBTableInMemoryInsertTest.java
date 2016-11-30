package ua.nure.yvm.dbtable;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBTableInMemoryInsertTest {

	DBTable<Integer> dao = DBTableFabrique.instance();
	static List<Integer> in;
	static final int MAX_COUNT = 10;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		in = new ArrayList<Integer>();
		for (int i = 0; i < MAX_COUNT; i++) {
			in.add(i);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInsert() {
		for (Integer i : in) {
			try {
				dao.insert(i);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		Collection<Integer> c = dao.selectAll();
		for (Integer i : in) {
			assertTrue(c.contains(i));
		}
	}

	@Test
	public void testInsertGet() throws DAOException {
		dao.clear();
		dao.insert(1);
		assertEquals(new Integer(1), dao.get(1));
	}
	
	@Test(expected = DAOException.class)
	public void testInsertGetNotExisted() throws DAOException {
		dao.clear();
		dao.insert(1);
		dao.get(0);
	}
	
	@Test(expected = DAOException.class)
	public void testInsertWithNull() throws DAOException {
		for (Integer i : in) {
			dao.insert(i);
		}
		dao.insert(null, null);
	}

}
