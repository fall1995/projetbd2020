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
public class LogementDAO extends SQLAble implements LogementInterface{

    @Override
    public void addH(Hebergement h) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Hebergement h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Logement> getLogement(int iDFestival, String dateDF, String dateFF, int idHebergement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
