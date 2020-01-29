package mesClassesMetier;

import java.util.Date;

public class LesReservationsLogements {

	int idReservation  ; 
    int numLogement     ; 
    int prixResLogement ;
    int nbPlaceAdulte  ;
    int nbPlaceEnfant ;
    Date dateDHebergement;  
    Date dateFHebergement ;
    Date dateReservation ;
    
    
	public LesReservationsLogements(int idReservation, int numLogement, int prixResLogement, int nbPlaceAdulte,
			int nbPlaceEnfant, Date dateDHebergement, Date dateFHebergement, Date dateReservation) {
		super();
		this.idReservation = idReservation;
		this.numLogement = numLogement;
		this.prixResLogement = prixResLogement;
		this.nbPlaceAdulte = nbPlaceAdulte;
		this.nbPlaceEnfant = nbPlaceEnfant;
		this.dateDHebergement = dateDHebergement;
		this.dateFHebergement = dateFHebergement;
		this.dateReservation = dateReservation;
	}
	
	public int getIdReservation() {
		return idReservation;
	}
	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}
	public int getNumLogement() {
		return numLogement;
	}
	public void setNumLogement(int numLogement) {
		this.numLogement = numLogement;
	}
	public int getPrixResLogement() {
		return prixResLogement;
	}
	public void setPrixResLogement(int prixResLogement) {
		this.prixResLogement = prixResLogement;
	}
	public int getNbPlaceAdulte() {
		return nbPlaceAdulte;
	}
	public void setNbPlaceAdulte(int nbPlaceAdulte) {
		this.nbPlaceAdulte = nbPlaceAdulte;
	}
	public int getNbPlaceEnfant() {
		return nbPlaceEnfant;
	}
	public void setNbPlaceEnfant(int nbPlaceEnfant) {
		this.nbPlaceEnfant = nbPlaceEnfant;
	}
	public Date getDateDHebergement() {
		return dateDHebergement;
	}
	public void setDateDHebergement(Date dateDHebergement) {
		this.dateDHebergement = dateDHebergement;
	}
	public Date getDateFHebergement() {
		return dateFHebergement;
	}
	public void setDateFHebergement(Date dateFHebergement) {
		this.dateFHebergement = dateFHebergement;
	}
	public Date getDateReservation() {
		return dateReservation;
	}
	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}
	
    
    
    
	
}
