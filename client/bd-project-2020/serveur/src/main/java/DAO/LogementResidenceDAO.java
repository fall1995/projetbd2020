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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import mesClassesMetier.Festival;
import mesClassesMetier.Hebergement;
import mesClassesMetier.Logement;
import mesClassesMetier.LogementCamping;
import mesClassesMetier.LogementResidence;

/**
 *
 * @author bouaziz
 */
public class LogementResidenceDAO extends SQLAble implements LogementInterface {

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

        ArrayList<Logement> logementsDispo = new ArrayList<Logement>();
        try {

            System.out.println(iDFestival);
            LogementResidence logementResidenceDsipo;
            // PreparedStatement ps;
            Statement ps = conn.createStatement();

            int numLogement;
            int tarif;
            int nbPersonneConseille;
            int idHebergementInt;
            int idH;
            idH = Integer.parseInt(idHebergement);
            dateDF = "'" + dateDF + "'";
            dateFF = "'" + dateFF + "'";
            System.out.println("--------------->"+ dateDF);
            System.out.println("--------------->"+ dateFF);
            System.out.println("--------------->"+ idH);
            String query = "SELECT numLogement, tarif,nbPersonneConseille, idHebergement FROM ( select * from LesLogementsResidences natural join LesResidences where idHebergement = " + idH + " ) A NATURAL JOIN (select numLogement, count(*) from LesPeriodeDeDisponibilites where (dateDispo >= TO_DATE(" + dateDF + ",'DD-MM-YYYY') and dateDispo <= TO_DATE(" + dateFF + ",'DD-MM-YY')) group by (numLogement) having count(*) >= TO_DATE(" + dateFF + ",'DD-MM-YY') -  TO_DATE(" + dateDF + ",'DD-MM-YY') + 1) B";

            ResultSet resultats = ps.executeQuery(query);
            while (resultats.next()) {
                System.out.println("dans boucle");
                numLogement = resultats.getInt(1);
                System.out.println("numLogement = " + numLogement + "\n");
                tarif = resultats.getInt(2);
                System.out.println("tarif = " + tarif + "\n");

                nbPersonneConseille = resultats.getInt(3);
                System.out.println("nbPersonneConseille = " + nbPersonneConseille + "\n");
                idHebergementInt = resultats.getInt(4);

                System.out.println("idHebergementInt = " + idHebergementInt + "\n");
                // j'instancie la classe festival et je l'ajoute a ma liste de festivale
                logementResidenceDsipo = new LogementResidence(numLogement, tarif, nbPersonneConseille, idHebergementInt);
                // System.out.println("Element de ma liste "+ fest.getIdUtilisateur());

                logementsDispo.add(logementResidenceDsipo);

            }
            resultats.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\n" + logementsDispo.size());
        return logementsDispo;

    }

}
