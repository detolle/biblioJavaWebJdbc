package com.bibliotheque.dao;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
	public T findByKey(Integer id) throws SQLException;
	public List<T> findAll() throws SQLException;
	public Boolean update(T t) throws SQLException;
	public Boolean insert(T t) throws SQLException;
	public Boolean delete(Integer id) throws SQLException;    

//  Optional<T> get(long id);
//  List<T> getAll();
//  void save(T t);
//  void update(T t, String[]params);
//  void delete(T t);
	
//	return Optional.ofNullable(returnValue)
	
//	Optional<User> user = userDao.get(id);
//  return user.orElseGet( () -> new User("non-existing user", "no-email"));
}
