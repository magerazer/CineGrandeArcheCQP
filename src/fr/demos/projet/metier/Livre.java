package fr.demos.projet.metier;

import java.time.LocalDate;

public class Livre extends Article {

	private String isbn;
	private String auteur;	
	private String genre;
	private String format;
	private int nbPages;
	private LocalDate dateParution;
	
	
	// livre virtuel
	public Livre(String ref, double prixHT, String nom, String format, String url, String auteur, String isbn) {
		super(ref, prixHT, nom, format, url);
		this.auteur = auteur;
		this.isbn =isbn;
		this.type = "Livre";
	}
	// livre reel
	public Livre(String ref, double prixHT, String nom, int stock, String auteur, String isbn) {
		super(ref, prixHT, nom, stock);
		this.auteur = auteur;
		this.isbn = isbn;
		this.type = "Livre";
	}
	// livre reel d'occasion
	public Livre(String ref, double prixHT, String nom, int stock, Etat etat, String auteur, String isbn) {
		super(ref, prixHT, nom, stock, etat);
		this.auteur = auteur;
		this.isbn = isbn;
		this.type = "Livre";
	}
	
	
	@Override
	public String toString() {
		return "auteur=" + auteur + ", genre=" + genre + "";
	}	
	
	
	
	// =============== début getters et setters =========== //
		
	public String getIsbn() {
		return isbn;
	}
	public String getAuteur() {
		return auteur;
	}
	public String getGenre() {
		return genre;
	}
	public String getFormat() {
		return format;
	}
	public int getNbPages() {
		return nbPages;
	}
	public LocalDate getDateParution() {
		return dateParution;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public void setNbPages(int nbPages) {
		this.nbPages = nbPages;
	}
	public void setDateParution(LocalDate dateParution) {
		this.dateParution = dateParution;
	}
	
	
	// ============== fin getters et setters ============== //
	
	

	
	
}


