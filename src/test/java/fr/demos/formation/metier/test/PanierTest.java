package fr.demos.formation.metier.test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.demos.projet.metier.Article;
import fr.demos.projet.metier.ArticleDivers;
import fr.demos.projet.metier.Compte;
import fr.demos.projet.metier.Etat;
import fr.demos.projet.metier.Livre;
import fr.demos.projet.metier.Panier;

public class PanierTest {
	
	Panier panier = null;

	@Before
	public void setUp() throws Exception {
			
		Compte compte1 = new Compte("mag", "pwd");
		
		Article livre1 = new Livre("ref1", 25.0, "seigneur des anneaux1", 50, "Tolkien", "575");
		Article livre2 = new Livre("ref2", 20.0, "seigneur des anneaux2", 60, "Tolkien", "575");
		Article livre3 = new Livre("ref3", 80.0, "seigneur des anneaux3", 70, "Tolkien", "575");
		
		Article dvd1 = new ArticleDivers("dvd1", "dvd", 10.0, "seigneur des anneaux1", 3);
		Article affiche1 = new ArticleDivers("affiche1", "pull", 5.0, "anneau", 1);
		
		Article pdf1 = new Livre("demat1", 3.0, "seigneurs des anneaux1", "pdf", "https://pdf1.com", "Tolkien", "123");
		Article kindle1 = new Livre("demat2", 4.0, "seigneurs des anneaux1", "kindle", "https://kindle1.com", "Tolkien", "123");
		 
		
		//System.out.println(livre1);				
		
		panier = new Panier(compte1);		
		
		panier.ajouter(livre1, 2);
		panier.ajouter(livre2, 1);
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testprixtotal() {
		
		assertEquals("prix total", "65.00", panier.getPrixTotal()  );
		
	}
	

}
