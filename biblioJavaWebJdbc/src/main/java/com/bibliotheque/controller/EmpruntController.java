package com.bibliotheque.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.bibliotheque.entity.Adherent;
import com.bibliotheque.entity.EmpruntEnCoursDb;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Livre;
import com.bibliotheque.entity.Utilisateur;
import com.bibliotheque.service.EmpruntService;
import com.bibliotheque.service.ExemplaireService;
import com.bibliotheque.service.LivreService;
import com.bibliotheque.service.UtilisateurService;
import com.bibliotheque.util.BiblioException;
import com.bibliotheque.util.EnumStatusExemplaire;

/**
 * Servlet implementation class SimpleServletController
 */
@WebServlet("/emprunt")
public class EmpruntController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/emprunt.jsp").forward(request, response);				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idu=null;
		Integer idex=null;
		Boolean ok=true;
		String erreur="";
		Utilisateur utilisateur=null;
		Exemplaire exemplaire=null;
		EmpruntService empruntService=null;
		ExemplaireService exemplaireService=null;
		UtilisateurService utilisateurService=null;
		
		//saisie de l'utilisateur
		if(request.getParameter("idUtilisateur").isEmpty()) {
//System.out.println("idUtilisateur="+request.getParameter("idUtilisateur"));
			if(request.getParameter("idu")==null) {
				request.getRequestDispatcher("/WEB-INF/emprunt.jsp").forward(request, response);	
				return;
			}
			
			//controle de la saisie idUtilisateur
			try {
				idu=Integer.parseInt(request.getParameter("idu"));
			} catch (NumberFormatException nfe) {
				//nfe.printStackTrace();
				erreur="Vous devez saisir un nombre entier";
				ok=false;
			} catch (Exception e) {
				e.printStackTrace();
				erreur=e.getMessage();
				ok=false;			
			}	

			//controle de l'existence de l'utilisateur et on fait une recherche en cascade emprunt/exemplaire
			if(ok) {
				empruntService=new EmpruntService();
				utilisateur=empruntService.findByKeyUtilisateur(idu);
				if(utilisateur==null) {
					erreur="utilisateur "+idu+" inexistant";
					ok=false;			
				}				
			}
			//controle si ok (max 3 et pas de retard)
			if(ok && (utilisateur instanceof Adherent)) {
				try {
					if (utilisateur.isConditionsPretAcceptees()) {
						ok = true;
					}
				} catch (BiblioException be) {
					erreur="utilisateur: "+ be.getMessage();
					ok = false;
				}				
			}
	
			if(ok) {			
				request.setAttribute("idUtilisateur", idu);			
				request.setAttribute("utilisateur", utilisateur);
	
				request.getRequestDispatcher("/WEB-INF/emprunt.jsp").forward(request, response);	
				return;				
			}
			else {
				request.setAttribute("erreur", erreur);			
				request.getRequestDispatcher("/WEB-INF/emprunt.jsp").forward(request, response);
				return;
			}
		}
		
		// si l'utilisateur est ok
		//saisie de l'exemplaire
		///////////////////////////////////////////////////////
		if(request.getParameter("idExemplaire").isEmpty()) {
//System.out.println("idUtilisateur="+request.getParameter("idUtilisateur"));
//System.out.println("idExemplaire="+request.getParameter("idExemplaire"));
			if(request.getParameter("idex")==null) {
				ok=false;
			}
			else {
				//controle de la saisie idUtilisateur
				try {
					idex=Integer.parseInt(request.getParameter("idex"));
				} catch (NumberFormatException nfe) {
					//nfe.printStackTrace();
					erreur="Vous devez saisir un nombre entier";
					ok=false;
				} catch (Exception e) {
					e.printStackTrace();
					erreur=e.getMessage();
					ok=false;			
				}	
			}

			//controle de l'existence de l'exemplaire
			if(ok) {
				exemplaireService=new ExemplaireService();
				exemplaire=exemplaireService.findByKey(idex);
				if(exemplaire==null) {
					erreur="exemplaire "+idex+" inexistant";
					ok=false;			
				}
				else {
					if(!exemplaire.getStatus().equals(EnumStatusExemplaire.DISPONIBLE)) {
						erreur="exemplaire indisponible ";
						ok=false;						
					}
				}
			}

			//recherche à nouveau de l'utilisateur pour l'afficher
			utilisateurService=new UtilisateurService();
			utilisateur=utilisateurService.findByKey(Integer.valueOf(request.getParameter("idUtilisateur")));				
			request.setAttribute("idUtilisateur", utilisateur.getIdUtilisateur());	
			request.setAttribute("utilisateur", utilisateur);	

			if(ok) {
				request.setAttribute("idExemplaire", exemplaire.getIdExemplaire());	
				request.setAttribute("exemplaire", exemplaire);	
				request.setAttribute("message", "Tout est ok ; Validez-vous l'emprunt ?");	
				request.getRequestDispatcher("/WEB-INF/emprunt.jsp").forward(request, response);	
				return;				
			}
			else {
				request.setAttribute("erreur", erreur);			
				request.getRequestDispatcher("/WEB-INF/emprunt.jsp").forward(request, response);											
				return;
			}
			
		}
		
		
		// Validation de l'emprunt
		if(! (request.getParameter("idUtilisateur").isEmpty() && request.getParameter("idExemplaire").isEmpty()) ) {
//System.out.println("idUtilisateur="+request.getParameter("idUtilisateur"));
//System.out.println("idExemplaire="+request.getParameter("idExemplaire"));
			
			/***************************************************************************/
			/**
			 * tout est ok insert empruntEnCours + update exemplaire
			 */
			idu=Integer.parseInt(request.getParameter("idUtilisateur"));
			idex=Integer.parseInt(request.getParameter("idExemplaire"));
			
			empruntService=new EmpruntService();
			empruntService.emprunter(idu, idex);				
			
			//recherche à nouveau de l'utilisateur pour l'afficher
			utilisateurService=new UtilisateurService();
			utilisateur=utilisateurService.findByKey(Integer.valueOf(request.getParameter("idUtilisateur")));				
			request.setAttribute("idUtilisateur", utilisateur.getIdUtilisateur());	
			request.setAttribute("utilisateur", utilisateur);	
			request.setAttribute("messageOK", "emprunt de l'exemplaire n°"+idex+" effectué");	
			request.getRequestDispatcher("/WEB-INF/emprunt.jsp").forward(request, response);		
			return;			
		}
		
		
		
	}
	
}
