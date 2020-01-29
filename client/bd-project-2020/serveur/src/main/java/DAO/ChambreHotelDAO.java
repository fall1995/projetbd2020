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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
String dateDFD = null;
String dateFFD = null;
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
            //dateDF = "'" + dateDF + "'";
            //dateFF = "'" + dateFF + "'";
            
            
            /*String query = "SELECT numLogement, tarifAdulte, tarifEnfant, nbAdultes, nbEnfants, typeChambre, idHebergement\n"
                    + "FROM ( select * from LesChambres natural join LesHotels where idHebergement =" + idH + " )\n"
                    + "NATURAL JOIN (select numLogement, count(*) \n"
                    + "from LesPeriodeDeDisponibilites\n"
                    + "where (dateDispo >= TO_DATE(" + dateDF + ",'DD-MM-YY') and dateDispo <= TO_DATE(" + dateFF + ",'DD-MM-YY'))\n"
                    + "group by (numLogement)\n"
                    + "having count(*) = TO_DATE(" + dateFF + ",'DD-MM-YY') -  TO_DATE(" + dateDF + ",'DD-MM-YY') + 1) B";
*/
            int idFestString  = Integer.parseInt(iDFestival);
            String q = "SELECT dateDebut, dateFin FROM LesFestivals where idFestival = "+idFestString+"";
            ResultSet r = ps.executeQuery(q);
            while(r.next()){
                 String[] splitArray = null;
                 dateDFD = r.getString(1);
                 splitArray = dateDFD.split(" ");
                 dateDFD = splitArray[0];
                
                 dateFFD = r.getString(2);
                 splitArray = dateFFD.split(" ");
                 dateFFD = splitArray[0];
                 
            }
             System.out.println("-------------++++++> dateDF      "+dateDFD);
             System.out.println("-------------+++++++++> dateDF     "+dateFFD);
            dateDFD = this.changeFormat(dateDFD);
            dateFFD = this.changeFormat(dateFFD);
            System.out.println("-------------> dateDF      "+dateDFD);
            System.out.println("-------------> dateDF     "+dateFFD);
            dateDFD = "'" + dateDFD + "'";
            dateFFD = "'" + dateFFD + "'";
            System.out.println("------------> à quoi ça ress    "+dateDFD);
            
 // String query = "SELECT numLogement, tarifAdulte, tarifEnfant, nbAdultes, nbEnfants, typeChambre, idHebergement FROM ( select * from LesChambres natural join LesHotels where idHebergement = "+idH+" ) A NATURAL JOIN (select numLogement, count(*) from LesPeriodeDeDisponibilites where dateDispo >= TO_DATE("+dateDFD+",'dd-MM-yy') and dateDispo <= TO_DATE("+dateFFD+",'dd-MM-yy') group by(numLogement))  B";
    //String query = "select numLogement from LesPeriodeDeDisponibilites where dateDispo >= TO_DATE("+dateDFD+",'dd-MM-yy') and dateDispo <= TO_DATE(+"+dateFFD+",'dd-MM-yy') ";      
   String query = "select distinct(numLogement),tarifAdulte, tarifEnfant, nbAdultes, nbEnfants, typeChambre, idHebergement from LesPeriodeDeDisponibilites natural join LesChambres where dateDispo >= TO_DATE("+dateDFD+",'dd-MM-yy') and dateDispo <= TO_DATE(+"+dateFFD+",'dd-MM-yy') and idHebergement ="+idH+"";
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
        } catch (ParseException ex) {
            Logger.getLogger(ChambreHotelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("\n" + logementsDispo.size());
        return logementsDispo;

    }
/*
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
*/
    
    public String changeFormat(String oldDate) throws ParseException{
        final String OLD_FORMAT = "yyyy-MM-dd";
        final String NEW_FORMAT = "dd-MM-yy";
        String oldDateString = oldDate;
        String newDateString;
        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        java.util.Date d = sdf.parse(oldDateString);
        sdf.applyPattern(NEW_FORMAT);
        newDateString = sdf.format(d);
        return newDateString;
    } 
}
