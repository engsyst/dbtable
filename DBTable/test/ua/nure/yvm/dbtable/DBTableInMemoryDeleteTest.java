package ua.nure.yvm.dbtable;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBTableInMemoryDeleteTest {

	DBTable<Integer> dao = DBTableFabrique.instance();
	static List<Integer> in;
	static List<Integer> res;
	static final int MAX_COUNT = 10;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		in = new ArrayList<Integer>();
		for (int i = 0; i < MAX_COUNT; i++) {
			in.add(i);
		}
		res = new ArrayList<Integer>();
		for (int i = 0; i < MAX_COUNT/2; i++) {
			res.add(i);
		}
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		dao.clear();
		for (Integer integer : in) {
			dao.insert(integer);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDelete() throws DAOException {
		for (int i = 1; i < MAX_COUNT +1; i++) {
			dao.delete(i);
		}
		assertEquals(0, dao.size());
	}

	@Test(expected = DAOException.class)
	public void testDeleteNull() throws DAOException {
		dao.clear();
		dao.delete(1);
	}

}
