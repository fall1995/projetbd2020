/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesRemplissageBD;

import static classesRemplissageBD.RemplissageHebergement.nbMaxHebergement;
import java.io.*;
import java.sql.*;


/**
 *
 * @author bouaziz
 */
public class RemplissageLesVillageVacances{
    
    public static void main(String[] args) {
        String jdbcURL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
        String username = "bouazizl";
        String password = "Lamine96";

        String csvFilePath = "/home/bouaziz/Etudes M1/ProjetBDi/FichierCSV/villageVacances.csv";

        int batchSize = 20;

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            String sql = "INSERT INTO LesVillageVacances (idHebergement, capaciteAccueil, nbUniteHabitationVil) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);

            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;

            int count = 0;

            lineReader.readLine(); // skip header line
            int idH = 0;
            int cpt = 0;

            // while ((lineText = lineReader.readLine()) != null) {
            while (idH <= nbMaxHebergement) {
                int capA = 0;
                int nbH = 0;
                lineText = lineReader.readLine();
                String[] data = lineText.split(",");
                String idHebergement = data[0];
                idH = Integer.parseInt(idHebergement);
                String capaciteAccueil = data[1];
                if (estNombre(capaciteAccueil) == true) {
                    capA = Integer.parseInt(capaciteAccueil);
                }
                
                String  nbUniteHabitationVil= data[2];
                if (estNombre(nbUniteHabitationVil) == true) {
                    nbH = Integer.parseInt(nbUniteHabitationVil);
                }

                statement.setInt(1, idH);
                statement.setInt(2, capA);
                statement.setInt(3, nbH);
                statement.addBatch();

                if (count % batchSize == 0 && idH <= nbMaxHebergement) {
                    
                    statement.executeBatch();
                    System.out.println(idH);
                }

            }

            lineReader.close();

            // execute the remaining queries
           // statement.executeBatch();

            connection.commit();
            connection.close();

        } catch (IOException ex) {
            System.err.println(ex);
        } catch (SQLException ex) {
            ex.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public static boolean estNombre(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
