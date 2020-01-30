package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import connexionBase.SQLAble;
import mesClassesMetier.*;

public class ResPlacesDAO extends SQLAble {

	public ArrayList<LesReservationsPlaces> panierPlaces(String idUtilisateur) {

		System.out.println("idUtilis =="+ idUtilisateur);
		ArrayList<LesReservationsPlaces> places = new ArrayList<LesReservationsPlaces>();
		int idReservation;
		int idFestival;
		int jour;
		int nbPlacesSansCateg;
		int nbPlacesCateg1;
		int nbPlacesCateg2;
		int prixResPlace;
		Date dateResPlace;
		String nomFestival;
		
		try {
			connectToDatabase();
			int res = 0;
			// int idReservation;
			System.out.println("dans creer panierLogement ");
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
				String query2 = "SELECT idReservation, idFestival, jour, nbPlacesSansCateg, nbPlacesCateg1, nbPlacesCateg2, prixResPlace, dateResPlace, nomFestival from LesReservationPaquetsPlaces natural join LesFestivals where idReservation =" + idReservation + "";
				ResultSet rs2 = ps2.executeQuery(query2);
				while (rs2.next()) {
					idFestival = rs2.getInt(2);
					jour = rs2.getInt(3);
					nbPlacesSansCateg = rs2.getInt(4);
					nbPlacesCateg1 = rs2.getInt(5);
					nbPlacesCateg2 = rs2.getInt(6);
					prixResPlace = rs2.getInt(7);
					dateResPlace = rs2.getDate(8);
					nomFestival =  rs2.getString(9);

					LesReservationsPlaces place = new LesReservationsPlaces(idReservation, idFestival, jour,
							nbPlacesSansCateg, nbPlacesCateg1, nbPlacesCateg2, prixResPlace, dateResPlace, nomFestival);
					places.add(place);

				}

			}
			ps2.close();
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("taille tab =="+places.size());
		System.out.println("premier element"+places.get(0).getJour());
		return places;

	}

}
