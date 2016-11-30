package ua.nure.yvm.dbtable;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DBTableInMemoryFilterTest {

	DBTable<Integer> dao = DBTableFabrique.instance();
	List<Integer> in;
	static final int MAX_COUNT = 10;
	
	Filter f  = new Filter() {
		@Override
		public boolean accept(Object pattern, Object item) {
			return Objects.equals(pattern, item);
		}
	};
	
	Collection<Integer> c1 = new ArrayList<>();
	Collection<Integer> c9 = new ArrayList<>();
	Collection<Integer> c7 = new ArrayList<>();
	Collection<Integer> c0 = new ArrayList<>();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	
	void setIn() {
		in = new ArrayList<Integer>();
		for (int i = 0; i < MAX_COUNT; i++) {
			in.add(i);
		}
	}
	
	void setDao() throws DAOException {
		dao.clear();
		dao.insert(in.toArray(new Integer[0]));
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFilter() throws DAOException {
		setIn();
		setDao();
		c1.add(new Integer(1));
		c9.add(new Integer(9));
		c7.add(new Integer(7));
		c0.add(new Integer(0));
		assertEquals("7", c7, dao.filter(7, f));
		assertEquals("1", c1, dao.filter(1, f));
		assertEquals("9", c9, dao.filter(9, f));
		assertEquals("0", c0, dao.filter(0, f));
	}

	@Test
	public void testDeleteFilter() throws DAOException {
		setIn();
		setDao();
		in.remove(new Integer(1));
		dao.delete(1, f);
		assertArrayEquals(in.toArray(), dao.selectAll().toArray());
	}

}
