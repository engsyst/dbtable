package ua.nure.yvm.dbtable;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBTableInMemoryUpdateTest {

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
		dao.clear();
		for (Integer integer : in) {
			dao.insert(integer);
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUpdate() throws SQLException  {
		dao.update(1, new Integer(20));
		assertEquals(new Integer(20), dao.get(1));
	}

	@Test(expected = NullPointerException.class)
	public void testUpdateWithNull() throws SQLException {
		dao.update(1, null);
	}
	
	@Test(expected = SQLException.class)
	public void testUpdateNotExisted() throws SQLException {
		dao.update(30, 1);
	}

}
