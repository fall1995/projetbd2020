/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import connexionBase.SQLAble;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;
import mesClassesMetier.Hebergement;
import mesClassesMetier.Hotel;

/**
 *
 * @author bouaziz
 */
public class DisponibiliteLogmentDAO extends SQLAble{
    public ArrayList<Date> getDateDisponibilitesLogement(String numLogement) {
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                int numLogementInt = Integer.parseInt(numLogement);
		ArrayList<Date> listeDeDsipo = new ArrayList<Date>();
                System.out.println("--------->"+numLogementInt);
		try {
			// PreparedStatement ps;
			Statement ps = conn.createStatement();
			String dateDeDispoS;
			Date dateDeDispoD;


			
			String query = "SELECT dateDispo FROM LesPeriodeDeDisponibilites where numLogement =" + numLogementInt+"";
			
			ResultSet resultats = ps.executeQuery(query);
			while (resultats.next()) {
				
			
				dateDeDispoD = resultats.getDate(1);
				
				
		
				System.out.println("Date  ="+dateDeDispoD);
				listeDeDsipo.add(dateDeDispoD);
				
			}
			resultats.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		
		return listeDeDsipo;
		// return null;

	}

}
