package fr.demos.projet.metier;

public class DematerialiseException extends Exception {

	private String message;
	private Article a;
	
	
	public DematerialiseException(Article a) {
		this.a = a;
		
		message = "L'article suivant est dematerialise." + a;
		
	}

	public String getMessage() {
		return message;
	}
	
}
