package mesClassesMetier;

import java.util.Date;

public class LesReservationsPlaces {

	int idReservation;
	int idFestival;
	int jour;
	int nbPlacesSansCateg;
	int nbPlacesCateg1;
	int nbPlacesCateg2;
	int prixResPlace;
	Date dateResPlace;
	String nomFestival;

	public LesReservationsPlaces(int idReservation, int idFestival, int jour, int nbPlacesSansCateg, int nbPlacesCateg1,
			int nbPlacesCateg2, int prixResPlace, Date dateResPlace, String nomFestival) {
		super();
		this.idReservation = idReservation;
		this.idFestival = idFestival;
		this.jour = jour;
		this.nbPlacesSansCateg = nbPlacesSansCateg;
		this.nbPlacesCateg1 = nbPlacesCateg1;
		this.nbPlacesCateg2 = nbPlacesCateg2;
		this.prixResPlace = prixResPlace;
		this.dateResPlace = dateResPlace;
		this.nomFestival = nomFestival ;
	}

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public int getIdFestival() {
		return idFestival;
	}

	public void setIdFestival(int idFestival) {
		this.idFestival = idFestival;
	}

	public int getJour() {
		return jour;
	}

	public void setJour(int jour) {
		this.jour = jour;
	}

	public int getNbPlacesSansCateg() {
		return nbPlacesSansCateg;
	}

	public void setNbPlacesSansCateg(int nbPlacesSansCateg) {
		this.nbPlacesSansCateg = nbPlacesSansCateg;
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

	public int getPrixResPlace() {
		return prixResPlace;
	}

	public void setPrixResPlace(int prixResPlace) {
		this.prixResPlace = prixResPlace;
	}

	public Date getDateResPlace() {
		return dateResPlace;
	}

	public void setDateResPlace(Date dateResPlace) {
		this.dateResPlace = dateResPlace;
	}

}
