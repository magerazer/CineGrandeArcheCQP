package fr.demos.projet.controleur;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.demos.projet.data.CompteDAOMySQL;

import fr.demos.projet.metier.Authentification;
import fr.demos.projet.metier.Compte;
import fr.demos.projet.metier.CreationCompte;
import fr.demos.projet.metier.LoginException;
import fr.demos.projet.metier.UtilisateurException;

/**
 * Servlet implementation class CompteControleur
 */
@WebServlet("/CompteControleur")
public class CompteControleur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompteControleur() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//
//		RequestDispatcher rd = request.getRequestDispatcher("/ListeArticlesVue.jsp");
//		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		String pageCourante = (String) session.getAttribute("pageCourante");

		// Map<String, String> erreursConnexion = new HashMap<String, String>();
		// request.setAttribute("erreursConnexion", erreursConnexion);
		Map<String, String> erreursCompte = new HashMap<String, String>();
		request.setAttribute("erreursCompte", erreursCompte);

		String connexion = request.getParameter("connexion");
		String deconnexion = request.getParameter("deconnexion");
		String creationCompte = request.getParameter("creationCompte");
		String validerCompte = request.getParameter("validerCompte");
		String consulterCompte = request.getParameter("consulterCompte");
		String modifierCompte = request.getParameter("modifierCompte");
		String modifier = request.getParameter("modifier");
		
		// Compte compte = null;
		// ArrayList<Compte> listeComptes = (ArrayList<Compte>)
		// session.getAttribute("listeComptes");
		CompteDAOMySQL compteDao = (CompteDAOMySQL) request.getServletContext().getAttribute("compteDao");
		boolean erreur = false;

		// si l'utilisateur se connecte via le menu
		if (connexion != null) {

			String mail = request.getParameter("mail");
			String pwd = request.getParameter("pwd");

			Compte compte = null;
			try {
				compte = new Authentification().login(mail, pwd);
			} catch (LoginException e) {
				String loginErreur = e.getMessage();
				request.setAttribute("loginErreur", loginErreur);
			}

			session.setAttribute("compte", compte);

			RequestDispatcher rd = request.getRequestDispatcher(pageCourante);
			rd.forward(request, response);
		}
		// si l'utilisateur se déconnecte
		if (deconnexion != null) {
			session.setAttribute("compte", null);

			RequestDispatcher rd = request.getRequestDispatcher(pageCourante);
			rd.forward(request, response);
		}
		// si l'utilisateur veut créer un compte et qu'il n'est pas connecté
		if (creationCompte != null) {

			RequestDispatcher rd = request.getRequestDispatcher("/CreerCompte.jsp");
			rd.forward(request, response);
		}
		/*
		 * Lorsque l'utilisateur valide le compte qu'il vient de créer
		 */
		if (validerCompte != null) {
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			String pwdConfirm = request.getParameter("pwdConfirm");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String adrFact = request.getParameter("adrFact");
			String adrLiv = request.getParameter("adrLiv");

			
			
			CreationCompte creeCompte = new CreationCompte(false);
			Compte compte = creeCompte.creationCompte(nom, prenom, email, pwd, pwdConfirm, adrLiv, adrFact);

			erreursCompte = creeCompte.getErreurs();

			request.setAttribute("erreursCompte", erreursCompte);
			// Compte c = new Compte(nom, prenom, email, pwd, adrFact, adrLiv);
			if (erreursCompte.size() == 0) {
				try {
					request.setAttribute("consultationCompte", true);
					request.setAttribute("modifier", true);
					request.setAttribute("validationConfirm", true);
					request.setAttribute("confirmCompte", compte);
					compteDao.insert(compte);					
						
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
			
			RequestDispatcher rd = request.getRequestDispatcher("/CreerCompte.jsp");
			rd.forward(request, response);
		}
		
		if (consulterCompte != null) {			
			request.setAttribute("consultationCompte", true);
			RequestDispatcher rd = request.getRequestDispatcher("/CreerCompte.jsp");
			rd.forward(request, response);
		}
		
		if (modifierCompte != null) {			
			request.setAttribute("consultationCompte", false);
			
			request.setAttribute("modifier", true);
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher("/CreerCompte.jsp");
			rd.forward(request, response);
		}
		
		if (modifier != null) {			
						
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			String pwdConfirm = request.getParameter("pwdConfirm");
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String adrFact = request.getParameter("adrFact");
			String adrLiv = request.getParameter("adrLiv");

			
			
			CreationCompte creeCompte = new CreationCompte(true);
			Compte compte = creeCompte.creationCompte(nom, prenom, email, pwd, pwdConfirm, adrLiv, adrFact);

			erreursCompte = creeCompte.getErreurs();

			request.setAttribute("erreursCompte", erreursCompte);
			// Compte c = new Compte(nom, prenom, email, pwd, adrFact, adrLiv);
			if (erreursCompte.size() == 0) {
				try {
					request.setAttribute("consultationCompte", true);					
						compteDao.update(compte);	
						session.setAttribute("compte", compte);
						
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
			
			RequestDispatcher rd = request.getRequestDispatcher("/CreerCompte.jsp");
			rd.forward(request, response);
		}

	}

}
