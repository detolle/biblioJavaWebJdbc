package com.bibliotheque.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bibliotheque.entity.Editeur;

/**
 * Servlet implementation class SimpleServletController
 */
@WebServlet("/editeur")
public class EditeurController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public EditeurController() {
        super();
    }


	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		List<Editeur> editeurs=new ArrayList<>();
		editeurs.add(new Editeur("Gallimard", "Paris"));
		editeurs.add(new Editeur("Flammarion", "Paris"));
		editeurs.add(new Editeur("Milan", "Toulouse"));
		editeurs.add(new Editeur("Baudelaire", "Lyon"));
		editeurs.add(new Editeur("Minuit", "Paris"));
		editeurs.add(new Editeur("Hachette", "Vanves"));
		
		request.setAttribute("editeurs", editeurs);
		request.setAttribute("madate", new Date());
		request.getRequestDispatcher("/WEB-INF/editeur-liste.jsp").forward(request, response);	

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//doGet(request, response);
		PrintWriter out=response.getWriter();

		out.print("methode="+request.getParameter("methode")+" id="+request.getParameter("id"));			
		
//		switch (request.getParameter("methode")) {
//		case "POST":	//ajout
//			break;
//		case "PUT":		//maj
//			
//			break;
//		case "DELETE":	//delete
//			
//			break;
//		default:
//			break;
//		}

		//request.getRequestDispatcher("/WEB-INF/editeur-liste.jsp").forward(request, response);	
		
	}
	


}
