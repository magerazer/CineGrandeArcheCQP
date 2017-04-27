package fr.demos.formation.metier.test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import fr.demos.projet.metier.Livre;
import fr.demos.projet.metier.Materialise;
import fr.demos.projet.metier.DematerialiseException;
import fr.demos.projet.metier.Etat;
import fr.demos.projet.metier.LignePanier;
import fr.demos.projet.metier.Panier;
import fr.demos.projet.metier.StockException;

import static org.mockito.Mockito.*;

import java.util.Iterator;

public class MockFirst {

	private Panier panier;
	private Livre livreMock1;
	private Livre livreMock2;
	
	@Before
	public void setUp() throws Exception {
		panier = new Panier();
		livreMock1 = mock(Livre.class);
		when(livreMock1.getPrixHT()).thenReturn(12.0);
		livreMock2 = mock(Livre.class);
		when(livreMock2.getPrixHT()).thenReturn(33.0);
		
		Materialise mat1 = mock(Materialise.class);
		when(mat1.getStock()).thenReturn(100);
		
		when(livreMock1.getMat()).thenReturn(mat1);
		when(livreMock2.getMat()).thenReturn(mat1);
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPrix() {
		
			try {
				panier.ajouter(livreMock1, 1);
				panier.ajouter(livreMock2, 2);
			} catch (StockException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DematerialiseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
		assertEquals(78, panier.getPrixTotal(), 0.01);
		
		verify(livreMock1, atLeast(2)).getPrixHT();
	}
	
	
	@Test
	public void testnbarticledifferents() {

		try {
			panier.ajouter(livreMock1, 5);
			panier.ajouter(livreMock2, 2);
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
			panier.ajouter(livreMock1, 1);
			panier.ajouter(livreMock2, 7);
//			panier.ajouter(, 1);
//			panier.ajouter(, 1);
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
			if(lp.getArticle().equals(livreMock1)) {
				qte = lp.getQuantite();
			}
		}
		assertThat("ajout quantité d'un article existant", qte, is(1));

	}
	
	
	

}
