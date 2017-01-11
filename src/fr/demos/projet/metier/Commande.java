package fr.demos.projet.metier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

public class Commande implements  Iterable<LigneCommande>{
	
	private int numero;
	private ArrayList<LigneCommande> commandes;
	private LocalDate date;
	private String nom;
	private String prenom;
	private String mail;
	private String adrLiv;
	private String adrFact;
	
	public Commande(Compte c, LocalDate date) {	
		System.out.println("mon compte avec adrLivr = " + c.getAdrLiv());
		this.date = date;
		commandes = new ArrayList<>();
		this.nom = c.getNom();
		this.prenom = c.getPrenom();
		this.mail = c.getMail();
		this.adrLiv = c.getAdrLiv();
		this.adrFact = c.getAdrFact();
	}
	
	public void ajouter(LigneCommande ligne) {
		//commandes.add(new LigneCommande(reference, nom, prixHT));
		commandes.add(ligne);
	}

	@Override
	public Iterator<LigneCommande> iterator() {
		return commandes.iterator();
	}
	
	public LocalDate getDate() {
		return date;
	}

	public int getNumero() {
		return numero;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getMail() {
		return mail;
	}

	public String getAdrLiv() {
		return adrLiv;
	}

	public String getAdrFact() {
		return adrFact;
	}
	
		
}


