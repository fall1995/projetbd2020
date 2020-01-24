/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesRemplissageBD;


import java.io.*;
import java.sql.*;

/**
 *
 * @author bouaziz
 */
public class RemplissageLesLogements {
public final static int nbMaxLogements = 8000;
    public static void main(String[] args) {
        String jdbcURL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
        String username = "bouazizl";
        String password = "Lamine96";

        String csvFilePath = "/home/bouaziz/Etudes M1/ProjetBDi/FichierCSV/LesHotels.csv";

        int batchSize = 20;

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);

            String sql = "INSERT INTO LesLogements (numLogement) VALUES (?)";
            PreparedStatement statement = connection.prepareStatement(sql);

           

            int count = 0;

            int numLog = 1;
          

            // while ((lineText = lineReader.readLine()) != null) {
            while (numLog <= nbMaxLogements) {
                
               
      
                
                statement.setInt(1, numLog);
               
                statement.addBatch();
                
                if (count % batchSize == 0 ) {
                    
                    statement.executeBatch();
                    System.out.println(numLog);
                }
                numLog++;
            }

            

            // execute the remaining queries
           // statement.executeBatch();

            connection.commit();
            connection.close();

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
