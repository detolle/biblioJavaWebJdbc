package com.bibliotheque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque.dao.ILivreDAO;
import com.bibliotheque.entity.Livre;




public class LivreDAO implements ILivreDAO {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Connection conn;	

	public LivreDAO(Connection conn) {
		super();
		this.conn = conn;
	}	

	
	public Livre findByKey(String isbn) throws SQLException {  	
		Livre livre=null;
		
        PreparedStatement ps=conn
        		.prepareStatement("select * from livre where isbn=?"); 
        ps.setString(1, isbn);
        ResultSet rs=ps.executeQuery();

        if(rs.next()) {
        	livre=new Livre(
						rs.getString("isbn"),
						rs.getString("titre"),
						rs.getShort("anneeParution"),
	        			rs.getInt("nbPages")
						);        	
        }        
        rs.close();
        ps.close();
        
		return livre;
	}
	public List<Livre> findAll() throws SQLException {
		Livre livre=null;
		List<Livre> livres = new ArrayList<>();
        PreparedStatement ps=conn
        		.prepareStatement("select * from livre order by titre"); 
        //ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();

        while(rs.next()) {
        	livre=new Livre(
						rs.getString("isbn"),
						rs.getString("titre"),
						rs.getShort("anneeParution"),
	        			rs.getInt("nbPages")
						);   
//System.out.println(livre);        	
        	livres.add(livre);
        }        
        rs.close();
        ps.close();
        
		return livres;
	}
	
//	public Boolean update(Livre livre) throws SQLException {
//        PreparedStatement ps=conn
//        		.prepareStatement("update livre set status=?,dateAchat=?,isbn=? where idLivre=?"); 
//        ps.setString(1, livre.getStatus().toString());
//    	ps.setDate(2, new java.sql.Date(livre.getDateAchat().getTime()));        
//        ps.setString(3, livre.getIsbn());
//        ps.setInt(4, livre.getIdLivre());
//        int nb=ps.executeUpdate();
//
//        ps.close();        
//		return ((nb > 0) ? true : false);		
//	}
//	public Boolean insert(Livre livre) throws SQLException {
//        PreparedStatement ps=conn
//        		.prepareStatement("insert into livre (seq_livre.nextval,dateAchat,status,isbn) values(?,?,?)"); 
//    	ps.setDate(1, new java.sql.Date(livre.getDateAchat().getTime()));        
//        ps.setString(2, livre.getStatus().toString());
//        ps.setString(3, livre.getIsbn());
//        int nb=ps.executeUpdate();
//
//        ps.close();        
//		return ((nb > 0) ? true : false);		
//	}
//	public Boolean delete(Integer id) throws SQLException {
//        PreparedStatement ps=conn
//        		.prepareStatement("delete livre where idLivre=?"); 
//        ps.setInt(1, id);
//        int nb=ps.executeUpdate();
//        ps.close();
//        
//		return ((nb > 0) ? true : false);
//	}	
	
}
