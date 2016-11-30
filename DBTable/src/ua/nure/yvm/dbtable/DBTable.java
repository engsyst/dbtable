package ua.nure.yvm.dbtable;

import java.util.Collection;

public interface DBTable<T> {
	/**
	 * Add a object to table 
	 * 
	 * @param object
	 * @return id of object
	 * @throws DAOException 
	 */
	public int insert(T item) throws DAOException;

	/**
	 * Delete object from table 
	 * 
	 * @param object
	 * @return
	 * @throws DAOException 
	 */
	public T delete(int id) throws DAOException;
	
	/**
	 * Delete all instance of object from table 
	 * 
	 * @param object
	 * @return
	 * @throws DAOException 
	 */
	public void delete(T item, Filter filter) throws DAOException;
	
	/**
	 * Add a object to table 
	 * 
	 * @param object
	 * @return
	 * @throws DAOException 
	 */
	public boolean update(int id, T item) throws DAOException;
	
	/**
	 * Find object with patter in the table 
	 * 
	 * @param name
	 * @return
	 */
	public Collection<T> filter(String pattern, Filter filter);

	/**
	 * 
	 * @return All objects from table
	 */
	public Collection<T> selectAll();

	public T get(int id) throws DAOException;

}
