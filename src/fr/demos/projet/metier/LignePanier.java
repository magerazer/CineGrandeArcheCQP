package fr.demos.projet.metier;

public class LignePanier {

	private Article article;
	private int quantite; 
	
	public LignePanier(Article a, int quantite) {
		this.article = a;
		this.quantite = quantite;
	}

	

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}



	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	
	
	@Override
	public String toString() {
		return "LignePanier [article=" + article + ", quantite=" + quantite + "]";
	}


	public double getPrixTot() {
		double tot = 0;
		tot = quantite * article.getPrixHT();
		tot = Math.rint(tot*100)/100;
		return tot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((article == null) ? 0 : article.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LignePanier other = (LignePanier) obj;
		if (article == null) {
			if (other.article != null)
				return false;
		} else if (!article.equals(other.article))
			return false;
		return true;
	}
	
	
	
}
