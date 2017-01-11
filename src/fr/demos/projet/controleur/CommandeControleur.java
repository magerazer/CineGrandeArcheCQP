package fr.demos.projet.controleur;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.demos.projet.data.ArticleDAOMySQL;
import fr.demos.projet.metier.LoginException;
import fr.demos.projet.metier.Commande;
import fr.demos.projet.metier.Compte;
import fr.demos.projet.metier.Panier;
import fr.demos.projet.metier.PassageCommande;

/**
 * Servlet implementation class CommandeControleur
 */
@WebServlet("/CommandeControleur")
public class CommandeControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommandeControleur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Panier p = (Panier) session.getAttribute("panier");

		Compte compte = (Compte) session.getAttribute("compte");
		// ArticleDAOMySQL articleDao = (ArticleDAOMySQL)
		// request.getServletContext().getAttribute("articleDao");

		Commande com = null;
		PassageCommande creationCommande = null;
		boolean erreurConnecte = false;
		try {
			creationCommande = new PassageCommande(p);
			com = creationCommande.creerCommande(compte);
		} catch (LoginException e) {
			// TODO Auto-generated catch block
			erreurConnecte = true;
			request.setAttribute("nonConnecte", "Vous devez vous connecter pour commander.");
			RequestDispatcher rd = request.getRequestDispatcher("/PanierVue.jsp");
			rd.forward(request, response);
		}
		if (creationCommande.getErreursCommandeStock().size() > 0) {
			erreurConnecte = true;
			request.setAttribute("erreurStock", creationCommande.getErreursCommandeStock());
			RequestDispatcher rd = request.getRequestDispatcher("/PanierVue.jsp");
			rd.forward(request, response);
		}
		if (!erreurConnecte) {
			request.setAttribute("tot", p.getPrixTotal());
			request.setAttribute("commande", com);

			session.setAttribute("panier", new Panier());
			// System.out.println("quantite panier = " + p.getQuantite());
			RequestDispatcher rd = request.getRequestDispatcher("/CommandeVue.jsp");
			rd.forward(request, response);
		}
	}

}
