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
public class LogementCamping extends Logement{
    int tarif;
    int idHebergement;  
    

    public LogementCamping(int tarif, int idHebergement, int numLogement) {
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
