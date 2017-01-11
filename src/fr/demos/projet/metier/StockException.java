package fr.demos.projet.metier;

public class StockException extends Exception {

	private String message = "";
	private Article a;
	private int stock;
	private int quantite;
	
	public StockException(Article a, int stock, int qte) {
		this.a = a;
		this.stock = stock;
		this.quantite = qte;
		
		message = "Commande de " + quantite + " " + a.getNom() +
			" impossible car le stock est de " + stock + " articles";
		
	}

	public StockException(int stock, int qte) {
		
		this.stock = stock;
		this.quantite = qte;
		
		message = "Commande de " + quantite + 
			" articles impossible car le stock est de " + stock + " articles";
		
	}
	
	public String getMessage() {
		return message;
	}
	
	


}
