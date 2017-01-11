package fr.demos.projet.metier;

public class Dematerialise {

	private String format;
	private int taille;
	private String url;
	
	public Dematerialise(String format, String url) {
		this.format = format;
		this.url = url;
		
	}

	
	
	
	@Override
	public String toString() {
		return "format=" + format + ", taille=" + taille + ", url=" + url;
	}




	public String getFormat() {
		return format;
	}

	public int getTaille() {
		return taille;
	}

	public String getUrl() {
		return url;
	}

	
	
}
