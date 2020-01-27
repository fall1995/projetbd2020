/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOInterfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import mesClassesMetier.Hebergement;
import mesClassesMetier.Logement;

/**
 *
 * @author bouaziz
 */
public interface LogementInterface {
        public abstract void addH(Hebergement h) throws SQLException ;
	public abstract void update(Hebergement h);
	//public abstract ArrayList<Hebergement>  getHebergementsProches(int iDFestival);
	
       // public abstract ArrayList<Hebergement> getHebergementsProches(int iDFestival, String dateDebutF, String dateFinF);
       public abstract ArrayList<Logement> getLogement(int iDFestival, String dateDF, String dateFF, int idHebergement);
}
