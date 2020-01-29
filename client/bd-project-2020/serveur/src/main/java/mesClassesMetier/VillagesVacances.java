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
public class VillagesVacances extends Hebergement{
    int capaciteAcc;
    int nbUniteHabitationVil;
    public VillagesVacances(int idHebergement, Date dateDePublication, String nomCommercial, Date dateDeClassement, String nomCategorie, String classement, String adresse, String codePostal, String commune, String numTel, String courreil, String siteNet, float coordonnee1, float coordonnee2, String nomDep, String nomRegion, String description, Date dateAjout, String idUtilisateur, int capaciteAcc, int nbUniteHabitationVil) {
        super(idHebergement, dateDePublication, nomCommercial, dateDeClassement, nomCategorie, classement, adresse, codePostal, commune, numTel, courreil, siteNet, coordonnee1, coordonnee2, nomDep, nomRegion, description, dateAjout, idUtilisateur);
        this.capaciteAcc = capaciteAcc;
        this.nbUniteHabitationVil = nbUniteHabitationVil;
    
    }

    public void setCapaciteAcc(int capaciteAcc) {
        this.capaciteAcc = capaciteAcc;
    }

    public void setNbChambres(int nbChambres) {
        this.nbUniteHabitationVil = nbChambres;
    }

    public int getCapaciteAcc() {
        return capaciteAcc;
    }

    public int getNbChambres() {
        return nbUniteHabitationVil;
    }
    
   
    
}
