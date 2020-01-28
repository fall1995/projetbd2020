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
public class EmplacementsChambres extends EmplacementsVillages{
    int tarifAdulte;
    int tarifEnfant;
    int nbAdultes;
    int nbEnfants ;
    int idHebergement ;

    public EmplacementsChambres(int numLogement, int tarifAdulte, int tarifEnfant, int nbAdultes, int nbEnfants, int idHebergement ) {
        super(numLogement);
        this.tarifAdulte = tarifAdulte;
        this.tarifEnfant = tarifEnfant;
        this.nbAdultes = nbAdultes;
        this.nbEnfants = nbEnfants;
        this.idHebergement = idHebergement;
    }

    public int getTarifAdulte() {
        return tarifAdulte;
    }

    public int getTarifEnfant() {
        return tarifEnfant;
    }

    public int getNbAdultes() {
        return nbAdultes;
    }

    public int getNbEnfants() {
        return nbEnfants;
    }

    public int getIdHebergement() {
        return idHebergement;
    }

    public void setTarifAdulte(int tarifAdulte) {
        this.tarifAdulte = tarifAdulte;
    }

    public void setTarifEnfant(int tarifEnfant) {
        this.tarifEnfant = tarifEnfant;
    }

    public void setNbAdultes(int nbAdultes) {
        this.nbAdultes = nbAdultes;
    }

    public void setNbEnfants(int nbEnfants) {
        this.nbEnfants = nbEnfants;
    }

    public void setIdHebergement(int idHebergement) {
        this.idHebergement = idHebergement;
    }
   
    
    
}
