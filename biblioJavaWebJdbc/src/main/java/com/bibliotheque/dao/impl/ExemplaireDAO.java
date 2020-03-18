package com.bibliotheque.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.bibliotheque.dao.IExemplaireDAO;
import com.bibliotheque.entity.Exemplaire;
import com.bibliotheque.util.DataSourceProvider;
import com.bibliotheque.util.EnumStatusExemplaire;


public class ExemplaireDAO implements IExemplaireDAO {
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	private Connection conn;	

	public ExemplaireDAO(Connection conn) {
		super();
		this.conn = conn;
	}	

	
	public Exemplaire findByKey(Integer id) throws SQLException {  	
		Exemplaire exemplaire=null;
		
        PreparedStatement ps=conn
        		.prepareStatement("select * from exemplaire where idExemplaire=?"); 
        ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();

        if(rs.next()) {
//        	public Exemplaire(Integer idExemplaire, Date dateAchat, EnumStatusExemplaire status, String isbn) {
       	
        	exemplaire=new Exemplaire(
								rs.getInt("idExemplaire"),
								rs.getDate("dateAchat"),
								EnumStatusExemplaire.valueOf(rs.getString("status")),
								rs.getString("isbn")
								);        	
        }        
        rs.close();
        ps.close();
        
		return exemplaire;
	}
	public List<Exemplaire> findAll() throws SQLException {
		Exemplaire exemplaire=null;
		List<Exemplaire> exemplaires = new ArrayList<>();
        PreparedStatement ps=conn
        		.prepareStatement("select * from exemplaire order by idExemplaire"); 
        //ps.setInt(1, id);
        ResultSet rs=ps.executeQuery();

        while(rs.next()) {
        	exemplaire=new Exemplaire(
								rs.getInt("idExemplaire"),
								rs.getDate("dateAchat"),
								EnumStatusExemplaire.valueOf(rs.getString("status")),
								rs.getString("isbn")
								);
//System.out.println(exemplaire);        	
        	exemplaires.add(exemplaire);
        }        
        rs.close();
        ps.close();
        
		return exemplaires;
	}
	
	public Boolean updateStatus(Exemplaire exemplaire) throws SQLException {
        PreparedStatement ps=conn
        		.prepareStatement("update exemplaire set status=? where idExemplaire=?"); 
        ps.setString(1, exemplaire.getStatus().toString());
        ps.setInt(2, exemplaire.getIdExemplaire());
        int nb=ps.executeUpdate();

        ps.close();        
		return ((nb > 0) ? true : false);		
	}
	
	public Boolean update(Exemplaire exemplaire) throws SQLException {
        PreparedStatement ps=conn
        		.prepareStatement("update exemplaire set status=?,dateAchat=?,isbn=? where idExemplaire=?"); 
        ps.setString(1, exemplaire.getStatus().toString());
    	ps.setDate(2, new java.sql.Date(exemplaire.getDateAchat().getTime()));        
        ps.setString(3, exemplaire.getIsbn());
        ps.setInt(4, exemplaire.getIdExemplaire());
        int nb=ps.executeUpdate();

        ps.close();        
		return ((nb > 0) ? true : false);		
	}
	public Boolean insert(Exemplaire exemplaire) throws SQLException {
        PreparedStatement ps=conn
        		.prepareStatement("insert into exemplaire (idExemplaire,dateAchat,status,isbn) values(seq_exemplaire.nextval,?,?,?)"); 
    	ps.setDate(1, new java.sql.Date(exemplaire.getDateAchat().getTime()));        
        ps.setString(2, exemplaire.getStatus().toString());
        ps.setString(3, exemplaire.getIsbn());
        int nb=ps.executeUpdate();

        ps.close();        
		return ((nb > 0) ? true : false);		
	}
	public Boolean delete(Integer id) throws SQLException {
        PreparedStatement ps=conn
        		.prepareStatement("delete exemplaire where idExemplaire=?"); 
        ps.setInt(1, id);
        int nb=ps.executeUpdate();
        ps.close();
        
		return ((nb > 0) ? true : false);
	}	
	
}
