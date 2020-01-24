/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connexionBase.SQLAble;
import DAOInterfaces.HebergementInterface;
import java.sql.Date;
import java.sql.SQLException;
import mesClassesMetier.Hebergement;

/**
 *
 * @author bouaziz
 */
public class HotelDAO extends SQLAble implements HebergementInterface{

    @Override
    public void add(Hebergement h) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Hebergement h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getHebergementsProches(int iDFestival, int nbPlaces, Date dateDebut, Date dateFin, String typeHebergement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getOneHebergement(Hebergement h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
