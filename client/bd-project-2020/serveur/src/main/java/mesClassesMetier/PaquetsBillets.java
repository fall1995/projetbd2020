package mesClassesMetier;

public class PaquetsBillets {

	int jour   ;
	int tarifSansCateg  ;
	int tarifCateg1    ;
	int tarifCateg2  ;
	int nbPlacesCateg1  ;
	int nbPlacesCateg2   ;
	int nbPlacesSansCateg  ;
	int nbPlacesRestantesCateg1 ;
	int nbPlacesRestantesCateg2 ;
	int nbPlacesRestantesSansCateg ;
	int idFestival  ;
	

	public PaquetsBillets(int jour, int tarifSansCateg, int tarifCateg1, int tarifCateg2, int nbPlacesCateg1,
			int nbPlacesCateg2, int nbPlacesSansCateg, int nbPlacesRestantesCateg1, int nbPlacesRestantesCateg2,
			int nbPlacesRestantesSansCateg, int idFestival) {
		super();
		this.jour = jour;
		this.tarifSansCateg = tarifSansCateg;
		this.tarifCateg1 = tarifCateg1;
		this.tarifCateg2 = tarifCateg2;
		this.nbPlacesCateg1 = nbPlacesCateg1;
		this.nbPlacesCateg2 = nbPlacesCateg2;
		this.nbPlacesSansCateg = nbPlacesSansCateg;
		this.nbPlacesRestantesCateg1 = nbPlacesRestantesCateg1;
		this.nbPlacesRestantesCateg2 = nbPlacesRestantesCateg2;
		this.nbPlacesRestantesSansCateg = nbPlacesRestantesSansCateg;
		this.idFestival = idFestival;
	}
	public int getJour() {
		return jour;
	}
	public void setJour(int jour) {
		this.jour = jour;
	}
	public int getTarifSansCateg() {
		return tarifSansCateg;
	}
	public void setTarifSansCateg(int tarifSansCateg) {
		this.tarifSansCateg = tarifSansCateg;
	}
	public int getTarifCateg1() {
		return tarifCateg1;
	}
	public void setTarifCateg1(int tarifCateg1) {
		this.tarifCateg1 = tarifCateg1;
	}
	public int getTarifCateg2() {
		return tarifCateg2;
	}
	public void setTarifCateg2(int tarifCateg2) {
		this.tarifCateg2 = tarifCateg2;
	}
	public int getNbPlacesCateg1() {
		return nbPlacesCateg1;
	}
	public void setNbPlacesCateg1(int nbPlacesCateg1) {
		this.nbPlacesCateg1 = nbPlacesCateg1;
	}
	public int getNbPlacesCateg2() {
		return nbPlacesCateg2;
	}
	public void setNbPlacesCateg2(int nbPlacesCateg2) {
		this.nbPlacesCateg2 = nbPlacesCateg2;
	}
	public int getNbPlacesSansCateg() {
		return nbPlacesSansCateg;
	}
	public void setNbPlacesSansCateg(int nbPlacesSansCateg) {
		this.nbPlacesSansCateg = nbPlacesSansCateg;
	}
	public int getNbPlacesRestantesCateg1() {
		return nbPlacesRestantesCateg1;
	}
	public void setNbPlacesRestantesCateg1(int nbPlacesRestantesCateg1) {
		this.nbPlacesRestantesCateg1 = nbPlacesRestantesCateg1;
	}
	public int getNbPlacesRestantesCateg2() {
		return nbPlacesRestantesCateg2;
	}
	public void setNbPlacesRestantesCateg2(int nbPlacesRestantesCateg2) {
		this.nbPlacesRestantesCateg2 = nbPlacesRestantesCateg2;
	}
	public int getNbPlacesRestantesSansCateg() {
		return nbPlacesRestantesSansCateg;
	}
	public void setNbPlacesRestantesSansCateg(int nbPlacesRestantesSansCateg) {
		this.nbPlacesRestantesSansCateg = nbPlacesRestantesSansCateg;
	}
	public int getIdFestival() {
		return idFestival;
	}
	public void setIdFestival(int idFestival) {
		this.idFestival = idFestival;
	}
	
	
	
	
	
}
