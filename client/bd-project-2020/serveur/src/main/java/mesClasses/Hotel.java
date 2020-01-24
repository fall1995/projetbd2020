/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mesClasses;

import java.sql.Date;

/**
 *
 * @author bouaziz
 */
public class Hotel extends Hebergement{
    
    public Hotel(int idHebergement, Date dateDePublication, String nomCommercial, Date dateDeClassement, String nomCategorie, String classement, String adresse, String categorie, int codePostal, String commune, int numTel, String courreil, String siteNet, double coordonnees, double nomEPCI, String nomDep, String nomRegion, String description, Date dateAjout, int idUtilisateur) {
        super(idHebergement, dateDePublication, nomCommercial, dateDeClassement, nomCategorie, classement, adresse, categorie, codePostal, commune, numTel, courreil, siteNet, coordonnees, nomEPCI, nomDep, nomRegion, description, dateAjout, idUtilisateur);
    }
    
}
