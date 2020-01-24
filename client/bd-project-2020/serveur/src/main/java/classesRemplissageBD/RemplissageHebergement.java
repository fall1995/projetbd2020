/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classesRemplissageBD;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.io.*;
import java.sql.*;
/**
 *
 * @author bouaziz
 */
public class RemplissageHebergement {
    public final static int nbMaxHebergement = 2000;
     public static void main(String[] args) {
        String jdbcURL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
        String username = "bouazizl";
        String password = "Lamine96";
 
        String csvFilePath = "/home/bouaziz/Etudes M1/ProjetBDi/FichierCSV/LesHebergements.csv";
 
        int batchSize = 20;
 
        Connection connection = null;
 
        try {
 
            connection = DriverManager.getConnection(jdbcURL, username, password);
            connection.setAutoCommit(false);
 
            String sql = "INSERT INTO LesHebergements (dateDePublication, nomCommercial, dateDeClassement, nomCategorie,classement,adresse,codePostal,commune,numTel,courriel,siteNet,coordonnee1,coordonnee2,nomDep,nomRegion,Description,dateAjout,idUtilisateur) VALUES ( ?, ?, ?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
 
            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
            String lineText = null;
 
            int count = 0;
            int idHebergement = 1;
            lineReader.readLine(); // skip header line
            int cpt =0;
           // while ((lineText = lineReader.readLine()) != null) {
           while(cpt < nbMaxHebergement){    
               lineText = lineReader.readLine();
                String[] data = lineText.split(",");
                  String dateDePublication = data[1];
                String nomCommercial = data[2];
                String dateDeClassement = data[3];
                String categorie = data[4];
                String classement = data[5];
                String adresse = data[6];
                String codePostal = data[7];
                String commune = data[8];
                String numTel = data[9];
                String courriel = data[10];
                String siteNet = data[11];
                String coordonnee1 = data[12];
                /*if(coordonnee1.compareTo("NULL") != 0 ){
                    System.out.println(coordonnee1);
                    fcoordonnee1 = Float.parseFloat(coordonnee1);
                }*/
                
                String coordonnee2 = data[13];
               
                String nomDep = data[14];
                String nomRegion = data[15];
                String Description = data[16];
                String dateAjout = data[17];
                String idUtilisateur = data[18];
            
                //statement.setInt(1, idHebergement);
                statement.setString(1, dateDePublication);
                statement.setString(2, nomCommercial);
                statement.setString(3, dateDeClassement);
                statement.setString(4, categorie);
                statement.setString(5, classement);
                statement.setString(6, adresse);
                statement.setString(7, codePostal);
                statement.setString(8, commune);
                statement.setString(9, numTel);
                statement.setString(10, courriel);
                statement.setString(11, siteNet);
                statement.setString(12, coordonnee1);
                statement.setString(13, coordonnee2);
                 statement.setString(14, nomDep);
                statement.setString(15, nomRegion);
                statement.setString(16, Description);
                statement.setString(17, dateAjout);
                statement.setString(18, idUtilisateur); 
                //float iidH = Float.parseFloat(idHeberegement);
                /*String idHeberegement = data[0];
                String idHeberegement = data[0];
                String idHeberegement = data[0];
                String idHeberegement = data[0];
                String idHeberegement = data[0];*/
                
                idHebergement++ ;
                cpt++;
                
 
               

                statement.addBatch();
 
                if (count % batchSize == 0) {
                    
                    statement.executeBatch();
                    System.out.println(idHebergement);
                }
            }
 
            lineReader.close();
 
            // execute the remaining queries
            statement.executeBatch();
 
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
}
