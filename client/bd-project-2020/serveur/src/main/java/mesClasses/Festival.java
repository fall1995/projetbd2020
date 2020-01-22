package mesClasses;

import java.sql.Date;

public class Festival {
	
	String idFestival;
    String nomFestival ;
    String domaine;
    String complementDomaine;
    String region;
    int departement;
    String periodicite;
    String moiHabDebut;
    String siteWeb;
    String commune;
    String dateDebut;
    String dateFin;
    String dateCreation;
    int codepost;
    String codeINSEE;
    float coord1;
    float coord2;
    String nomDepartement;
    int nbPlaceLouees;
    String dateAjout;
    int idUtilisateur;
    
    

	public Festival(String idFestival, String nomFestival, String domaine, String complementDomaine, String region,
			int departement, String periodicite, String moiHabDebut, String siteWeb, String commune, String dateDebut2,
			String dateFin2, String dateCreation2, int codepost, String codeINSEE, float coord1, float coord2,
			String nomDepartement, int nbPlaceLouees, String dateAjout2, int idUtilisateur) {
		super();
		this.idFestival = idFestival;
		this.nomFestival = nomFestival;
		this.domaine = domaine;
		this.complementDomaine = complementDomaine;
		this.region = region;
		this.departement = departement;
		this.periodicite = periodicite;
		this.moiHabDebut = moiHabDebut;
		this.siteWeb = siteWeb;
		this.commune = commune;
		this.dateDebut = dateDebut2;
		this.dateFin = dateFin2;
		this.dateCreation = dateCreation2;
		this.codepost = codepost;
		this.codeINSEE = codeINSEE;
		this.coord1 = coord1;
		this.coord2 = coord2;
		this.nomDepartement = nomDepartement;
		this.nbPlaceLouees = nbPlaceLouees;
		this.dateAjout = dateAjout2;
		this.idUtilisateur = idUtilisateur;
	}


	public String getIdFestival() {
		return idFestival;
	}


	public void setIdFestival(String idFestival) {
		this.idFestival = idFestival;
	}


	public String getNomFestival() {
		return nomFestival;
	}


	public void setNomFestival(String nomFestival) {
		this.nomFestival = nomFestival;
	}


	public String getDomaine() {
		return domaine;
	}


	public void setDomaine(String domaine) {
		this.domaine = domaine;
	}


	public String getComplementDomaine() {
		return complementDomaine;
	}


	public void setComplementDomaine(String complementDomaine) {
		this.complementDomaine = complementDomaine;
	}


	public String getRegion() {
		return region;
	}


	public void setRegion(String region) {
		this.region = region;
	}


	public int getDepartement() {
		return departement;
	}


	public void setDepartement(int departement) {
		this.departement = departement;
	}


	public String getPeriodicite() {
		return periodicite;
	}


	public void setPeriodicite(String periodicite) {
		this.periodicite = periodicite;
	}


	public String getMoiHabDebut() {
		return moiHabDebut;
	}


	public void setMoiHabDebut(String moiHabDebut) {
		this.moiHabDebut = moiHabDebut;
	}


	public String getSiteWeb() {
		return siteWeb;
	}


	public void setSiteWeb(String siteWeb) {
		this.siteWeb = siteWeb;
	}


	public String getCommune() {
		return commune;
	}


	public void setCommune(String commune) {
		this.commune = commune;
	}


	public String getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}


	public String getDateFin() {
		return dateFin;
	}


	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}


	public String getDateCreation() {
		return dateCreation;
	}


	public void setDateCreation(String dateCreation) {
		this.dateCreation = dateCreation;
	}


	public int getCodepost() {
		return codepost;
	}


	public void setCodepost(int codepost) {
		this.codepost = codepost;
	}


	public String getCodeINSEE() {
		return codeINSEE;
	}


	public void setCodeINSEE(String codeINSEE) {
		this.codeINSEE = codeINSEE;
	}


	public float getCoord1() {
		return coord1;
	}


	public void setCoord1(float coord1) {
		this.coord1 = coord1;
	}


	public float getCoord2() {
		return coord2;
	}


	public void setCoord2(float coord2) {
		this.coord2 = coord2;
	}


	public String getNomDepartement() {
		return nomDepartement;
	}


	public void setNomDepartement(String nomDepartement) {
		this.nomDepartement = nomDepartement;
	}


	public int getNbPlaceLouees() {
		return nbPlaceLouees;
	}


	public void setNbPlaceLouees(int nbPlaceLouees) {
		this.nbPlaceLouees = nbPlaceLouees;
	}


	public String getDateAjout() {
		return dateAjout;
	}


	public void setDateAjout(String dateAjout) {
		this.dateAjout = dateAjout;
	}


	public int getIdUtilisateur() {
		return idUtilisateur;
	}


	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}
    
    

    
    
    
    

}
