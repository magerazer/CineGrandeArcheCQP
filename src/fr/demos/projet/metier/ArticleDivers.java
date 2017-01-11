package fr.demos.projet.metier;

public class ArticleDivers extends Article {

	private String typeDivers;
	private String caracteristiques;

	
	// article dematerialise
	public ArticleDivers(String ref, double prixHT, String nom, String format, String url, String typeDivers, String caracteristiques) {
		super(ref, prixHT, nom, format, url);
		this.type = "Divers";
		
	}

	public ArticleDivers(String ref, String typeDivers, double prixHT, String nom, int stock) {
		super(ref, prixHT, nom, stock);
		// TODO Auto-generated constructor stub
		this.type = "Divers";
		this.typeDivers = typeDivers;
	}

	public ArticleDivers(String ref, String typeDivers, double prixHT, String nom, int stock, Etat etat) {
		super(ref, prixHT, nom, stock, etat);
		// TODO Auto-generated constructor stub
		this.type = "Divers";
		this.typeDivers = typeDivers;
	}

	public String getTypeDivers() {
		return typeDivers;
	}

	public void setTypeDivers(String type) {
		this.typeDivers = type;
	}

	public String getCaracteristiques() {
		return caracteristiques;
	}

	public void setCaracteristiques(String caracteristiques) {
		this.caracteristiques = caracteristiques;
	}

	
	
	
	
}
