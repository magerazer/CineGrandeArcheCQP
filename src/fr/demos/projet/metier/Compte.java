package fr.demos.projet.metier;

public class Compte {
	
	private String nom;
	private String prenom;
	private String mail;
	private String pwd;
	private String adrFact;
	private String adrLiv;
	
	public Compte() {
		
	}
	
	public Compte(String mail, String pwd) {
		this.mail = mail;
		this.pwd = pwd;
	}
	
	public Compte(String nom, String prenom, String mail, String pwd, String liv, String fact) {
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.pwd = pwd;
		this.adrFact = fact;
		this.adrLiv = liv;
	}

	public String getMail() {
		return mail;
	}


	public void setMail(String mail) {
		this.mail = mail;
	}

	
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	
	
	public String getAdrFact() {
		return adrFact;
	}

	public void setAdrFact(String adrFact) {
		this.adrFact = adrFact;
	}

	public String getAdrLiv() {
		return adrLiv;
	}

	public void setAdrLiv(String adrLiv) {
		this.adrLiv = adrLiv;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
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
		Compte other = (Compte) obj;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		return true;
	}
	
}
