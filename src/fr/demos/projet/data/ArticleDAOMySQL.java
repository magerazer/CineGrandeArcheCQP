package fr.demos.projet.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.naming.Context;
import javax.sql.DataSource;

import fr.demos.projet.metier.Article;
import fr.demos.projet.metier.ArticleDivers;
import fr.demos.projet.metier.Etat;
import fr.demos.projet.metier.Livre;
import fr.demos.projet.metier.StockException;

public class ArticleDAOMySQL implements ArticleDAO {
	
	private Context context;
	@Resource(name="jdbc/CineGrandeArche") 
	private DataSource dataSource;

	public ArticleDAOMySQL() throws Exception {
		/*
		context = new InitialContext
				();
		dataSource = (DataSource) context.lookup("java:comp/env/jdbc/CineGrandeArche");
		*/
	}
	
	@Override
	public void insert(Article a) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public synchronized void update(Article a) throws Exception {
		// TODO Auto-generated method stub
		try (Connection cx = dataSource.getConnection()) {
			PreparedStatement pstm = null;

			pstm = cx.prepareStatement("UPDATE article SET stock = ? " + "WHERE reference = ?");

			// pstm.setString(1, a.getMat().getStock() - );
			// pstm.setString(2, crit);

			ResultSet rs = pstm.executeQuery();

		} 
	}

	@Override
	public synchronized void  updateStock(Article a, int qte) throws Exception, StockException {
		// TODO Auto-generated method stub
		try (Connection cx = dataSource.getConnection()) {
			PreparedStatement pstm = null;
			
			pstm = cx.prepareStatement("SELECT stock FROM article WHERE reference = ? ");
			pstm.setString(1, a.getRef());
			ResultSet rs = pstm.executeQuery();
			int stock = 0;
			if(rs.next()) {
				stock = rs.getInt("stock");
			}
			
			if(stock - qte < 0) {
				System.out.println("Le stock - qte < 0");
				throw new StockException(stock, qte);
			}
			
			pstm = cx.prepareStatement("UPDATE article SET stock = ? " + "WHERE reference = ? "
					+ "AND (stock - ?) ");

			pstm.setInt(1, a.getMat().getStock() - qte);
			pstm.setString(2, a.getRef());
			pstm.setInt(3, qte);
			
			pstm.executeUpdate();

		}
	}

	@Override
	public void delete(Article a) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Article> select(String critere) {
		// TODO Auto-generated method stub
		List<Article> liste = new ArrayList<>();

		try (Connection cx = dataSource.getConnection()) {
			PreparedStatement pstm = null;
			PreparedStatement pstmDivers = null;
			if (critere.equals("")) {
				pstm = cx.prepareStatement("SELECT referenceLivre, prix, nom, stock, description, image, type, stock "
						+ ", etat, coutLivraison, formatLivre, format, url, isbn, auteur, dateParution, genre, nbPages "
						+ " FROM Livre INNER JOIN Article " + "ON Article.reference = Livre.referenceLivre "
						+ "ORDER BY reference DESC");

				pstmDivers = cx.prepareStatement("SELECT referenceDivers, prix, nom, description, image, type, stock "
						+ ", etat, coutLivraison, format, url, typeDivers, caracteristique "
						+ " FROM Divers INNER JOIN Article " + "ON Article.reference = Divers.referenceDivers "
						+ "ORDER BY reference DESC");

			} else {
				pstm = cx.prepareStatement("SELECT referenceLivre, prix, nom, stock, description, image, type, stock "
						+ ", etat, coutLivraison, formatLivre, format, url, isbn, auteur, dateParution, genre, nbPages"
						+ " FROM Livre INNER JOIN Article " + "ON Article.reference = Livre.referenceLivre "
						+ "WHERE auteur LIKE ? || nom LIKE ? || description LIKE ? "
						+ "|| formatLivre LIKE ? || format LIKE ? || url LIKE ? " + "ORDER BY reference DESC");

				pstmDivers = cx.prepareStatement("SELECT referenceDivers, prix, nom, description, image, type, stock "
						+ ", etat, coutLivraison, format, url, typeDivers, caracteristique "
						+ " FROM Divers INNER JOIN Article " + "ON Article.reference = Divers.referenceDivers "
						+ "WHERE nom LIKE ? || description LIKE ? " + "|| format LIKE ? || url LIKE ? "
						+ " || type LIKE ? || caracteristique LIKE ? " + "ORDER BY reference DESC");

				String crit = "%" + critere + "%";
				pstm.setString(1, crit);
				pstm.setString(2, crit);
				pstm.setString(3, crit);
				pstm.setString(4, crit);
				pstm.setString(5, crit);
				pstm.setString(6, crit);
				
				pstmDivers.setString(1, crit);
				pstmDivers.setString(2, crit);
				pstmDivers.setString(3, crit);
				pstmDivers.setString(4, crit);
				pstmDivers.setString(5, crit);
				pstmDivers.setString(6, crit);
			}

			ResultSet rs = pstm.executeQuery();

			while (rs.next()) {

				Livre liv = creationLivre(rs);

				liste.add(liv);

			}

			ResultSet rsDivers = pstmDivers.executeQuery();

			while (rsDivers.next()) {

				ArticleDivers divers = creationDivers(rsDivers);

				liste.add(divers);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return liste;
	}

	public Livre creationLivre(ResultSet rs) throws SQLException {
		String reference = rs.getString("referenceLivre");
		double prix = rs.getDouble("prix");
		String nom = rs.getString("nom");
		String description = rs.getString("description");
		String image = rs.getString("image");

		String type = rs.getString("type");

		// attributs communs aux livres
		String isbn = rs.getString("isbn");
		String auteur = rs.getString("auteur");
		String formatLivre = rs.getString("formatLivre");
		String genre = rs.getString("genre");
		int nbPages = rs.getInt("nbPages");
		LocalDate dateParution = null;
		if (rs.getDate("dateParution") != null)
			dateParution = rs.getDate("dateParution").toLocalDate();

		Livre liv = null;

		// dans le cas d'un livre materialisé
		if (rs.getString("url").equals("")) {

			int stock = rs.getInt("stock");
			Etat etat = null;
			if (rs.getString("etat") != null) {
				etat = Etat.valueOf(rs.getString("etat"));				
			}
			int coutLivraison = rs.getInt("coutLivraison");

			liv = new Livre(reference, prix, nom, stock, etat, auteur, isbn);

		}
		// dans le cas d'un livre dematérialisé
		else {

			String format = rs.getString("format");
			String url = rs.getString("url");

			liv = new Livre(reference, prix, nom, format, url, auteur, isbn);

		}
		// attributs communs aux livres materialises et dematerialises
		liv.setImage(image);
		liv.setDescription(description);
		liv.setGenre(genre);
		liv.setNbPages(nbPages);
		liv.setDateParution(dateParution);
		liv.setFormat(formatLivre);
		return liv;
	}

	public ArticleDivers creationDivers(ResultSet rs) throws SQLException {
		// attributs communs à tous les articles
		String reference = rs.getString("referenceDivers");
		double prix = rs.getDouble("prix");
		String nom = rs.getString("nom");
		String description = rs.getString("description");
		String image = rs.getString("image");

		String type = rs.getString("type");

		// attributs communs aux articles divers
		String typeDivers = rs.getString("typeDivers");
		String caracteristiques = rs.getString("caracteristique");

		ArticleDivers div = null;

		// dans le cas d'un article divers materialisé
		if (rs.getString("url").equals("")) {

			int stock = rs.getInt("stock");
			Etat etat = null;
			if (rs.getString("etat") != null) {
				etat = Etat.valueOf(rs.getString("etat"));
			}
			int coutLivraison = rs.getInt("coutLivraison");

			div = new ArticleDivers(reference, typeDivers, prix, nom, stock);

		}
		// dans le cas d'un article divers dematérialisé
		else {

			String format = rs.getString("format");
			String url = rs.getString("url");

			div = new ArticleDivers(reference, prix, nom, format, url, typeDivers, caracteristiques);

		}
		// attributs communs aux articles divers materialises et dematerialises
		div.setImage(image);
		div.setDescription(description);
		div.setCaracteristiques(caracteristiques);

		return div;
	}

	@Override
	public Article selectArticle(String reference) {
		// TODO Auto-generated method stub
		Article art = null;

		try (Connection cx = dataSource.getConnection()) {
			PreparedStatement pstm = null;
			PreparedStatement pstmDivers = null;

			pstm = cx.prepareStatement("SELECT referenceLivre, prix, nom, stock, description, image, type, stock "
					+ ", etat, coutLivraison, formatLivre, format, url, isbn, auteur, dateParution, genre, nbPages "
					+ " FROM Livre INNER JOIN Article " + "ON Article.reference = Livre.referenceLivre "
					+ "WHERE referenceLivre = ? ");
			pstm.setString(1, reference);

			pstmDivers = cx.prepareStatement("SELECT referenceDivers, prix, nom, description, image, type, stock "
					+ ", etat, coutLivraison, format, url, typeDivers, caracteristique "
					+ " FROM Divers INNER JOIN Article " 
					+ "ON Article.reference = Divers.referenceDivers "
					+ "WHERE referenceDivers = ? ");

			pstmDivers.setString(1, reference);

			ResultSet rs = pstm.executeQuery();
			ResultSet rs2 = pstmDivers.executeQuery();
					
			
			if(rs.next()) {
			if (rs.getString("type").equals("livre")) {
				art = creationLivre(rs);
			}
			}
			if(rs2.next()) {
			if (rs2.getString("type").equals("divers"))
				art = creationDivers(rs2);
			}
			System.out.println("article null ? = " + art);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return art;
	}

}
