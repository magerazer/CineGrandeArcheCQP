package fr.demos.projet.metier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import fr.demos.projet.data.ArticleDAOMySQL;
import fr.demos.projet.data.CompteDAOMySQL;

public class PassageCommande {

	Panier panier;
	/*
	 * gestion des erreurs avec la HashMap
	 */
	 Map<Article, String> erreursCommandeStock = new HashMap<Article, String>();
	
	
	public PassageCommande(Panier panier) {
		this.panier = panier;
		
	}
	
	public Commande creerCommande(Compte compte) throws LoginException {
		
		ArticleDAOMySQL articleDao = null;
    	try {
			articleDao = new ArticleDAOMySQL();			
		} catch (Exception e) {
			e.printStackTrace();		
		}
		
		System.out.println("erreur Compte " + compte);
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDate dateCde = currentTime.toLocalDate();
		if(compte == null) {
			throw new LoginException("Pas authentifié");
		}
		Commande commande = new Commande(compte, dateCde);
		System.out.println("erreur Compte disparait bouton commande");
		
		
		System.out.println("erreur Compte disparait bouton commande");
		/*
		 ajout du panier à la commande
		 */
		LigneCommande ligCde;
		for (LignePanier lignePanier : panier) {
			
			ligCde = new LigneCommande(dateCde, lignePanier.getArticle().getRef(), lignePanier.getArticle().getNom(), lignePanier.getArticle().getPrixHT(), lignePanier.getQuantite(), lignePanier.getArticle().getImage());
			commande.ajouter(ligCde);
			
			Article a = lignePanier.getArticle();
			Article article = articleDao.selectArticle(a.getRef()); 
			
			try {
				articleDao.updateStock(article, lignePanier.getQuantite());
			} 
			catch(StockException e) {
				
				erreursCommandeStock.put(a, "Cet article n'est plus disponible.");
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			/*
			System.out.println("stock avant : " + a.getMat().getStock());		
			a.getMat().setStock(a.getMat().getStock() - lignePanier.getQuantite());
			System.out.println("stock après : " + a.getMat().getStock());
			*/
		}
		
		
		return commande;
	}

	public Map<Article, String> getErreursCommandeStock() {
		return erreursCommandeStock;
	}

	
	
}
