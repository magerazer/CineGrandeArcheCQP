package fr.demos.projet.controleur.listener;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import fr.demos.projet.data.ArticleDAO;
import fr.demos.projet.data.ArticleDAOMySQL;
import fr.demos.projet.data.CompteDAOMySQL;
import fr.demos.projet.metier.Panier;

/**
 * Application Lifecycle Listener implementation class PanierListener
 *
 */
@WebListener
public class PanierListener implements ServletContextListener, HttpSessionListener {

	@Inject private ArticleDAO dao;
	
    /**
     * Default constructor. 
     */
    public PanierListener() {
       
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
    	
		//List<Article> listeArticles = donnees.remplirCatalogue();
    
    	
    	
		
	//	arg0.getSession().setAttribute("donnees", donnees);
//		arg0.getSession().setAttribute("listeArticles", listeArticles);
    //	arg0.getSession().setAttribute("listeComptes", comptes);
		
    	Panier panier = new Panier();      	
    	arg0.getSession().setAttribute("panier", panier);
//    	arg0.getSession().setAttribute("qtePanier", panier.getPanier().size());
    	
    	arg0.getSession().setAttribute("pageCourante", "/ListeArticlesVue.jsp");
    	
		System.out.println("Session creee");
		
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
        
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
       
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  {     	
    //	ArticleDAOMySQL articleDao = null;
    	CompteDAOMySQL compteDao = null;
		try {
		//	articleDao = new ArticleDAOMySQL();
			compteDao = new CompteDAOMySQL();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
    	
    	
		arg0.getServletContext().setAttribute("articleDao", dao);
		arg0.getServletContext().setAttribute("compteDao", compteDao);
		
		
		
    }
	
}
