package fr.demos.projet.controleur;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.demos.projet.data.ArticleDAOMySQL;

import fr.demos.projet.metier.Article;
import fr.demos.projet.metier.Panier;

/**
 * Servlet implementation class ListeArticleControleur
 */
@WebServlet("/ListeArticlesControleur")
public class ListeArticlesControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListeArticlesControleur() {
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
		
		Panier p = (Panier) session.getAttribute("panier");
		
		ArticleDAOMySQL articleDao = (ArticleDAOMySQL) request.getServletContext().getAttribute("articleDao");
		
    	List<Article> listeArticles = articleDao.select("");    	
    	session.setAttribute("listeArticles", listeArticles);
    	
		/*
		 * Redirige vers la page de la liste des articles ou de la consultation
		 * d'un article selon que le paramètre consultation soit true ou false
		 * dans la requête
		 */
		if (request.getParameter("consultation") != null && request.getParameter("consultation").equals("true")) {
						
			
			String reference = request.getParameter("ref");
						
			Article a = articleDao.selectArticle(reference);
			System.out.println("article bdd " + a);
			session.setAttribute("article", a);
			
			
			session.setAttribute("pageCourante", "/ArticleVue.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/ArticleVue.jsp");
			rd.forward(request, response);
		} else {
			
			session.setAttribute("pageCourante", "/ListeArticlesVue.jsp");
			RequestDispatcher rd = request.getRequestDispatcher("/ListeArticlesVue.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		/*
		 * Gestion de la recherche
		 */
		String action = request.getParameter("action");
		if (action != null && action.equals("Rechercher")) {
			
			ArticleDAOMySQL articleDao = (ArticleDAOMySQL) request.getServletContext().getAttribute("articleDao");
			
			String critere = request.getParameter("critere");
			System.out.println("critere ======== " + critere);
	    	List<Article> listeArticles = articleDao.select(critere);    	
	    	session.setAttribute("listeArticles", listeArticles);
	    	
			
			RequestDispatcher rd = request.getRequestDispatcher("/ListeArticlesVue.jsp");
			rd.forward(request, response);
		}

	}

}
