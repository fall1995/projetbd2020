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

public class ReservationLogementDAO extends SQLAble {

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

	public void supprimerResaTimer(String idUtilisateur) throws SQLException {
		try {
			connectToDatabase();
			int res = 0;
			System.out.println("supprimerResaTimer");

			Statement ps = conn.createStatement();
			idUtilisateur = "'" + idUtilisateur + "'";

			// pour rajouter timerr
			/*
			 * DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); Date date =
			 * new Date();
			 */
			/*
			 * String select =
			 * "select nbPlacesRestantesCateg1, nbPlacesRestantesCateg2, nbPlacesRestantesSansCateg,tarifSansCateg,tarifCateg1,tarifCateg2 from LesPaquetsPlaces where jour ="
			 * + jour + " and idFestival =" + idFestival + " for Update ";
			 */
			// ResultSet rs1 = ps.executeQuery(select);
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public boolean creerResaLogementChambre(String idUtilisateur, int numLogement, String jour, int nbPlaceAdulte,
			int nbPlaceEnfant) throws SQLException {
		/*
		 * System.out.println("nbcommandeS  ="+ nbPlacesSansCateg);
		 * System.out.println("nbcommandeC1  ="+ nbPlaceCateg1);
		 * System.out.println("nbcommandeC2  ="+ nbPlaceCateg2);
		 */
		System.out.println("-------------->idUtilisateur          " + idUtilisateur);
		System.out.println("-------------->numLogement        " + numLogement);
		System.out.println("-------------->jour       " + jour);
		System.out.println("-------------->nbPlaceAdulte          " + nbPlaceAdulte);
		System.out.println("-------------->nbPlaceEnfant                " + nbPlaceEnfant);

		try {
			connectToDatabase();
			System.out.println("après conn base");
			int count = 0;
			int res = 0;
			System.out.println("dans creerResLogement");
			Statement ps = conn.createStatement();
			idUtilisateur = "'" + idUtilisateur + "'";
			System.out.println("id Utilisateur =" + idUtilisateur);
			String str = this.str(jour);
			System.out.println("--------->" + str);
			str = "'" + str + "'";

		/*	String query15 = "select count(idUtilisateur) from LesUtilisateurs where idUtilisateur = " + idUtilisateur
					+ "";
			ResultSet rs8 = ps.executeQuery(query15);
			while (rs8.next()) {
				count = rs8.getInt(1);
			}
			System.out.println("count ==" + count);
			if (count > 0) {
				System.out.println("client deja existant");
			}

			if (count == 0) {
				// creer nouvel utilisateur
				int nb10 = ps
						.executeUpdate("INSERT INTO LesUtilisateurs ( idUtilisateur) VALUES (" + idUtilisateur + ")");
				System.out.println("Nombre de lignes insérées dans LesUtilisateurs = " + nb10);

				// creer nouveau client
				int nb7 = ps.executeUpdate("INSERT INTO LesClients ( idUtilisateur) VALUES (" + idUtilisateur + ")");
				System.out.println("Nombre de lignes insérées dans LesClients = " + nb7);
			}*/
			// creation du panier

			DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			// Date d= format.format(date)

			int nb9 = ps.executeUpdate(
					"INSERT INTO LesReservations ( idUtilisateur, prix, status) VALUES (" + idUtilisateur + ",0,'N')");
			System.out.println("Nombre de lignes insérées dans panier = " + nb9);

			int idReservation = -1;

			String query = "SELECT max(idReservation) from LesReservations where idUtilisateur =" + idUtilisateur + "";
			ResultSet rs = ps.executeQuery(query);
			while (rs.next()) {
				idReservation = rs.getInt(1);
			}
			System.out.println("idRes===" + idReservation);
			// voir les dispo
			String select = "select NBADULTES, NBENFANTS, TARIFADULTE, TARIFENFANT from LesChambres where numLogement ="
					+ numLogement + " for Update ";
			ResultSet rs1 = ps.executeQuery(select);
			int tarifAdulte = 0;
			int tarifEnfant = 0;
			int nbAdulte = 0;
			int nbEnfant = 0;

			while (rs1.next()) {
				nbAdulte = rs1.getInt(1);
				nbEnfant = rs1.getInt(2);
				tarifAdulte = rs1.getInt(3);
				tarifEnfant = rs1.getInt(4);

			}

			int totalPrixAdulte = tarifAdulte * nbAdulte;
			int totalPrixEnfant = tarifEnfant * nbEnfant;
			int prixTotal = totalPrixAdulte + totalPrixEnfant;
			System.out.println("prix total = " + prixTotal);
			jour = "'" + jour + "'";
			Statement upd = conn.createStatement();

			if (nbAdulte >= nbPlaceAdulte && nbEnfant >= nbPlaceEnfant) {
				System.out.println("dans le mille");
				int nb = ps.executeUpdate("DELETE FROM LesPeriodeDeDisponibilites WHERE dateDispo = TO_DATE(" + str
						+ ",'dd-MM-yy') and numLogement =" + numLogement + "");

				System.out.println("Nombre de lignes mises à jour dans update place = " + nb);
				if (nb > 0) {

					int nb2 = ps.executeUpdate(
							"INSERT INTO LesReservationLogements (idReservation, numLogement, prixResLogement, nbPlaceAdulte, "
									+ " nbPlaceEnfant) VALUES (" + idReservation + "," + numLogement + "," + prixTotal
									+ "," + nbPlaceAdulte + "," + nbPlaceEnfant + ")");
					System.out.println("Nombre de lignes insérées dans RESPAQUETPLACE = " + nb2);

				}System.out.println("commit");
				conn.commit();
				ps.close();
				return true;
				// upd.close();
			} else {

				// int nb = ps.executeUpdate("Delete from LesReservations where idReservation ="
				// + idReservation + "");
				// System.out.println("Nombre de lignes supprime dans LesReservations = " + nb);
				System.out.println("rollback");
				SQLAble.conn.rollback();
				ps.close();
				return false;
			}

			// this.disconnect();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			SQLAble.conn.rollback();
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}

	public boolean reserverLogement(String idUtilisateur, int numLogement, String jour, int nbPlaceAdulte,
			int nbPlaceEnfant) {
		boolean res = false;
		try {
			// this.creerPanierRes(idUtilisateur);

			res = this.creerResaLogementChambre(idUtilisateur, numLogement, jour, nbPlaceAdulte, nbPlaceEnfant);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;

	}

	public ArrayList<PaquetsBillets> billetFestival(int idFestival) {
		System.out.println("banks to banks");
		ArrayList<PaquetsBillets> paquets = new ArrayList<PaquetsBillets>();
		paquets = this.billetFestivalSql(idFestival);
		return paquets;

	}

	public String str(String s) {
		s = s.replace(".", "");
		s = s.replace(",", "");
		// System.out.println(s);
		String[] test = s.split(" ");
		String mois = test[0];
		String jour = test[1];
		String annee = test[2];

		String d = jour + "-" + mois + "-" + annee;
		System.out.println(d);
		switch (mois) {
		case "janv":
			mois = "01";
			break;
		case "févr":
			mois = "02";
			break;
		case "mars":
			mois = "03";
			break;
		case "avr":
			mois = "04";
			break;
		case "mai":
			mois = "05";
			break;
		case "juin":
			mois = "06";
			break;
		case "juil":
			mois = "07";
			break;
		case "août":
			mois = "08";
			break;
		case "sept":
			mois = "09";
			break;
		case "oct":
			mois = "10";
			break;
		case "nov":
			mois = "11";
			break;
		case "déc":
			mois = "12";
			break;

		}
		d = jour + "-" + mois + "-" + annee;
		return d;

	}
}
