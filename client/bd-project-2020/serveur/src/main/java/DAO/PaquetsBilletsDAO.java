package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import mesClassesMetier.Festival;
import mesClassesMetier.PaquetsBillets;
import connexionBase.SQLAble;
import DAOInterfaces.FestivalInterface;

public class PaquetsBilletsDAO extends SQLAble {

	public ArrayList<PaquetsBillets> billetFestivalSql(int idFestival) {

		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<PaquetsBillets> paquetsB = new ArrayList<PaquetsBillets>();// Liste pour recuperer mon resultat

		// requete si l'utilisateur n'a pas affiné sa recherche
		try {
			PaquetsBillets billets;
			// PreparedStatement ps;
			Statement ps = conn.createStatement();
			// ps = conn.prepareStatement("SELECT * FROM LesFestivals");
			String query = "select * from LesPaquetsPlaces where idFestival = " + idFestival + "";
			// ps.setInt(1, 0);
			ResultSet resultats = ps.executeQuery(query);

			// Parcours des resulats (objet ResulSet) retournés par executeQuery()

			int jour;
			int tarifSansCateg;
			int tarifCateg1;
			int tarifCateg2;
			int nbPlacesCateg1;
			int nbPlacesCateg2;
			int nbPlacesSansCateg;
			int nbPlacesRestantesCateg1;
			int nbPlacesRestantesCateg2;
			int nbPlacesRestantesSansCateg;
			// int idFestival ;

			while (resultats.next()) {
				jour = resultats.getInt(1);
				// System.out.println("id festival = "+idFestival + "\n");
				tarifSansCateg = resultats.getInt(2);
				// System.out.println("nomFestival = "+nomFestival + "\n");
				tarifCateg1 = resultats.getInt(3);
				// System.out.println("domaine = "+domaine + "\n");
				tarifCateg2 = resultats.getInt(4);
				// System.out.println("complement dom = "+complementDomaine + "\n");
				nbPlacesCateg1 = resultats.getInt(5);
				nbPlacesCateg2 = resultats.getInt(6);
				nbPlacesSansCateg = resultats.getInt(7);
				nbPlacesRestantesCateg1 = resultats.getInt(8);
				// System.out.println("moiHabDebut = "+moiHabDebut + "\n");
				nbPlacesRestantesCateg2 = resultats.getInt(9);
				// System.out.println("siteWeb = "+siteWeb + "\n");
				nbPlacesRestantesSansCateg = resultats.getInt(10);
				// System.out.println("commune = "+commune + "\n");

				// j'instancie la classe et je l'ajoute a ma liste
				billets = new PaquetsBillets(jour, tarifSansCateg, tarifCateg1, tarifCateg2, nbPlacesCateg1,
						nbPlacesCateg2, nbPlacesSansCateg, nbPlacesRestantesCateg1, nbPlacesRestantesCateg2,
						nbPlacesRestantesSansCateg, idFestival);
				// System.out.println("Element de ma liste "+ fest.getIdUtilisateur());
				paquetsB.add(billets);
			}
			resultats.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		// System.out.println("\n" + festivals.size());
		System.out.println("taille ==== " + paquetsB.size());
		return paquetsB;
		// return null;
	}

	public void creerPanierRes(String idUtilisateur) throws SQLException {
		try {
			connectToDatabase();
			int res = 0;
			System.out.println("dans creer panier");

			Statement ps = conn.createStatement();
			idUtilisateur = "'" + idUtilisateur + "'";

			// pour rajouter timerr
			/*
			 * DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); Date date =
			 * new Date();
			 */
			int nb = ps.executeUpdate(
					"INSERT INTO LesReservations ( idUtilisateur, prix) VALUES (" + idUtilisateur + "," + 0 + ")");
			System.out.println("Nombre de lignes insérées dans panier = " + nb);
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public void creerResBillet(String idUtilisateur, int idFestival, int jour, int nbPlacesSansCateg, int nbPlaceCateg1,
			int nbPlaceCateg2) throws SQLException {
		try {
			connectToDatabase();
			int res = 0;
			System.out.println("dans creerResbillet");
			Statement ps = conn.createStatement();
			idUtilisateur = "'" + idUtilisateur + "'";
			int idReservation = -1;

			String query = "SELECT max(idReservation) from LesReservations where idUtilisateur =" + idUtilisateur + "";
			ResultSet rs = ps.executeQuery(query);
			while (rs.next()) {
				idReservation = rs.getInt(1);
			}
			System.out.println("idRes==="+idReservation);
			String select = "select nbPlacesRestantesCateg1, nbPlacesRestantesCateg2, nbPlacesRestantesSansCateg,tarifSansCateg,tarifCateg1,tarifCateg2 from LesPaquetsPlaces where jour ="
					+ jour + " and idFestival =" + idFestival + " for Update ";
			ResultSet rs1 = ps.executeQuery(select);
			int nbSansC = -1, nbC1 = -1, nbC2 = -1;
			int tarifS = 0, tarifC1 = 0, tarifC2 = 0;

			while (rs1.next()) {
				nbSansC = rs1.getInt(1);
				nbC1 = rs1.getInt(2);
				nbC2 = rs1.getInt(3);
				tarifS = rs1.getInt(4);
				tarifC1 = rs1.getInt(5);
				tarifC2 = rs1.getInt(6);
			}
			System.out.println("quantite sans =" + nbSansC);
			System.out.println("quantite cat1 =" + nbC1);
			System.out.println("quantite cat2 =" + nbC2);
			int totalSansC = tarifS * nbPlacesSansCateg;
			int totalC1 = tarifC1 * nbPlaceCateg1;
			int totalC2 = tarifC2 * nbPlaceCateg2;
			int prixTotal = totalSansC + totalC1 + totalC2;
			System.out.println("prix total = " + prixTotal);

			Statement upd = conn.createStatement();
			
			if (nbSansC >= nbPlacesSansCateg && nbC1 >= nbPlaceCateg1 && nbC2 >= nbPlaceCateg2) {
				System.out.println("dans le mille");
				int nb = ps
						.executeUpdate("UPDATE LesPaquetsPlaces SET nbPlacesRestantesCateg1 = nbPlacesRestantesCateg1 -"
								+ nbPlaceCateg1 + ", nbPlacesRestantesCateg2 = nbPlacesRestantesCateg2 -" + nbPlaceCateg2
								+ ", nbPlacesRestantesSansCateg = nbPlacesRestantesSansCateg -" + nbPlacesSansCateg
								+ " WHERE jour = " + jour + " and idFestival =" + idFestival + "");
																
				System.out.println("Nombre de lignes mises à jour dans update place = " + nb);
				if(nb>0) {
				// voir comment faire pour prix
				int nb2 = ps.executeUpdate(
						"INSERT INTO LesReservationPaquetsPlaces (idReservation, idFestival, jour, nbPlacesSansCateg, "
								+ " nbPlacesCateg1, nbPlacesCateg2, prixResPlace) VALUES (" + idReservation + ","
								+ idFestival + "," + jour + "," + nbPlacesSansCateg + "," + nbPlaceCateg1 + ","
								+ nbPlaceCateg2 + "," + prixTotal + ")");
				System.out.println("Nombre de lignes insérées dans RESPAQUETPLACE = " + nb2);
				}
				//upd.close();
			} else {
				int nb = ps.executeUpdate("Delete from LesReservations where idReservation =" + idReservation + "");
				System.out.println("Nombre de lignes supprime dans LesReservations = " + nb);
				SQLAble.conn.rollback();
			}

			ps.close();
			this.disconnect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void reserverBillets(String idUtilisateur, int idFestival, int jour, int nbPlacesSansCateg,
			int nbPlaceCateg1, int nbPlaceCateg2) {
		try {
			this.creerPanierRes(idUtilisateur);
			this.creerResBillet(idUtilisateur, idFestival, jour, nbPlacesSansCateg, nbPlaceCateg1, nbPlaceCateg2);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<PaquetsBillets> billetFestival(int idFestival) {
		System.out.println("banks to banks");
		ArrayList<PaquetsBillets> paquets = new ArrayList<PaquetsBillets>();
		paquets = this.billetFestivalSql(idFestival);
		return paquets;

	}
}
