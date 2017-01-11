package fr.demos.projet.metier;

import fr.demos.projet.data.CompteDAOMySQL;

public class Authentification {

	public Compte login(String mail, String pwd) throws LoginException {
		// liaison avec la base de données
				
    	CompteDAOMySQL compteDao = null;
		try {			
			compteDao = new CompteDAOMySQL();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
		}
		
		Compte compte = compteDao.select(mail, pwd);    	
    	
		/*
		 * gestion de la saisie du mail et du mot de passe renvoyer une
		 * erreur si la saisie est incorrecte 
		 */
		if (compte == null) {
			throw new LoginException("Les informations saisies sont incorrectes.");
		}
		
		
		return compte;
		
	}	
}
