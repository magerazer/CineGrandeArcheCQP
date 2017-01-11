package fr.demos.projet.controleur;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.demos.projet.data.ArticleDAOMySQL;

import fr.demos.projet.metier.Article;
import fr.demos.projet.metier.ArticleInconnuException;
import fr.demos.projet.metier.DematerialiseException;
import fr.demos.projet.metier.LignePanier;
import fr.demos.projet.metier.Panier;
import fr.demos.projet.metier.StockException;

/**
 * Servlet implementation class PanierControleur
 */
@WebServlet("/PanierControleur")
public class PanierControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PanierControleur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Panier panier = (Panier) request.getSession().getAttribute("panier");
		//int prixTotal = panier.getPrixTotal();
		//request.setAttribute("prixTotal", prixTotal);
		RequestDispatcher rd = request.getRequestDispatcher("/PanierVue.jsp");
		rd.forward(request, response);
		session.setAttribute("pageCourante", "/PanierVue.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		ArticleDAOMySQL articleDao = (ArticleDAOMySQL) request.getServletContext().getAttribute("articleDao");
		// List<Article> liste = d.remplirCatalogue();
	
		Panier panier = (Panier) session.getAttribute("panier");
		// permet de se repositionner sur la page à partir de laquelle on a
		// ajoute l'article
		String ajouter = request.getParameter("ajouter");				
		String modifier = request.getParameter("modifierPanier");
		String delete = request.getParameter("delete");

		String consulter = request.getParameter("consulter");
		
		String reference = request.getParameter("ref");
		
		Article a = articleDao.selectArticle(reference);
		/*
		 * gestion des erreurs avec la HashMap
		 */
		 Map<Article, String> erreurs = new HashMap<Article, String>();
		request.setAttribute("erreurs", erreurs);
		
		
		if (ajouter != null || modifier != null) {
			String qteStr = request.getParameter("qte");

			int qte = 0;
			try {
				qte = Integer.parseInt(qteStr);
			} catch (NumberFormatException ex) {
				// gestion erreur conversion
			}
			//System.out.println("panier rempli : " + panier);
			
			//System.out.println("article rempli : " + a);
						try {
				if(ajouter != null)
					panier.ajouter(a, qte);
				if(modifier != null)
					panier.modifierQte(a, qte);
				
			} catch (StockException e) {
				erreurs.put(a, e.getMessage());				
			} catch (ArticleInconnuException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DematerialiseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
//		ArrayList<LignePanier> l = panier.getPanier();
//		session.setAttribute("lignes", l);
//			
		
		RequestDispatcher rd = null;
		
		if (consulter != null) {
			if (consulter.equals("false")) {

				rd = request.getRequestDispatcher("/ListeArticlesVue.jsp");
			} else{// garder l'article sur lequel on etait positionne

				System.out.println("ref = " + reference);
				
				request.setAttribute("article", a);
				rd = request.getRequestDispatcher("/ArticleVue.jsp");
			} 
		} 
		
		if(modifier != null) {

			rd = request.getRequestDispatcher("/PanierVue.jsp");

		}
		
		if(delete != null) {
			panier.supprimerLigne(a);
			rd = request.getRequestDispatcher("/PanierVue.jsp");

		}
		
		rd.forward(request, response);
		
	}

}
