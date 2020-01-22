package mesClasses;

import java.sql.Date;

import oracle.sql.DATE;

public class Hebergement {
	int idHebergement;
	Date dateDePublication;
	String nomCommercial;
	Date dateDeClassement;
	String nomCategorie;
	String classement;
	String adresse;
	String categorie;
	int codePostal;
	String commune;
	int numTel;
	String courreil;
	String siteNet;
	double coordonnees;
	double nomEPCI;
	String nomDep;
	String nomRegion;
	String Description;
	Date dateAjout;
	int idUtilisateur;
	
	
	public Hebergement(int idHebergement, Date dateDePublication, String nomCommercial, Date dateDeClassement,
			String nomCategorie, String classement, String adresse, String categorie, int codePostal, String commune,
			int numTel, String courreil, String siteNet, double coordonnees, double nomEPCI, String nomDep,
			String nomRegion, String description, Date dateAjout, int idUtilisateur) {
		super();
		this.idHebergement = idHebergement;
		this.dateDePublication = dateDePublication;
		this.nomCommercial = nomCommercial;
		this.dateDeClassement = dateDeClassement;
		this.nomCategorie = nomCategorie;
		this.classement = classement;
		this.adresse = adresse;
		this.categorie = categorie;
		this.codePostal = codePostal;
		this.commune = commune;
		this.numTel = numTel;
		this.courreil = courreil;
		this.siteNet = siteNet;
		this.coordonnees = coordonnees;
		this.nomEPCI = nomEPCI;
		this.nomDep = nomDep;
		this.nomRegion = nomRegion;
		Description = description;
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


	public String getCategorie() {
		return categorie;
	}


	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}


	public int getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}


	public String getCommune() {
		return commune;
	}


	public void setCommune(String commune) {
		this.commune = commune;
	}


	public int getNumTel() {
		return numTel;
	}


	public void setNumTel(int numTel) {
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


	public double getCoordonnees() {
		return coordonnees;
	}


	public void setCoordonnees(double coordonnees) {
		this.coordonnees = coordonnees;
	}


	public double getNomEPCI() {
		return nomEPCI;
	}


	public void setNomEPCI(double nomEPCI) {
		this.nomEPCI = nomEPCI;
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


	public int getIdUtilisateur() {
		return idUtilisateur;
	}


	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	
	
	
	
}