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
import mesClassesMetier.ChambreHotel;
import mesClassesMetier.Festival;
import mesClassesMetier.Hebergement;
import mesClassesMetier.Hotel;
import mesClassesMetier.Logement;

/**
 *
 * @author bouaziz
 */
public class ChambreHotelDAO extends SQLAble implements LogementInterface {

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
        
        System.out.println("-------IDH>"+idH);
        // String nomDepartementUpper = nomDepartement.toUpperCase();
        //nomDepartementUpper = "'" + nomDepartementUpper + "'";
        //float diffDate = this.diffrenecEnetreDates(dateDF, dateFF);

        ArrayList<Logement> logementsDispo = new ArrayList<Logement>();
        try {

            System.out.println(iDFestival);
            ChambreHotel logementDsipo;
            // PreparedStatement ps;
            Statement ps = conn.createStatement();

            int numLogement;
            int tarifAdulte;
            int tarifEnfant;
            int nbAdultes;
            int nbEnfants;
            char typeChambre;
            int idHebergementInt;
            dateDF = "'" + dateDF + "'";
            dateFF = "'" + dateFF + "'";
            
            
            /*String query = "SELECT numLogement, tarifAdulte, tarifEnfant, nbAdultes, nbEnfants, typeChambre, idHebergement\n"
                    + "FROM ( select * from LesChambres natural join LesHotels where idHebergement =" + idH + " )\n"
                    + "NATURAL JOIN (select numLogement, count(*) \n"
                    + "from LesPeriodeDeDisponibilites\n"
                    + "where (dateDispo >= TO_DATE(" + dateDF + ",'DD-MM-YY') and dateDispo <= TO_DATE(" + dateFF + ",'DD-MM-YY'))\n"
                    + "group by (numLogement)\n"
                    + "having count(*) = TO_DATE(" + dateFF + ",'DD-MM-YY') -  TO_DATE(" + dateDF + ",'DD-MM-YY') + 1) B";
*/
  String query = "SELECT numLogement, tarifAdulte, tarifEnfant, nbAdultes, nbEnfants, typeChambre, idHebergement FROM ( select * from LesChambres natural join LesHotels where idHebergement = "+idH+" ) A NATURAL JOIN (select numLogement, count(*) from LesPeriodeDeDisponibilites where (dateDispo >= TO_DATE("+dateDF+",'DD-MM-YYYY') and dateDispo <= TO_DATE("+dateFF+",'DD-MM-YY')) group by (numLogement) having count(*) >= TO_DATE("+dateFF+",'DD-MM-YY') -  TO_DATE("+dateDF+",'DD-MM-YY') + 1) B";
          
            System.out.println("après req");
            ResultSet resultats = ps.executeQuery(query);
            while (resultats.next()) {
            	System.out.println("icccccccccccccci");
                System.out.println("dans le while");
                numLogement = resultats.getInt(1);
                System.out.println("numLog = " + numLogement + "\n");
                tarifAdulte = resultats.getInt(2);
                System.out.println("tarifAdulte = " + tarifAdulte + "\n");
                tarifEnfant = resultats.getInt(3);
                System.out.println("tarifEnfant = " + tarifEnfant + "\n");
                nbAdultes = resultats.getInt(4);
                System.out.println("nbAdultes = " + nbAdultes + "\n");
                nbEnfants = resultats.getInt(5);
                System.out.println("nbEnfants = " + nbEnfants + "\n");
                typeChambre = resultats.getString(6).charAt(0);
                System.out.println("typeChambre  = " + typeChambre + "\n");
                idHebergementInt = resultats.getInt(7);
                System.out.println("idHebergementInt  = " + idHebergementInt + "\n");
                // j'instancie la classe festival et je l'ajoute a ma liste de festivale
                logementDsipo = new ChambreHotel(numLogement, tarifAdulte, tarifEnfant, nbAdultes, nbEnfants, typeChambre, idHebergementInt);
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
            System.out.println("ICI PARSE");
            long diff = dateApres.getTime() - dateAvant.getTime();
            res = (diff / (1000 * 60 * 60 * 24));
            System.out.println("Nombre de jours entre les deux dates est: " + res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

}
