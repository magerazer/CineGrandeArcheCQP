package fr.demos.projet.controleur;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
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
import fr.demos.projet.metier.Panier;
import fr.demos.projet.metier.StockException;

/**
 * Servlet implementation class PanierAsync
 */
@WebServlet("/PanierAsync")
public class PanierAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Inject Panier panier;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PanierAsync() {
        super();
        // TODO Auto-generated constr  uctor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=================================== ici");
		PrintWriter out = response.getWriter();
		
		String total = request.getParameter("total");
		
		
		HttpSession session = request.getSession();
		ArticleDAOMySQL articleDao = (ArticleDAOMySQL) request.getServletContext().getAttribute("articleDao");
		// List<Article> liste = d.remplirCatalogue();
	
		//Panier panier = (Panier) session.getAttribute("panier");
		// permet de se repositionner sur la page à partir de laquelle on a
		// ajoute l'article
		String ajouter = request.getParameter("ajouter");				
		String modifier = request.getParameter("modifier");
		String delete = request.getParameter("delete");

		String consulter = request.getParameter("consulter");
		
		System.out.println("ref ajax : " + request.getParameter("ref") + " qte = " +  request.getParameter("qte"));
		/* 
		 * on sélectionne en AJAX la ref et qte
		 */
		String reference = request.getParameter("ref");
		String qteStr = request.getParameter("qte");
		
		
		
		Article a = articleDao.selectArticle(reference);
		/*
		 * gestion des erreurs avec la HashMap
		 */
		 Map<Article, String> erreurs = new HashMap<Article, String>();
		request.setAttribute("erreurs", erreurs);
		
//		if(modifier != null && ajouter != null)
		if ((modifier != null && modifier.equals("true")) || (ajouter != null && ajouter.equals("true")) ) {
			//String qteStr = request.getParameter("qte");

			int qte = 0;
			try {
				qte = Integer.parseInt(qteStr);
			} catch (NumberFormatException ex) {
				// gestion erreur conversion
			}
			//System.out.println("panier rempli : " + panier);
			
			//System.out.println("article rempli : " + a);
						try {
				if(ajouter != null && ajouter.equals("true"))
					panier.ajouter(a, qte);
				if(modifier != null && modifier.equals("true"))
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
		
		if (consulter != null) {
			if (consulter.equals("false")) {

			} else{// garder l'article sur lequel on etait positionne

				System.out.println("ref = " + reference);
				
				request.setAttribute("article", a);
			} 
		} 
		
		if(modifier != null && modifier.equals("true")) {
		//	out.println(panier.getPrixTotal() + ":" + panier.getQuantite());
			out.println("{"
					+ "\"prixTotal\":"+ panier.getPrixTotal()+","
					+ "\"qte\":"+panier.getQuantite()
							+ "}");

		}
		
		if(ajouter != null && ajouter.equals("true")) {
			//out.println(panier.getQuantite());
			response.setContentType("application/json");
			out.println("{"
					+ "\"qte\":\""+ panier.getQuantite()+"\""
							+ "}");
			
		}
		
		if(delete != null) {
			panier.supprimerLigne(a);

		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
