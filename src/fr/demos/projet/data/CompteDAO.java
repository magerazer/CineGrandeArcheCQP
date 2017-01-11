package fr.demos.projet.data;

import java.util.List;

import fr.demos.projet.metier.Compte;

public interface CompteDAO {

	void insert (Compte c) throws Exception;
	void update(Compte c) throws Exception;
	void delete(Compte c) throws Exception;
	Compte select(String mail, String pwd);
	
}
