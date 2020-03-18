//Source file: D:\\CDA\\TP_UML\\bibliotheque\\domain\\Editeur.java

package com.bibliotheque.entity;

import java.util.ArrayList;
import java.util.List;

public class Editeur {
	private String nomEditeur;
	private String ville;
	public List<Livre> livres= new ArrayList<>();

	public Editeur() {}
	public Editeur(String nomEditeur, String ville) {
		super();
		this.nomEditeur = nomEditeur;
		this.ville = ville;
	}

	public String getNomEditeur() {
		return nomEditeur;
	}

	public void setNomEditeur(String nomEditeur) {
		this.nomEditeur = nomEditeur;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public List<Livre> getLivres() {
		return livres;
	}

	public void setLivres(List<Livre> livres) {
		this.livres = livres;
	}
	@Override
	public String toString() {
		return "Editeur [nomEditeur=" + nomEditeur + ", ville=" + ville + ", livres=" + livres + "]";
	}
	
}
