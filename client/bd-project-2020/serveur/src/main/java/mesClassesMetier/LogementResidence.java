/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesClassesMetier;

/**
 *
 * @author bouaziz
 */
public class LogementResidence extends Logement{
     int tarif;
     int nbPersonneConseille ;
     int idHebergement ;

    public LogementResidence(int numLogement, int tarif, int nbPersonneConseille, int idHebergement ) {
        super(numLogement);
        this.tarif = tarif;
        this.nbPersonneConseille = nbPersonneConseille;
        this.idHebergement = idHebergement;
    }

    public int getTarif() {
        return tarif;
    }

    public int getNbPersonneConseille() {
        return nbPersonneConseille;
    }

    public int getIdHebergement() {
        return idHebergement;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public void setNbPersonneConseille(int nbPersonneConseille) {
        this.nbPersonneConseille = nbPersonneConseille;
    }

    public void setIdHebergement(int idHebergement) {
        this.idHebergement = idHebergement;
    }
   
    
}
