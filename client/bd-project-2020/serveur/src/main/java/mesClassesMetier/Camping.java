/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesClassesMetier;

import java.sql.Date;

/**
 *
 * @author bouaziz
 */
public class Camping extends Hebergement{
    int nbEmplacement;
    public Camping(int idHebergement, Date dateDePublication, String nomCommercial, Date dateDeClassement, String nomCategorie, String classement, String adresse, String codePostal, String commune, String numTel, String courreil, String siteNet, float coordonnee1, float coordonnee2, String nomDep, String nomRegion, String description, Date dateAjout, int idUtilisateur, int nbEmplacement) {
        super(idHebergement, dateDePublication, nomCommercial, dateDeClassement, nomCategorie, classement, adresse, codePostal, commune, numTel, courreil, siteNet, coordonnee1, coordonnee2, nomDep, nomRegion, description, dateAjout, idUtilisateur);
        this.nbEmplacement = nbEmplacement;
        
    
    }

    public void setCapaciteAcc(int nbEmplacement) {
        this.nbEmplacement = nbEmplacement;
    }

 
    public int getCapaciteAcc() {
        return nbEmplacement;
    }

  
    
   
    
}
