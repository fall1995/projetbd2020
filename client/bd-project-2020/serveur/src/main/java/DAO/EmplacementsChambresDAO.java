/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterfaces.LogementInterface;
import connexionBase.SQLAble;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import mesClassesMetier.ChambreHotel;
import mesClassesMetier.EmplacementsChambres;
import mesClassesMetier.Hebergement;
import mesClassesMetier.Logement;

/**
 *
 * @author bouaziz
 */
public class EmplacementsChambresDAO extends SQLAble implements LogementInterface{

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
       
        try {
            connectToDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int idH;
        idH = Integer.parseInt(idHebergement);
        // String nomDepartementUpper = nomDepartement.toUpperCase();
        //nomDepartementUpper = "'" + nomDepartementUpper + "'";
        float diffDate = this.diffrenecEnetreDates(dateDF, dateFF);
        ArrayList<Logement> logementsDispo = new ArrayList<Logement>();
        try {

            System.out.println(iDFestival);
            EmplacementsChambres logementDsipo;
            // PreparedStatement ps;
            Statement ps = conn.createStatement();
            dateDF = "'"+dateDF+"'";
            dateFF = "'"+dateFF+"'";
            int numLogement;
            int tarifAdulte;
            int tarifEnfant;
            int nbAdultes;
            int nbEnfants;
            int idHebergementInt;
            String query = "SELECT numLogement, tarifAdulte, tarifEnfant, nbAdultes, nbEnfants, idHebergement FROM ( select * from LesEmplacementsChambres natural join LesVillageVacances where idHebergement = "+idH+" ) A NATURAL JOIN (select numLogement, count(*) from LesPeriodeDeDisponibilites where (dateDispo >= TO_DATE("+dateDF+",'DD-MM-YYYY') and dateDispo <= TO_DATE("+dateFF+",'DD-MM-YY')) group by (numLogement) having count(*) >= TO_DATE("+dateFF+",'DD-MM-YY') -  TO_DATE("+dateDF+",'DD-MM-YY') + 1) B";
            
            ResultSet resultats = ps.executeQuery(query);
            while (resultats.next()) {

                numLogement = resultats.getInt(1);
                // System.out.println("id festival = "+idFestival + "\n");
                tarifAdulte = resultats.getInt(2);
                // System.out.println("nomFestival = "+nomFestival + "\n");
                tarifEnfant = resultats.getInt(3);
                // System.out.println("domaine = "+domaine + "\n");
                 nbAdultes = resultats.getInt(4);
                 nbEnfants = resultats.getInt(5);
                 
                // System.out.println("complement dom = "+complementDomaine + "\n");
                idHebergementInt = resultats.getInt(6);
                
                // j'instancie la classe festival et je l'ajoute a ma liste de festivale
                logementDsipo = new EmplacementsChambres(numLogement, tarifAdulte, tarifEnfant, nbAdultes, nbEnfants, idHebergementInt);
                // System.out.println("Element de ma liste "+ fest.getIdUtilisateur());

                
                logementsDispo.add(logementDsipo);

            }
            resultats.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n" + logementsDispo.size());
        return logementsDispo;

    }
    
    
    public float diffrenecEnetreDates(String dateD, String dateF) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
        float res = 0;
        try {
            Date dateAvant = sdf.parse(dateD);
            Date dateApres = sdf.parse(dateF);
            long diff = dateApres.getTime() - dateAvant.getTime();
            res = (diff / (1000 * 60 * 60 * 24));
            System.out.println("Nombre de jours entre les deux dates est: " + res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    
}
