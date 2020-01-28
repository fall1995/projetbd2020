/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterfaces.HebergementInterface;
import DAOInterfaces.LogementInterface;
import connexionBase.SQLAble;
import java.sql.SQLException;
import java.util.ArrayList;
import mesClassesMetier.Hebergement;
import mesClassesMetier.Logement;

/**
 *
 * @author bouaziz
 */
public class LogementDAO extends SQLAble implements LogementInterface {

    @Override
    public void addH(Hebergement h) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Hebergement h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Logement> getLogement(String iDFestival, String dateDF, String dateFF, String idHebergement, String typeHebergement) {
        // connection à la base
        try {
            connectToDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int idFestivalInt = Integer.parseInt(iDFestival);
        String typeHebergementUpperCase = typeHebergement.toUpperCase();
        ArrayList<Logement> logements = new ArrayList<Logement>();// Liste pour recuperer mon resultat
        int idH;
        
        idH = Integer.parseInt(idHebergement);
        switch (typeHebergementUpperCase) {
            case "HOTEL":
               
                ChambreHotelDAO chd = new ChambreHotelDAO();
                logements = chd.getLogement(iDFestival, dateDF, dateFF, idHebergement);
                break;
            case "RESIDENCE":
                System.out.println("---------------> ici Residence");
                LogementResidenceDAO lrd = new LogementResidenceDAO();
                logements = lrd.getLogement(iDFestival, dateDF, dateFF, idHebergement);
                break;
            case "CAMPING":
                LogementCampingDAO lcd = new LogementCampingDAO();
                logements = lcd.getLogement(iDFestival, dateDF, dateFF, idHebergement);
                break;
            default:
                EmplacementVillageDAO ecd = new EmplacementVillageDAO();
                logements = ecd.getLogement(iDFestival, dateDF, dateFF, idHebergement);
                break;
        }
        return logements;

    }

    

    @Override
    public ArrayList<Logement> getLogement(String iDFestival, String dateDF, String dateFF, String idHebergement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
