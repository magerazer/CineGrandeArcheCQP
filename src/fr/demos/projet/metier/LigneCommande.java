package fr.demos.projet.metier;

import java.time.LocalDate;

public class LigneCommande {

	private String reference;
	private String nom;
	private double prixHT;
	private int qte;
	private String img;
		
	private LocalDate date;
	
	public LigneCommande(LocalDate date, String reference, String nom, double prixHT, int qte, String img) {
		this.reference = reference;
		this.nom = nom;
		this.prixHT = prixHT;
		this.qte = qte;
		this.date = date;
		this.img = img;
	}
	
	public String getReference() {
		return reference;
	}

	public String getNom() {
		return nom;
	}

	public double getPrixHT() {
		return prixHT;
	}	
	
	public int getQte() {
		return qte;
	}
	
	public String getImg() {
		return img;
	}

	
	
	public LocalDate getDate() {
		return date;
	}
	
	

}
