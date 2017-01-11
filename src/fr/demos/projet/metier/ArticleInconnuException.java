package fr.demos.projet.metier;

public class ArticleInconnuException extends Exception {

	
	private String message;
	private Article a;
	
	
	public ArticleInconnuException(Article a) {
		this.a = a;
		
		message = "Vous ne ouvez pas modifie cet article car il nexiste pas.";
		
	}

	public String getMessage() {
		return message;
	}
	
}
