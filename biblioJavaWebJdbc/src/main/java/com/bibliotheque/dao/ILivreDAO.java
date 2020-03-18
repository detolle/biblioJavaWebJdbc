package com.bibliotheque.dao;

import java.sql.SQLException;
import java.util.List;

import com.bibliotheque.entity.Livre;

public interface ILivreDAO {
	public Livre findByKey(String isbn) throws SQLException;
	public List<Livre> findAll() throws SQLException;
//	public Boolean update(Livre livre) throws SQLException;
//	public Boolean insert(Livre livre) throws SQLException;
//	public Boolean delete(String isbn) throws SQLException;
}
