package fr.demos.formation.metier.test;

import static org.hamcrest.core.Is.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.demos.projet.metier.Article;
import fr.demos.projet.metier.ArticleDivers;
import fr.demos.projet.metier.Compte;
import fr.demos.projet.metier.DematerialiseException;
import fr.demos.projet.metier.Etat;
import fr.demos.projet.metier.LignePanier;
import fr.demos.projet.metier.Livre;
import fr.demos.projet.metier.Panier;
import fr.demos.projet.metier.StockException;

public class PanierTest {

	Panier panier = null;

	Article livreMat1 = null;
	Article livreMatOccas1 = null;
	Article livreDemat1 = null;

	Article diversMat1 = null;
	Article diversMatOccas1 = null;
	Article diversDemat1 = null;

	@Before
	public void setUp() throws Exception {

		Compte compte1 = new Compte("mag", "pwd");

		livreMat1 = new Livre("livreMat1", 25.0, "seigneur des anneaux1", 50, "Tolkien", "575");
		livreMatOccas1 = new Livre("livreMatOccas1", 20.0, "seigneur des anneaux2", 60, Etat.MOYEN, "Tolkien", "575");
		livreDemat1 = new Livre("livreDemat1", 4.0, "seigneurs des anneaux1", "kindle", "https://kindle1.com",
				"Tolkien", "123");

		diversMat1 = new ArticleDivers("diversMat1", "dvd", 10.0, "seigneur des anneaux1", 3);
		diversMatOccas1 = new ArticleDivers("diversMat1", "dvd", 10.0, "seigneur des anneaux1", 3);
		diversDemat1 = new ArticleDivers("diversDemat1", "album photo", 5.0, "anneau", 1);

		// System.out.println(livre1);

		panier = new Panier(compte1);

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testprixtotal() throws StockException, DematerialiseException {

		panier.ajouter(livreMat1, 2);
		panier.ajouter(livreMatOccas1, 1);

		// assertEquals("prix total", 70.0, panier.getPrixTotal(), 1);
		assertThat("prix total", 70.0, is(panier.getPrixTotal()));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testajout_negatif() throws StockException, DematerialiseException {
		panier.ajouter(diversDemat1, -1);
	}

	@Test(expected = StockException.class)
	public void testverifstock() throws StockException, DematerialiseException {

		panier.ajouter(livreMat1, 100);

	}

	@Test
	public void testnbarticledifferent() {

		try {
			panier.ajouter(livreMat1, 5);
			panier.ajouter(diversMat1, 2);
		} catch (StockException | DematerialiseException e) {
		
		}
		
		int n = 0;
		Iterator<LignePanier> iterator = panier.iterator();
		while (iterator.hasNext()) {
			n++;
			iterator.next();
		}
		assertThat("nb articles différents", n, is(2));

	}
	
	@Test
	public void testajout_livre_quantite() {
		try {
			panier.ajouter(diversMat1, 1);
			panier.ajouter(livreMat1, 7);
			panier.ajouter(diversDemat1, 1);
			panier.ajouter(livreDemat1, 1);
		} catch (StockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DematerialiseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int qte = 0;
		Iterator<LignePanier> iterator = panier.iterator();
		while (iterator.hasNext()) {
			LignePanier lp = iterator.next();
			if(lp.getArticle().equals(livreMat1)) {
				qte = lp.getQuantite();
			}
		}
		assertThat("ajout quantité d'un article existant", qte, is(7));

	}
	
	@Test
	public void testajout_dematerialise() {
		Article articleDemat = null;
		try {
			panier.ajouter(livreDemat1, 5);
		} catch (StockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DematerialiseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int qte = 0;
			
		Iterator<LignePanier> iterator = panier.iterator();
		while (iterator.hasNext()) {
			LignePanier lp = iterator.next();
			if(lp.getArticle().equals(livreDemat1)) {
				qte = lp.getQuantite();
			}
		}
		assertThat("ajout quantité > 1 d'un article dematerialise", qte, is(0));
					
			
	}	
	
	@Test
	public void testarticle_ajoute() {
		Article article = null;
		try {
			panier.ajouter(diversMat1, 1);
			panier.ajouter(livreMat1, 7);
			panier.ajouter(diversDemat1, 1);
			panier.ajouter(livreDemat1, 1);
		} catch (StockException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DematerialiseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int qte = 0;
			
		Iterator<LignePanier> iterator = panier.iterator();
		while (iterator.hasNext()) {
			LignePanier lp = iterator.next();
			if(lp.getArticle().equals(diversMat1)) {
				article = diversMat1;
			}
		}
		assertNotNull("article inséré", article);
							
	}	
	
	
	

	
}
