package com.bibliotheque.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque.dao.IUtilisateurDAO;
import com.bibliotheque.dao.impl.UtilisateurDAO;
import com.bibliotheque.entity.Utilisateur;
import com.bibliotheque.util.DataSourceProvider;

public class UtilisateurService {
	private Connection conn;
	private IUtilisateurDAO utilisateurDAO;

	public UtilisateurService() {
	}

	public Utilisateur findByKey(Integer id) {
		Utilisateur utilisateur=null;
		try {
			conn = DataSourceProvider.getdataSourceInstance().getConnection();
			conn.setAutoCommit(false);
			
			utilisateurDAO = new UtilisateurDAO(conn);
			utilisateur=utilisateurDAO.findByKey(id);
						
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
		return utilisateur;
	}
	
	public List<Utilisateur> findAll() {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		try {
			conn = DataSourceProvider.getdataSourceInstance().getConnection();
			conn.setAutoCommit(false);
			
			utilisateurDAO = new UtilisateurDAO(conn);
			utilisateurs=utilisateurDAO.findAll();
						
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
		return utilisateurs;
	}	
	
//	public Boolean update(Utilisateur utilisateur) {
//		Boolean ok=null;
//		try {
//			conn = DataSourceProvider.getdataSourceInstance().getConnection();
//			conn.setAutoCommit(false);
//			
//			utilisateurDAO = new UtilisateurDAO(conn);
//			ok=utilisateurDAO.update(utilisateur);
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
//	public Boolean insert(Utilisateur utilisateur) {
//		Boolean ok=null;
//		try {
//			conn = DataSourceProvider.getdataSourceInstance().getConnection();
//			conn.setAutoCommit(false);
//			
//			utilisateurDAO = new UtilisateurDAO(conn);
//			ok=utilisateurDAO.insert(utilisateur);
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
//			utilisateurDAO = new UtilisateurDAO(conn);
//			ok=utilisateurDAO.delete(id);
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
