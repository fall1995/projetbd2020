package mesClasses;

public class LesUtilisateurs {
	private String idUtilisateur;
	String nomUtilisateur;
	private String nom;
	private String  prenom;
	String mail;
	String tel;
	String adresse;
	public LesUtilisateurs(String idUtilisateur, String nomUtilisateur, String nom, String prenom, String mail,
			String tel, String adresse) {
		super();
		this.idUtilisateur = idUtilisateur;
		this.nomUtilisateur = nomUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.mail = mail;
		this.tel = tel;
		this.adresse = adresse;
	}
	public String getIdUtilisateur() {
		return idUtilisateur;
	}
	public void setIdUtilisateur(String idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
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
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
	
	
	

}
