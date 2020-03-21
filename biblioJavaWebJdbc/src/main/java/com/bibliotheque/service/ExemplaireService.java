package com.bibliotheque.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque.dao.Dao;
import com.bibliotheque.dao.IExemplaireDAO;
import com.bibliotheque.dao.impl.ExemplaireDAO;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.util.DataSourceProvider;

public class ExemplaireService {
	private Connection conn;
	private IExemplaireDAO exemplaireDAO;
//	private Dao exemplaireDAO;

	public ExemplaireService() {
	}

	public Exemplaire findByKey(Integer id) {
		Exemplaire exemplaire=null;
		try {
			conn = DataSourceProvider.getdataSourceInstance().getConnection();
			conn.setAutoCommit(false);
			
			exemplaireDAO = new ExemplaireDAO(conn);
			exemplaire=exemplaireDAO.findByKey(id);
						
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException el) {
				el.printStackTrace();
			}
		} finally {

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exemplaire;
	}
	
	public List<Exemplaire> findAll() {
		List<Exemplaire> exemplaires = new ArrayList<>();
		try {
			conn = DataSourceProvider.getdataSourceInstance().getConnection();
			conn.setAutoCommit(false);
			
			exemplaireDAO = new ExemplaireDAO(conn);
			exemplaires=exemplaireDAO.findAll();
						
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException el) {
				el.printStackTrace();
			}
		} finally {

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return exemplaires;
	}	
	
	public Boolean updateStatus(Exemplaire exemplaire) {
		Boolean ok=null;
		try {
			conn = DataSourceProvider.getdataSourceInstance().getConnection();
			conn.setAutoCommit(false);
			
			exemplaireDAO = new ExemplaireDAO(conn);
			ok=exemplaireDAO.updateStatus(exemplaire);
						
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException el) {
				el.printStackTrace();
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ok;
	}	

	public Boolean update(Exemplaire exemplaire) {
		Boolean ok=null;
		try {
			conn = DataSourceProvider.getdataSourceInstance().getConnection();
			conn.setAutoCommit(false);
			
			exemplaireDAO = new ExemplaireDAO(conn);
			ok=exemplaireDAO.update(exemplaire);
						
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException el) {
				el.printStackTrace();
			}
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ok;
	}		

	public Boolean insert(Exemplaire exemplaire) {
		Boolean ok=null;
		try {
			conn = DataSourceProvider.getdataSourceInstance().getConnection();
			conn.setAutoCommit(false);
			
			exemplaireDAO = new ExemplaireDAO(conn);
			ok=exemplaireDAO.insert(exemplaire);
						
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException el) {
				el.printStackTrace();
			}
		} finally {

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ok;
	}	

	public Boolean delete(Integer id) {
		Boolean ok=null;
		try {
			conn = DataSourceProvider.getdataSourceInstance().getConnection();
			conn.setAutoCommit(false);
			
			exemplaireDAO = new ExemplaireDAO(conn);
			ok=exemplaireDAO.delete(id);
						
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				if (conn != null)
					conn.rollback();
			} catch (SQLException el) {
				el.printStackTrace();
			}
		} finally {

			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return ok;
	}		
}
