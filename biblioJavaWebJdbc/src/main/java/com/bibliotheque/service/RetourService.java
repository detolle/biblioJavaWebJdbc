package com.bibliotheque.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bibliotheque.dao.IEmpruntArchiveDAO;
import com.bibliotheque.dao.IEmpruntEnCoursDAO;
import com.bibliotheque.dao.IExemplaireDAO;
import com.bibliotheque.dao.IUtilisateurDAO;
import com.bibliotheque.dao.impl.EmpruntArchiveDAO;
import com.bibliotheque.dao.impl.EmpruntEnCoursDAO;
import com.bibliotheque.dao.impl.ExemplaireDAO;
import com.bibliotheque.dao.impl.UtilisateurDAO;
import com.bibliotheque.entity.EmpruntArchive;
import com.bibliotheque.entity.EmpruntEnCoursDb;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Utilisateur;
import com.bibliotheque.util.DataSourceProvider;
import com.bibliotheque.util.EnumStatusExemplaire;

public class RetourService {
	private Connection conn;
	private IExemplaireDAO exemplaireDAO;
	private IUtilisateurDAO utilisateurDAO;
	private IEmpruntEnCoursDAO empruntEnCoursDAO;
	private IEmpruntArchiveDAO empruntArchiveDAO;	
	
	
	public RetourService() {
	}


	public void retourner(Integer idex) {		
		Utilisateur utilisateur=null;
		Exemplaire exemplaire=null;
		EmpruntEnCoursDb empruntEnCoursDb=null;
		EmpruntArchive empruntArchive=null;
		
		try {
			conn = DataSourceProvider.getdataSourceInstance().getConnection();
			conn.setAutoCommit(false);

			/***************************************************************************/
			/**	tout est ok
			 *  del empruntEnCours + insert empruntArchive + update exemplaire
			 */		
			
			empruntEnCoursDAO=new EmpruntEnCoursDAO(conn);
			empruntEnCoursDb=empruntEnCoursDAO.findByKey(idex);
			
			exemplaireDAO=new ExemplaireDAO(conn);
			exemplaire=exemplaireDAO.findByKey(idex);
			
			utilisateur=new Utilisateur("", "", new Date(), "", empruntEnCoursDb.getIdUtilisateur(), "", "");
			empruntArchive=new EmpruntArchive(empruntEnCoursDb.getDateEmprunt(), 
											new Date(), utilisateur, exemplaire);
			
			empruntArchiveDAO=new EmpruntArchiveDAO(conn);
			empruntArchiveDAO.insert(empruntArchive);

			empruntEnCoursDAO.delete(idex);
			
			exemplaire.setStatus(EnumStatusExemplaire.DISPONIBLE);
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
