package com.bibliotheque.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bibliotheque.dao.IEmpruntEnCoursDAO;
import com.bibliotheque.dao.IExemplaireDAO;
import com.bibliotheque.dao.IUtilisateurDAO;
import com.bibliotheque.dao.impl.EmpruntEnCoursDAO;
import com.bibliotheque.dao.impl.ExemplaireDAO;
import com.bibliotheque.dao.impl.UtilisateurDAO;
import com.bibliotheque.entity.EmpruntEnCoursDb;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Utilisateur;
import com.bibliotheque.util.DataSourceProvider;
import com.bibliotheque.util.EnumStatusExemplaire;

public class EmpruntService {
	private Connection conn;
	private IExemplaireDAO exemplaireDAO;
	private IUtilisateurDAO utilisateurDAO;
	private IEmpruntEnCoursDAO empruntEnCoursDAO;
		
	public EmpruntService() {
	}

	public Utilisateur findByKeyUtilisateur(Integer id) {
		Utilisateur utilisateur=null;
		List<EmpruntEnCoursDb> listeEmpruntEnCours=null;
		Exemplaire exemplaire=null;
		try {
			conn = DataSourceProvider.getdataSourceInstance().getConnection();
			conn.setAutoCommit(false);

			//recherche utilisateur
			utilisateurDAO = new UtilisateurDAO(conn);
			utilisateur = (Utilisateur) utilisateurDAO.findByKey(id);

			if(utilisateur!=null) {
				//recherche des emprunts en cours
				empruntEnCoursDAO = new EmpruntEnCoursDAO(conn);			
				listeEmpruntEnCours = empruntEnCoursDAO.findByKeyUtilisateur(id);
				utilisateur.setEmpruntEnCoursDb(listeEmpruntEnCours);
				
				//on renseigne les exemplaire des emprunts
				exemplaireDAO = new ExemplaireDAO(conn);			
				for (EmpruntEnCoursDb emprunt : listeEmpruntEnCours) {
					exemplaire = exemplaireDAO.findByKey(emprunt.getIdExemplaire());
					emprunt.setExemplaire(exemplaire);
				}
			}
											
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

	public void emprunter(Integer idu, Integer idex) {		
		Utilisateur utilisateur=null;
		Exemplaire exemplaire=null;
		EmpruntEnCoursDb empruntEnCoursDb=null;
		
		try {
			conn = DataSourceProvider.getdataSourceInstance().getConnection();
			conn.setAutoCommit(false);

			empruntEnCoursDb = new EmpruntEnCoursDb(idex, idu, new Date());
			
			empruntEnCoursDAO=new EmpruntEnCoursDAO(conn);
			empruntEnCoursDAO.insert(empruntEnCoursDb);

			exemplaireDAO=new ExemplaireDAO(conn);
			exemplaire=exemplaireDAO.findByKey(idex);
			exemplaire.setStatus(EnumStatusExemplaire.PRETE);
			exemplaireDAO.updateStatus(exemplaire);	
			
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

	}

}
