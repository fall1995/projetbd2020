package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import connexionBase.SQLAble;
import mesClassesMetier.LesReservationsLogements;
import mesClassesMetier.LesReservationsPlaces;

public class ResLogDao extends SQLAble{

	public ArrayList<LesReservationsLogements> resLog(String idUtilisateur) {

		System.out.println("idUtilis =="+ idUtilisateur);
		ArrayList<LesReservationsLogements> logs = new ArrayList<LesReservationsLogements>();
		

		int idReservation  ; 
	    int numLogement     ; 
	    int prixResLogement ;
	    int nbPlaceAdulte  ;
	    int nbPlaceEnfant ;
	    Date dateDHebergement;  
	    Date dateFHebergement ;
	    Date dateReservation ;
		
		try {
			connectToDatabase();
			int res = 0;
			// int idReservation;
			System.out.println("dans creer panierPlaces ");
			Statement ps2 = conn.createStatement();
			Statement ps = conn.createStatement();
			idUtilisateur = "'" + idUtilisateur + "'";
			int test =0;
			String query = "SELECT idReservation from LesReservations where idUtilisateur =" + idUtilisateur + "";
			ResultSet rs = ps.executeQuery(query);
			while (rs.next()) {
				System.out.println("test ="+test);
				System.out.println("idUtilisateur =="+ idUtilisateur);
				test++;
				idReservation = rs.getInt(1);
				String query2 = "SELECT idReservation, numLogement, prixResLogement, nbPlaceAdulte, nbPlaceEnfant, dateDHebergement, dateFHebergement, dateReservation from LesReservationLogements where idReservation =" + idReservation + "";
				ResultSet rs2 = ps2.executeQuery(query2);
				while (rs2.next()) {
					numLogement = rs2.getInt(2);
					prixResLogement = rs2.getInt(3);
					nbPlaceAdulte = rs2.getInt(4);
					nbPlaceEnfant = rs2.getInt(5);
					dateDHebergement = rs2.getDate(6);
					dateFHebergement = rs2.getDate(7);
					dateReservation = rs2.getDate(8);
				

					LesReservationsLogements logement = new LesReservationsLogements(idReservation, numLogement, prixResLogement, nbPlaceAdulte, nbPlaceEnfant, dateDHebergement, dateFHebergement, dateReservation);
					logs.add(logement);

				}

			}
			ps2.close();
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("taille tab =="+logs.size());

		return logs;

	}
	
}
