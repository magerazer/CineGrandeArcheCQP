package fr.demos.projet.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import fr.demos.projet.metier.Compte;
import fr.demos.projet.metier.Etat;
import fr.demos.projet.metier.Livre;

public class CompteDAOMySQL implements CompteDAO {

	private Context context;
	@Resource(name="jdbc/CineGrandeArche") 
	private DataSource dataSource;

	public CompteDAOMySQL() throws Exception {
//		context = new InitialContext();
//		dataSource = (DataSource) context.lookup("java:comp/env/jdbc/CineGrandeArche");
	}

	@Override
	public void insert(Compte c) throws Exception {

		try (Connection cx = dataSource.getConnection()) {
			PreparedStatement pstm = null;
			System.out.println("avant insert");
			
			
			pstm = cx.prepareStatement("INSERT INTO compte VALUES (?, ?, ?, ?, ?, ?)");
			
			pstm.setString(1, c.getMail());
			pstm.setString(2, c.getPwd());
			pstm.setString(3, c.getNom());
			pstm.setString(4, c.getPrenom());			
			pstm.setString(5, c.getAdrLiv());
			pstm.setString(6, c.getAdrFact());

			pstm.executeUpdate();

			// ResultSet rs = pstm.executeQuery();
			//
			// while (rs.next()) {
			// String mail = rs.getString("mail");
			// String pwd = rs.getString("pwd");
			//
			// c = new Compte(mail, pwd);
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void update(Compte c) throws Exception {
		System.out.println("update ..." + c);
		try (Connection cx = dataSource.getConnection()) {
			PreparedStatement pstm = null;
			pstm = cx.prepareStatement("UPDATE compte SET "
					+ "nom = ?, "
					+ "prenom = ?, "				
					+ "mail = ?, "
					+ "pwd = ?, "
					+ "adrLiv = ?, "
					+ "adrFact = ? "
					+ "WHERE mail = ?");
			pstm.setString(1, c.getNom());
			pstm.setString(2, c.getPrenom());
			pstm.setString(3, c.getMail());
			pstm.setString(4, c.getPwd());
			pstm.setString(5, c.getAdrLiv());
			pstm.setString(6, c.getAdrFact());
			pstm.setString(7, c.getMail());
			System.out.println("update ..." + c.getAdrLiv());
			pstm.executeUpdate();
			System.out.println("update ..." + c.getAdrLiv());
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void delete(Compte c) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public Compte select(String argMail, String argPwd) {
		// TODO Auto-generated method stub
		Compte c = null;

		try (Connection cx = dataSource.getConnection()) {
			PreparedStatement pstm = null;
			pstm = cx.prepareStatement("SELECT * " 
			+ "FROM Compte" 
			+ " WHERE mail = ? AND pwd = ? ");
			pstm.setString(1, argMail);
			pstm.setString(2, argPwd);
			
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				String mail = rs.getString("mail");
				String pwd = rs.getString("pwd");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String adrLiv = rs.getString("adrLiv");
				String adrFact = rs.getString("adrFact");

				c = new Compte(nom, prenom, mail, pwd, adrLiv, adrFact);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return c;
	}
	
	public Compte selectMail(String argMail) {
		// TODO Auto-generated method stub
		Compte c = null;

		try (Connection cx = dataSource.getConnection()) {
			PreparedStatement pstm = null;
			pstm = cx.prepareStatement("SELECT * " 
			+ "FROM Compte" 
			+ " WHERE mail = ? ");
			pstm.setString(1, argMail);
			
			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {
				String mail = rs.getString("mail");
				String pwd = rs.getString("pwd");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String adrLiv = rs.getString("adrLiv");
				String adrFact = rs.getString("adrFact");

				c = new Compte(nom, prenom, mail, pwd, adrLiv, adrFact);
			}
			System.out.println("Compte c = " + c);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return c;
	}

}
