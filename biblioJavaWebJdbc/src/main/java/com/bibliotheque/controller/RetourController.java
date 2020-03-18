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
import com.bibliotheque.service.RetourService;
import com.bibliotheque.service.UtilisateurService;
import com.bibliotheque.util.BiblioException;
import com.bibliotheque.util.EnumStatusExemplaire;

/**
 * Servlet implementation class SimpleServletController
 */
@WebServlet("/retour")
public class RetourController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/retour.jsp").forward(request, response);				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idu=null;
		Integer idex=null;
		Boolean ok=true;
		String erreur="";
		Exemplaire exemplaire=null;
		RetourService retourService=null;
		ExemplaireService exemplaireService=null;

		
		//saisie de l'exemplaire
		///////////////////////////////////////////////////////
		if(request.getParameter("idExemplaire").isEmpty()) {
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
					//e.printStackTrace();
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
					if(exemplaire.getStatus().equals(EnumStatusExemplaire.DISPONIBLE)) {
						erreur="exemplaire non emprunté";
						ok=false;						
					}
				}
			}

			if(ok) {
				request.setAttribute("idExemplaire", exemplaire.getIdExemplaire());	
				request.setAttribute("exemplaire", exemplaire);	
				request.setAttribute("message", "Tout est ok ; Validez-vous le retour ?");	
				request.getRequestDispatcher("/WEB-INF/retour.jsp").forward(request, response);	
				return;				
			}
			else {
				request.setAttribute("erreur", erreur);			
				request.getRequestDispatcher("/WEB-INF/retour.jsp").forward(request, response);											
				return;
			}
			
		}
		
		
		// Validation du retour
		if(! request.getParameter("idExemplaire").isEmpty() ) {
//System.out.println("idExemplaire="+request.getParameter("idExemplaire"));
			
			/***************************************************************************/
			/**
			 * tout est ok insert empruntEnCours + update exemplaire
			 */
			idex=Integer.parseInt(request.getParameter("idExemplaire"));
			
			retourService=new RetourService();
			retourService.retourner(idex);
			
			request.setAttribute("messageOK", "retour de l'exemplaire n°"+idex+" effectué");	
			request.getRequestDispatcher("/WEB-INF/retour.jsp").forward(request, response);		
			return;			
		}
		
		
		
	}
	
}
