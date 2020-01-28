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
public class ChambreHotel extends Logement{
    int tarifAdulte;
    int tarifEnfant ;
    int nbAdultes ;
    int nbEnfants ;
    char typeChambre ;
    int idHebergement;

  

    public ChambreHotel( int numLogement, int tarifAdulte, int tarifEnfant, int nbAdultes, int nbEnfants, char typeChambre, int idHebergement) {
        super(numLogement);
        this.tarifAdulte = tarifAdulte;
        this.tarifEnfant = tarifEnfant;
        this.nbAdultes = nbAdultes;
        this.nbEnfants = nbEnfants;
        this.typeChambre = typeChambre;
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

    public char getTypeChambre() {
        return typeChambre;
    }

    public int getIdHebergement() {
        return idHebergement;
    }
    
    
    
   
      
}
