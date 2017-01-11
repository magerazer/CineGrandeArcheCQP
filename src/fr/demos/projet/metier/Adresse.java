package fr.demos.projet.metier;

public class Adresse {

	private String rue;
	private String codePostal;
	private String ville;
	private String pays;
	
	public Adresse() {
		
		
	}

	public Adresse(String rue, String codePostal, String ville, String pays) {
		super();
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}

	
	public String getRue() {
		return rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public String getVille() {
		return ville;
	}

	public String getPays() {
		return pays;
	}
	
	

}
