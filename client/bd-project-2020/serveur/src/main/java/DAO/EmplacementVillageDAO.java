/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterfaces.FestivalInterface;
import DAOInterfaces.HebergementInterface;
import DAOInterfaces.LogementInterface;
import connexionBase.SQLAble;
import java.sql.SQLException;
import java.util.ArrayList;
import mesClassesMetier.EmplacementsChambres;
import mesClassesMetier.Festival;
import mesClassesMetier.Hebergement;
import mesClassesMetier.Logement;

/**
 *
 * @author bouaziz
 */
public class EmplacementVillageDAO extends SQLAble implements LogementInterface{

    @Override
    public void addH(Hebergement h) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Hebergement h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Logement> getLogement(String iDFestival, String dateDF, String dateFF, String idHebergement) {
        int idH; 
        idH = Integer.parseInt(idHebergement);
        EmplacementsChambresDAO e = new EmplacementsChambresDAO();
        ArrayList<Logement> emplacementC = new ArrayList<Logement>();
        emplacementC = e.getLogement(iDFestival, dateDF, dateFF, idHebergement);
        
        EmplacementsCollectifsDAO ec = new EmplacementsCollectifsDAO();
        ArrayList<Logement> emplacementColl = new ArrayList<Logement>();
        emplacementColl = ec.getLogement(iDFestival, dateDF, dateFF, idHebergement);
        
        ArrayList<Logement> liste = new ArrayList<Logement>(); 
        for(int i = 0; i < emplacementC.size(); i++){
            liste.add(emplacementC.get(i));
        }
        
        for(int j = 0 ; j < emplacementC.size(); j++){
            liste.add(emplacementColl.get(j));
        }
        return liste;
    }

    
}
