package com.bibliotheque.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.entity.Livre;
import com.bibliotheque.service.ExemplaireService;
import com.bibliotheque.service.LivreService;
import com.bibliotheque.util.EnumStatusExemplaire;

/**
 * Servlet implementation class SimpleServletController
 */
@WebServlet("/exemplaireUpd")
public class ExemplaireControllerUpd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//format de date de l'input type=date HTML5
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");       

    public ExemplaireControllerUpd() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExemplaireService exemplaireService=null;
		LivreService livreService=null;
		List<Livre> livres = new ArrayList<>();
		Boolean ok=true;
		String erreur="";
		Integer id=null;
		/**
		 * controle saisie
		 * 
		 */
		Exemplaire exemplaire=new Exemplaire();

		if(request.getParameter("isbn").isEmpty()) {
			erreur="Veuillez saisir un isbn";
			ok=false;			
		}
		try {			
			exemplaire.setIdExemplaire(Integer.parseInt(request.getParameter("idExemplaire")));
			exemplaire.setDateAchat(sdf.parse(request.getParameter("dateAchat")));
			exemplaire.setStatus(EnumStatusExemplaire.valueOf(request.getParameter("status")));
			exemplaire.setIsbn(request.getParameter("isbn"));
		} catch (ParseException pe) {
			pe.printStackTrace();
			erreur="Date érronée";
			ok=false;
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			erreur="erreur interne";
			ok=false;
		} catch (Exception e) {
			e.printStackTrace();
			erreur=e.getMessage();
			ok=false;			
		}	
//System.out.println(exemplaire);		
		if(!ok) {			
			livreService=new LivreService();
			livres=livreService.findAll();
			request.setAttribute("livres", livres);
			
			request.setAttribute("exemplaire", exemplaire);			
			request.setAttribute("erreur", erreur);			
			request.getRequestDispatcher("/WEB-INF/exemplaire-form.jsp").forward(request, response);				
		}
		else {
			id=exemplaire.getIdExemplaire();
			switch (id) {
			case 0:	//ajout
				exemplaireService=new ExemplaireService();
				exemplaireService.insert(exemplaire);
				break;
			default: //maj
				exemplaireService=new ExemplaireService();
				exemplaireService.update(exemplaire);
				break;
			}
//System.out.println(request.getContextPath()+"/exemplaire");			
			//retour vers l'url du controlleur ExemplaireController	
			response.sendRedirect(request.getContextPath()+"/exemplaire");  
		}

		
	}
	


}
