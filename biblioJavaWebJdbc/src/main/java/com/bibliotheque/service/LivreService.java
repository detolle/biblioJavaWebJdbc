package com.bibliotheque.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque.dao.ILivreDAO;
import com.bibliotheque.dao.impl.LivreDAO;
import com.bibliotheque.entity.Livre;
import com.bibliotheque.util.DataSourceProvider;

public class LivreService {
	private Connection conn;
	private ILivreDAO LivreDAO;

	public LivreService() {
	}

	public Livre findByKey(String isbn) {
		Livre Livre=null;
		try {
			conn = DataSourceProvider.getdataSourceInstance().getConnection();
			conn.setAutoCommit(false);
			
			LivreDAO = new LivreDAO(conn);
			Livre=LivreDAO.findByKey(isbn);
						
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
		return Livre;
	}
	
	public List<Livre> findAll() {
		List<Livre> Livres = new ArrayList<>();
		try {
			conn = DataSourceProvider.getdataSourceInstance().getConnection();
			conn.setAutoCommit(false);
			
			LivreDAO = new LivreDAO(conn);
			Livres=LivreDAO.findAll();
						
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
		return Livres;
	}	
	

//	public Boolean update(Livre Livre) {
//		Boolean ok=null;
//		try {
//			conn = DataSourceProvider.getdataSourceInstance().getConnection();
//			conn.setAutoCommit(false);
//			
//			LivreDAO = new LivreDAO(conn);
//			ok=LivreDAO.updateStatus(Livre);
//						
//			conn.commit();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			try {
//				if (conn != null)
//					conn.rollback();
//			} catch (SQLException el) {
//				el.printStackTrace();
//			}
//		} finally {
//			try {
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return ok;
//	}		
//
//	public Boolean insert(Livre Livre) {
//		Boolean ok=null;
//		try {
//			conn = DataSourceProvider.getdataSourceInstance().getConnection();
//			conn.setAutoCommit(false);
//			
//			LivreDAO = new LivreDAO(conn);
//			ok=LivreDAO.insert(Livre);
//						
//			conn.commit();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			try {
//				if (conn != null)
//					conn.rollback();
//			} catch (SQLException el) {
//				el.printStackTrace();
//			}
//		} finally {
//
//			try {
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return ok;
//	}	
//
//	public Boolean delete(Integer id) {
//		Boolean ok=null;
//		try {
//			conn = DataSourceProvider.getdataSourceInstance().getConnection();
//			conn.setAutoCommit(false);
//			
//			LivreDAO = new LivreDAO(conn);
//			ok=LivreDAO.delete(id);
//						
//			conn.commit();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			try {
//				if (conn != null)
//					conn.rollback();
//			} catch (SQLException el) {
//				el.printStackTrace();
//			}
//		} finally {
//
//			try {
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return ok;
//	}		
}
