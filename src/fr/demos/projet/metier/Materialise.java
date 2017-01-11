package fr.demos.projet.metier;

public class Materialise {

	private int stock;
	private double coutLivraison;
	private Etat etat;
	
	public Materialise(Etat etat, int stock) {
		this.etat = etat;
		this.stock = stock;
	}
	
//	public void diminuerStock(int qte) {
//		this.stock -= qte;
//	}

	@Override
	public String toString() {
		return "stock=" + stock + ", coutLivraison=" + coutLivraison;
	}



	public Etat getEtat() {
		return etat;
	}



	public void setEtat(Etat etat) {
		this.etat = etat;
	}



	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getCoutLivraison() {
		return coutLivraison;
	}

	public void setCoutLivraison(double coutLivraison) {
		this.coutLivraison = coutLivraison;
	}
	
	

}
