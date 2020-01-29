package mesClassesMetier;

import java.util.Date;

import oracle.sql.DATE;

public class Hebergement {
	int idHebergement;
	Date dateDePublication;
	String nomCommercial;
	Date dateDeClassement;
	String nomCategorie;
	String classement;
	String adresse;
	String codePostal;
	String commune;
	String numTel;
	String courreil;
	String siteNet;
	float coordonnee1;
	float coordonnee2;
	String nomDep;
	String nomRegion;
	String Description;
	Date dateAjout;
	String idUtilisateur;
	
	
	public Hebergement(int idHebergement, Date dateDePublication, String nomCommercial, Date dateDeClassement,
			String nomCategorie, String classement, String adresse, String codePostal, String commune,
			String numTel, String courreil, String siteNet, float coordonnee1, float coordonnee2, String nomDep,
			String nomRegion, String description, Date dateAjout, String idUtilisateur) {
		super();
		this.idHebergement = idHebergement;
		this.dateDePublication = dateDePublication;
		this.nomCommercial = nomCommercial;
		this.dateDeClassement = dateDeClassement;
		this.nomCategorie = nomCategorie;
		this.classement = classement;
		this.adresse = adresse;
	
		this.codePostal = codePostal;
		this.commune = commune;
		this.numTel = numTel;
		this.courreil = courreil;
		this.siteNet = siteNet;
		this.coordonnee1 = coordonnee1;
		this.coordonnee2 = coordonnee2;
		this.nomDep = nomDep;
		this.nomRegion = nomRegion;
		this.Description = description;
		this.dateAjout = dateAjout;
		this.idUtilisateur = idUtilisateur;
	}


	public int getIdHebergement() {
		return idHebergement;
	}


	public void setIdHebergement(int idHebergement) {
		this.idHebergement = idHebergement;
	}


	public Date getDateDePublication() {
		return dateDePublication;
	}


	public void setDateDePublication(Date dateDePublication) {
		this.dateDePublication = dateDePublication;
	}


	public String getNomCommercial() {
		return nomCommercial;
	}


	public void setNomCommercial(String nomCommercial) {
		this.nomCommercial = nomCommercial;
	}


	public Date getDateDeClassement() {
		return dateDeClassement;
	}


	public void setDateDeClassement(Date dateDeClassement) {
		this.dateDeClassement = dateDeClassement;
	}


	public String getNomCategorie() {
		return nomCategorie;
	}


	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}


	public String getClassement() {
		return classement;
	}


	public void setClassement(String classement) {
		this.classement = classement;
	}


	public String getAdresse() {
		return adresse;
	}


	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}



	public String getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	public String getCommune() {
		return commune;
	}


	public void setCommune(String commune) {
		this.commune = commune;
	}


	public String getNumTel() {
		return numTel;
	}


	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}


	public String getCourreil() {
		return courreil;
	}


	public void setCourreil(String courreil) {
		this.courreil = courreil;
	}


	public String getSiteNet() {
		return siteNet;
	}


	public void setSiteNet(String siteNet) {
		this.siteNet = siteNet;
	}


	public double getCoordonnee1() {
		return coordonnee1;
	}


	public void setCoordonnees(float coordonnee1) {
		this.coordonnee1 = coordonnee1;
	}


	public double getCoordonnee2() {
		return coordonnee2;
	}


	public void setNomEPCI(float coordonnee2) {
		this.coordonnee2 = coordonnee2;
	}


	public String getNomDep() {
		return nomDep;
	}


	public void setNomDep(String nomDep) {
		this.nomDep = nomDep;
	}


	public String getNomRegion() {
		return nomRegion;
	}


	public void setNomRegion(String nomRegion) {
		this.nomRegion = nomRegion;
	}


	public String getDescription() {
		return Description;
	}


	public void setDescription(String description) {
		Description = description;
	}


	public Date getDateAjout() {
		return dateAjout;
	}


	public void setDateAjout(Date dateAjout) {
		this.dateAjout = dateAjout;
	}


	public String getIdUtilisateur() {
		return idUtilisateur;
	}


	public void setIdUtilisateur(String idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	
	
	
	
}