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
public class EmplacementsCollectifs extends EmplacementsVillages{
     int tarif;
    int idHebergement;
    public EmplacementsCollectifs(int numLogement) {
        super(numLogement);
    }

    public EmplacementsCollectifs(int numLogement, int tarif, int idHebergement) {
        super(numLogement);
        this.tarif = tarif;
        this.idHebergement = idHebergement;
    }

    public int getTarif() {
        return tarif;
    }

    public int getIdHebergement() {
        return idHebergement;
    }

    public void setTarif(int tarif) {
        this.tarif = tarif;
    }

    public void setIdHebergement(int idHebergement) {
        this.idHebergement = idHebergement;
    }
    
    
}
