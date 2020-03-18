package com.bibliotheque.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet("/exemplaire")
public class ExemplaireController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ExemplaireController() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Exemplaire> exemplaires = new ArrayList<>();
		
		ExemplaireService exemplaireService=new ExemplaireService();
		exemplaires=exemplaireService.findAll();
//for(Exemplaire exemplaire:exemplaires) {
//	System.out.println(exemplaire);
//}
		request.setAttribute("exemplaires", exemplaires);
		request.getRequestDispatcher("/WEB-INF/exemplaire-liste.jsp").forward(request, response);	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExemplaireService exemplaireService=null;
		LivreService livreService=null;
		Exemplaire exemplaire=null;
		List<Livre> livres = new ArrayList<>();

		Integer id=Integer.parseInt(request.getParameter("id"));
				
		switch (request.getParameter("methode")) {
		case "POST":	//ajout
			exemplaire=new Exemplaire(0, new Date(), EnumStatusExemplaire.DISPONIBLE, "");
			request.setAttribute("exemplaire", exemplaire);
			
			livreService=new LivreService();
			livres=livreService.findAll();
			request.setAttribute("livres", livres);
			
			request.getRequestDispatcher("/WEB-INF/exemplaire-form.jsp").forward(request, response);	
			break;
		case "PUT":		//maj
			exemplaireService=new ExemplaireService();
			exemplaire=exemplaireService.findByKey(id);
			request.setAttribute("exemplaire", exemplaire);
			
			livreService=new LivreService();
			livres=livreService.findAll();
			request.setAttribute("livres", livres);
			
			request.getRequestDispatcher("/WEB-INF/exemplaire-form.jsp").forward(request, response);	
			break;
		case "DELETE":	//delete
			exemplaireService=new ExemplaireService();
			exemplaireService.delete(id);			
			response.sendRedirect(request.getContextPath()+"/exemplaire");  
			break;
		default:
			response.sendRedirect(request.getContextPath()+"/exemplaire");  
			break;
		}
		
	}
	


}
