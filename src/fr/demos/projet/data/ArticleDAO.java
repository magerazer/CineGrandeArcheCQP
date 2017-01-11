package fr.demos.projet.data;

import java.util.List;

import fr.demos.projet.metier.Article;

public interface ArticleDAO {

	void insert (Article a) throws Exception;
	void update(Article a) throws Exception;
	void delete(Article a) throws Exception;
	List<Article> select(String critere);
	Article selectArticle(String reference);
	void updateStock(Article a, int qte) throws Exception;
	
}
