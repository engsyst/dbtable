package ua.nure.yvm.dbtable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import ua.nure.log.Log;

class DBTableInMemory<T> implements DBTable<T> {
	private ConcurrentHashMap<Integer, T> items = new ConcurrentHashMap<>();
	private int bookIndex;
	private static final Log log = Log.getInstance(Log.DEBUG, DBTableInMemory.class);

	@Override
	public int insert(T item) throws DAOException {
		if (item == null) {
			log.error("item is null");
			throw new DAOException("T can not be a null");
		}
		items.put(bookIndex, item);
		log.debug("T added -->" + item);
		return bookIndex;
	}

	@Override
	public T delete(int id) throws DAOException {
		T book = items.remove(id);
		log.debug("Removed --> " + book);
		return book;
	}

	@Override
	public void delete(T item, Filter filter) throws DAOException {
		for (Entry<Integer, T> entry : items.entrySet()) {
			if (filter.accept(item, entry.getValue())) {
				log.debug("Accepted to remove --> " + entry.getValue());
				boolean res = items.remove(entry.getKey(), entry.getValue());
				log.debug("IsRemoved --> " + res);
			}
		}
	}

	@Override
	public boolean update(int id, T item) throws DAOException {
		T o = items.get(id);
		if (o == null) {
			log.debug("Not found. Id --> " + id);
			throw new DAOException("Not found or count more then exist");
		}
		T res = items.put(id, item);
		log.debug("Not found. Id --> " + res);
		return true;
	}

	@Override
	public Collection<T> filter(String pattern, Filter filter) {
		ArrayList<T> found = new ArrayList<T>();
		for (T item : items.values()) {
			if (filter.accept(pattern, item)) {
				log.debug("Found --> " + item);
				found.add(item);
			}
		}
		return found;
	}

	@Override
	public Collection<T> selectAll() {
		return items.values();
	}

	@Override
	public T get(int id) throws DAOException {
		T b = items.get(id);
		if (b == null) {
			log.debug("Not found --> " + id);
			throw new DAOException("Not found id = " + id);
		}
		log.debug("Found --> " + id);
		return b;
	}

}