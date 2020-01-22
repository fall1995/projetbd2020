package mesClasses;

public class LesUtilisateurs {
	private String idUtilisateur;
	private String nom;
	private String  prenom;
	
	
	
	
	public LesUtilisateurs(String idUtilisateur, String nomUtilisateur, String nom, String prenom, String mail,
			String tel, String adresse) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		
	}
	
	public String getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
