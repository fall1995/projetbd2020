package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import connexionBase.SQLAble;
import DAOInterfaces.FestivalInterface;
import mesClassesMetier.Festival;

public class FestivalDAO extends SQLAble implements FestivalInterface {

	@Override
	public void addFestival() {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateFestival(int nbplace, int idFestivale) {
		// TODO Auto-generated method stub

	}

	@Override // modifier string de date
	public ArrayList<Festival> getFestival() {

		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String s = "null";
		ArrayList<Festival> festivals = new ArrayList<Festival>();// Liste pour recuperer mon resultat

		// requete si l'utilisateur n'a pas affiné sa recherche
		System.out.println("avant try \n");
		try {
			Festival fest;
			// PreparedStatement ps;
			Statement ps = conn.createStatement();
			System.out.println("avant select \n");
			// ps = conn.prepareStatement("SELECT * FROM LesFestivals");
			String query = "SELECT * FROM ( select distinct(idFestival) from LesFestivals natural join LesPaquetsPlaces where nbPlacesRestantesCateg1 > 0 or nbPlacesRestantesCateg2 > 0 or nbPlacesRestantesSansCateg > 0) A NATURAL JOIN (select * from LesFestivals) B";
			// ps.setInt(1, 0);
			
			ResultSet resultats = ps.executeQuery(query);
			System.out.println(" apres query \n");
			// Parcours des resulats (objet ResulSet) retournés par executeQuery()
			int idFestival;

			String nomFestival;
			String domaine;
			String complementDomaine;
			String region;
			int departement;
			String periodicite;
			String moiHabDebut;
			String siteWeb;
			String commune;
			Date dateDebut;
			Date dateFin;
			Date dateCreation;
			int codepost;
			String codeINSEE;
			float coord1;
			float coord2;
			String nomDepartement;
			int nbPlaceLouees;
			Date dateAjout;
			String idUtilisateur;

			while (resultats.next()) {
				idFestival = resultats.getInt(1);
				// System.out.println("id festival = "+idFestival + "\n");
				nomFestival = resultats.getString(2);
				// System.out.println("nomFestival = "+nomFestival + "\n");
				domaine = resultats.getString(3);
				// System.out.println("domaine = "+domaine + "\n");
				complementDomaine = resultats.getString(4);
				// System.out.println("complement dom = "+complementDomaine + "\n");
				region = resultats.getString(5);
				departement = resultats.getInt(6);
				periodicite = resultats.getString(7);
				moiHabDebut = resultats.getString(8);
				// System.out.println("moiHabDebut = "+moiHabDebut + "\n");
				siteWeb = resultats.getString(9);
				// System.out.println("siteWeb = "+siteWeb + "\n");
				commune = resultats.getString(10);
				 //System.out.println("commune = "+commune + "\n");
				dateDebut = resultats.getDate(11);
				//System.out.println("dateDebut = "+dateDebut + "\n");
				dateFin = resultats.getDate(12);
				// System.out.println("dateFin = "+dateFin + "\n");
				dateCreation = resultats.getDate(13);
				// System.out.println("datecreation = "+dateCreation + "\n");
				codepost = resultats.getInt(14);
				// System.out.println("codeP = "+codepost + "\n");
				codeINSEE = resultats.getString(15);
				//System.out.println("codeInsee = "+codeINSEE + "\n");
				coord1 = resultats.getFloat(16);
				// System.out.println("coord1 = "+coord1 + "\n");
				coord2 = resultats.getFloat(17);
				//System.out.println("coord2 = "+coord2 + "\n");
				nomDepartement = resultats.getString(18);
				// System.out.println("nomDepartement = "+nomDepartement + "\n");
				nbPlaceLouees = resultats.getInt(19);
				dateAjout = resultats.getDate(20);
				//System.out.println("avant utilisateur");
				idUtilisateur = resultats.getString(21);
				// System.out.println("id utilisateur "+idUtilisateur);

				// j'instancie la classe festival et je l'ajoute a ma liste de festivale
				fest = new Festival(idFestival, nomFestival, domaine, complementDomaine, region, departement,
						periodicite, moiHabDebut, siteWeb, commune, dateDebut, dateFin, dateCreation, codepost,
						codeINSEE, coord1, coord2, nomDepartement, nbPlaceLouees, dateAjout, idUtilisateur);
				// System.out.println("Element de ma liste "+ fest.getIdUtilisateur());
                               // System.out.println("avant d'ajouter");
				if(dateDebut != null || dateFin != null){
                                  //  System.out.println("dedans");
                                festivals.add(fest);
                                }
			}
			resultats.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n" + festivals.size());
		return festivals;
		// return null;
	}

	public ArrayList<Festival> filtreDomaineSql(String domainesql) {

		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String s = "null";
		System.out.println("domaine sql =" + domainesql);
		domainesql = "'" + domainesql + "'";
		ArrayList<Festival> festivals = new ArrayList<Festival>();// Liste pour recuperer mon resultat

		// requete si l'utilisateur n'a pas affiné sa recherche
		System.out.println("avant try \n");
		try {
			Festival fest;
			// PreparedStatement ps;
			Statement ps = conn.createStatement();
			System.out.println("avant select \n");
			// ps = conn.prepareStatement("SELECT * FROM LesFestivals");
			String query = "SELECT * FROM LesFestivals where domaine=" + domainesql + "";
			// ps.setInt(1, 0);
			System.out.println(" apres ps \n");
			ResultSet resultats = ps.executeQuery(query);

			// Parcours des resulats (objet ResulSet) retournés par executeQuery()
			int idFestival;

			String nomFestival;

			String domaine;
			String complementDomaine;
			String region;
			int departement;
			String periodicite;
			String moiHabDebut;
			String siteWeb;
			String commune;
			Date dateDebut;
			Date dateFin;
			Date dateCreation;
			int codepost;
			String codeINSEE;
			float coord1;
			float coord2;
			String nomDepartement;
			int nbPlaceLouees;
			Date dateAjout;
			String idUtilisateur;

			while (resultats.next()) {
				idFestival = resultats.getInt(1);
				// System.out.println("id festival = "+idFestival + "\n");
				nomFestival = resultats.getString(2);
				// System.out.println("nomFestival = "+nomFestival + "\n");
				domaine = resultats.getString(3);
				// System.out.println("domaine = "+domaine + "\n");
				complementDomaine = resultats.getString(4);
				// System.out.println("complement dom = "+complementDomaine + "\n");
				region = resultats.getString(5);
				departement = resultats.getInt(6);
				periodicite = resultats.getString(7);
				moiHabDebut = resultats.getString(8);
				// System.out.println("moiHabDebut = "+moiHabDebut + "\n");
				siteWeb = resultats.getString(9);
				// System.out.println("siteWeb = "+siteWeb + "\n");
				commune = resultats.getString(10);
				// System.out.println("commune = "+commune + "\n");
				dateDebut = resultats.getDate(11);
				// System.out.println("dateDebut = "+dateDebut + "\n");
				dateFin = resultats.getDate(12);
				// System.out.println("dateFin = "+dateFin + "\n");
				dateCreation = resultats.getDate(13);
				// System.out.println("datecreation = "+dateCreation + "\n");
				codepost = resultats.getInt(14);
				// System.out.println("codeP = "+codepost + "\n");
				codeINSEE = resultats.getString(15);
				// System.out.println("codeInsee = "+codeINSEE + "\n");
				coord1 = resultats.getFloat(16);
				// System.out.println("coord1 = "+coord1 + "\n");
				coord2 = resultats.getFloat(17);
				// System.out.println("coord2 = "+coord2 + "\n");
				nomDepartement = resultats.getString(18);
				// System.out.println("nomDepartement = "+nomDepartement + "\n");
				nbPlaceLouees = resultats.getInt(19);
				dateAjout = resultats.getDate(20);
				idUtilisateur = resultats.getString(21);
				// System.out.println("id utilisateur "+idUtilisateur);

				// j'instancie la classe festival et je l'ajoute a ma liste de festivale
				fest = new Festival(idFestival, nomFestival, domaine, complementDomaine, region, departement,
						periodicite, moiHabDebut, siteWeb, commune, dateDebut, dateFin, dateCreation, codepost,
						codeINSEE, coord1, coord2, nomDepartement, nbPlaceLouees, dateAjout, idUtilisateur);
				// System.out.println("Element de ma liste "+ fest.getIdUtilisateur());
				festivals.add(fest);
			}
			resultats.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n" + festivals.size());
		return festivals;
		// return null;
	}

	public ArrayList<Festival> filtreVilleSQL(String ville) {

		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String s = "null";
		ville = "'" + ville + "'";
		ArrayList<Festival> festivals = new ArrayList<Festival>();// Liste pour recuperer mon resultat

		// requete si l'utilisateur n'a pas affiné sa recherche
		System.out.println("avant try \n");
		try {
			Festival fest;
			// PreparedStatement ps;
			Statement ps = conn.createStatement();
			System.out.println("avant select \n");
			// ps = conn.prepareStatement("SELECT * FROM LesFestivals");
			String query = "SELECT * FROM LesFestivals where commune = " + ville + "";
			// ps.setInt(1, 0);
			System.out.println(" apres ps \n");
			ResultSet resultats = ps.executeQuery(query);

			// Parcours des resulats (objet ResulSet) retournés par executeQuery()
			int idFestival;

			String nomFestival;

			String domaine;
			String complementDomaine;
			String region;
			int departement;
			String periodicite;
			String moiHabDebut;
			String siteWeb;
			String commune;
			Date dateDebut;
			Date dateFin;
			Date dateCreation;
			int codepost;
			String codeINSEE;
			float coord1;
			float coord2;
			String nomDepartement;
			int nbPlaceLouees;
			Date dateAjout;
			String idUtilisateur;

			while (resultats.next()) {
				idFestival = resultats.getInt(1);
				// System.out.println("id festival = "+idFestival + "\n");
				nomFestival = resultats.getString(2);
				// System.out.println("nomFestival = "+nomFestival + "\n");
				domaine = resultats.getString(3);
				// System.out.println("domaine = "+domaine + "\n");
				complementDomaine = resultats.getString(4);
				// System.out.println("complement dom = "+complementDomaine + "\n");
				region = resultats.getString(5);
				departement = resultats.getInt(6);
				periodicite = resultats.getString(7);
				moiHabDebut = resultats.getString(8);
				// System.out.println("moiHabDebut = "+moiHabDebut + "\n");
				siteWeb = resultats.getString(9);
				// System.out.println("siteWeb = "+siteWeb + "\n");
				commune = resultats.getString(10);
				// System.out.println("commune = "+commune + "\n");
				dateDebut = resultats.getDate(11);
				// System.out.println("dateDebut = "+dateDebut + "\n");
				dateFin = resultats.getDate(12);
				// System.out.println("dateFin = "+dateFin + "\n");
				dateCreation = resultats.getDate(13);
				// System.out.println("datecreation = "+dateCreation + "\n");
				codepost = resultats.getInt(14);
				// System.out.println("codeP = "+codepost + "\n");
				codeINSEE = resultats.getString(15);
				// System.out.println("codeInsee = "+codeINSEE + "\n");
				coord1 = resultats.getFloat(16);
				// System.out.println("coord1 = "+coord1 + "\n");
				coord2 = resultats.getFloat(17);
				// System.out.println("coord2 = "+coord2 + "\n");
				nomDepartement = resultats.getString(18);
				// System.out.println("nomDepartement = "+nomDepartement + "\n");
				nbPlaceLouees = resultats.getInt(19);
				dateAjout = resultats.getDate(20);
				idUtilisateur = resultats.getString(21);
				// System.out.println("id utilisateur "+idUtilisateur);

				// j'instancie la classe festival et je l'ajoute a ma liste de festivale
				fest = new Festival(idFestival, nomFestival, domaine, complementDomaine, region, departement,
						periodicite, moiHabDebut, siteWeb, commune, dateDebut, dateFin, dateCreation, codepost,
						codeINSEE, coord1, coord2, nomDepartement, nbPlaceLouees, dateAjout, idUtilisateur);
				// System.out.println("Element de ma liste "+ fest.getIdUtilisateur());
				festivals.add(fest);
			}
			resultats.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n" + festivals.size());
		return festivals;
		// return null;
	}
	
	public ArrayList<Festival> filtreDomaineVilleSql(String domainesql, String ville) {

		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String s = "null";
		ville = "'" + ville + "'";
		domainesql = "'" + domainesql + "'";
		ArrayList<Festival> festivals = new ArrayList<Festival>();// Liste pour recuperer mon resultat

		// requete si l'utilisateur n'a pas affiné sa recherche
		System.out.println("avant try \n");
		try {
			Festival fest;
			// PreparedStatement ps;
			Statement ps = conn.createStatement();
			System.out.println("avant select \n");
			// ps = conn.prepareStatement("SELECT * FROM LesFestivals");
			String query = "SELECT * FROM LesFestivals where commune = " + ville + "and domaine = "+ domainesql+"";
			// ps.setInt(1, 0);
			System.out.println(" apres ps \n");
			ResultSet resultats = ps.executeQuery(query);

			// Parcours des resulats (objet ResulSet) retournés par executeQuery()
			int idFestival;

			String nomFestival;

			String domaine;
			String complementDomaine;
			String region;
			int departement;
			String periodicite;
			String moiHabDebut;
			String siteWeb;
			String commune;
			Date dateDebut;
			Date dateFin;
			Date dateCreation;
			int codepost;
			String codeINSEE;
			float coord1;
			float coord2;
			String nomDepartement;
			int nbPlaceLouees;
			Date dateAjout;
			String idUtilisateur;

			while (resultats.next()) {
				idFestival = resultats.getInt(1);
				// System.out.println("id festival = "+idFestival + "\n");
				nomFestival = resultats.getString(2);
				// System.out.println("nomFestival = "+nomFestival + "\n");
				domaine = resultats.getString(3);
				// System.out.println("domaine = "+domaine + "\n");
				complementDomaine = resultats.getString(4);
				// System.out.println("complement dom = "+complementDomaine + "\n");
				region = resultats.getString(5);
				departement = resultats.getInt(6);
				periodicite = resultats.getString(7);
				moiHabDebut = resultats.getString(8);
				// System.out.println("moiHabDebut = "+moiHabDebut + "\n");
				siteWeb = resultats.getString(9);
				// System.out.println("siteWeb = "+siteWeb + "\n");
				commune = resultats.getString(10);
				// System.out.println("commune = "+commune + "\n");
				dateDebut = resultats.getDate(11);
				// System.out.println("dateDebut = "+dateDebut + "\n");
				dateFin = resultats.getDate(12);
				// System.out.println("dateFin = "+dateFin + "\n");
				dateCreation = resultats.getDate(13);
				// System.out.println("datecreation = "+dateCreation + "\n");
				codepost = resultats.getInt(14);
				// System.out.println("codeP = "+codepost + "\n");
				codeINSEE = resultats.getString(15);
				// System.out.println("codeInsee = "+codeINSEE + "\n");
				coord1 = resultats.getFloat(16);
				// System.out.println("coord1 = "+coord1 + "\n");
				coord2 = resultats.getFloat(17);
				// System.out.println("coord2 = "+coord2 + "\n");
				nomDepartement = resultats.getString(18);
				// System.out.println("nomDepartement = "+nomDepartement + "\n");
				nbPlaceLouees = resultats.getInt(19);
				dateAjout = resultats.getDate(20);
				idUtilisateur = resultats.getString(21);
				// System.out.println("id utilisateur "+idUtilisateur);

				// j'instancie la classe festival et je l'ajoute a ma liste de festivale
				fest = new Festival(idFestival, nomFestival, domaine, complementDomaine, region, departement,
						periodicite, moiHabDebut, siteWeb, commune, dateDebut, dateFin, dateCreation, codepost,
						codeINSEE, coord1, coord2, nomDepartement, nbPlaceLouees, dateAjout, idUtilisateur);
				// System.out.println("Element de ma liste "+ fest.getIdUtilisateur());
				festivals.add(fest);
			}
			resultats.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n" + festivals.size());
		return festivals;
		// return null;
	}

	

	public ArrayList<Festival> filtreDateSql(String dateD, String dateF) {

		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String s = "null";
		dateD = "'" + dateD + "'";
		dateF = "'" + dateF + "'";
		ArrayList<Festival> festivals = new ArrayList<Festival>();// Liste pour recuperer mon resultat

		// requete si l'utilisateur n'a pas affiné sa recherche
		try {
			Festival fest;
			// PreparedStatement ps;
			Statement ps = conn.createStatement();
			// ps = conn.prepareStatement("SELECT * FROM LesFestivals");
			String query = "SELECT * FROM LesFestivals where " + "dateDebut >= TO_Date(" + dateD
					+ ",'DD-MM-YY') and dateFin <= TO_DATE(" + dateF + ",'DD-MM-YY')";
			// ps.setInt(1, 0);
			ResultSet resultats = ps.executeQuery(query);

			// Parcours des resulats (objet ResulSet) retournés par executeQuery()
			int idFestival;

			String nomFestival;

			String domaine;
			String complementDomaine;
			String region;
			int departement;
			String periodicite;
			String moiHabDebut;
			String siteWeb;
			String commune;
			Date dateDebut;
			Date dateFin;
			Date dateCreation;
			int codepost;
			String codeINSEE;
			float coord1;
			float coord2;
			String nomDepartement;
			int nbPlaceLouees;
			Date dateAjout;
			String idUtilisateur;

			while (resultats.next()) {
				idFestival = resultats.getInt(1);
				// System.out.println("id festival = "+idFestival + "\n");
				nomFestival = resultats.getString(2);
				// System.out.println("nomFestival = "+nomFestival + "\n");
				domaine = resultats.getString(3);
				// System.out.println("domaine = "+domaine + "\n");
				complementDomaine = resultats.getString(4);
				// System.out.println("complement dom = "+complementDomaine + "\n");
				region = resultats.getString(5);
				departement = resultats.getInt(6);
				periodicite = resultats.getString(7);
				moiHabDebut = resultats.getString(8);
				// System.out.println("moiHabDebut = "+moiHabDebut + "\n");
				siteWeb = resultats.getString(9);
				// System.out.println("siteWeb = "+siteWeb + "\n");
				commune = resultats.getString(10);
				// System.out.println("commune = "+commune + "\n");
				dateDebut = resultats.getDate(11);
				// System.out.println("dateDebut = "+dateDebut + "\n");
				dateFin = resultats.getDate(12);
				// System.out.println("dateFin = "+dateFin + "\n");
				dateCreation = resultats.getDate(13);
				// System.out.println("datecreation = "+dateCreation + "\n");
				codepost = resultats.getInt(14);
				// System.out.println("codeP = "+codepost + "\n");
				codeINSEE = resultats.getString(15);
				// System.out.println("codeInsee = "+codeINSEE + "\n");
				coord1 = resultats.getFloat(16);
				// System.out.println("coord1 = "+coord1 + "\n");
				coord2 = resultats.getFloat(17);
				// System.out.println("coord2 = "+coord2 + "\n");
				nomDepartement = resultats.getString(18);
				// System.out.println("nomDepartement = "+nomDepartement + "\n");
				nbPlaceLouees = resultats.getInt(19);
				dateAjout = resultats.getDate(20);
				idUtilisateur = resultats.getString(21);
				// System.out.println("id utilisateur "+idUtilisateur);

				// j'instancie la classe festival et je l'ajoute a ma liste de festivale
				fest = new Festival(idFestival, nomFestival, domaine, complementDomaine, region, departement,
						periodicite, moiHabDebut, siteWeb, commune, dateDebut, dateFin, dateCreation, codepost,
						codeINSEE, coord1, coord2, nomDepartement, nbPlaceLouees, dateAjout, idUtilisateur);
				// System.out.println("Element de ma liste "+ fest.getIdUtilisateur());
				festivals.add(fest);
			}
			resultats.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n" + festivals.size());
		return festivals;
		// return null;
	}

	public ArrayList<Festival> filtreDateDomaineSql(String dateD, String dateF, String domainesql) {

		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String s = "null";
		domainesql = "'" + domainesql + "'";
		dateD = "'" + dateD + "'";
		dateF = "'" + dateF + "'";
		ArrayList<Festival> festivals = new ArrayList<Festival>();// Liste pour recuperer mon resultat

		// requete si l'utilisateur n'a pas affiné sa recherche
		try {
			Festival fest;
			// PreparedStatement ps;
			Statement ps = conn.createStatement();
			// ps = conn.prepareStatement("SELECT * FROM LesFestivals");
			String query = "SELECT * FROM LesFestivals where " + "dateDebut >= TO_Date(" + dateD
					+ ",'DD-MM-YY') and dateFin <= TO_DATE(" + dateF + ",'DD-MM-YY') and domaine = " + domainesql + "";
			// ps.setInt(1, 0);
			ResultSet resultats = ps.executeQuery(query);

			// Parcours des resulats (objet ResulSet) retournés par executeQuery()
			int idFestival;

			String nomFestival;

			String domaine;
			String complementDomaine;
			String region;
			int departement;
			String periodicite;
			String moiHabDebut;
			String siteWeb;
			String commune;
			Date dateDebut;
			Date dateFin;
			Date dateCreation;
			int codepost;
			String codeINSEE;
			float coord1;
			float coord2;
			String nomDepartement;
			int nbPlaceLouees;
			Date dateAjout;
			String idUtilisateur;

			while (resultats.next()) {
				idFestival = resultats.getInt(1);
				// System.out.println("id festival = "+idFestival + "\n");
				nomFestival = resultats.getString(2);
				// System.out.println("nomFestival = "+nomFestival + "\n");
				domaine = resultats.getString(3);
				// System.out.println("domaine = "+domaine + "\n");
				complementDomaine = resultats.getString(4);
				// System.out.println("complement dom = "+complementDomaine + "\n");
				region = resultats.getString(5);
				departement = resultats.getInt(6);
				periodicite = resultats.getString(7);
				moiHabDebut = resultats.getString(8);
				// System.out.println("moiHabDebut = "+moiHabDebut + "\n");
				siteWeb = resultats.getString(9);
				// System.out.println("siteWeb = "+siteWeb + "\n");
				commune = resultats.getString(10);
				// System.out.println("commune = "+commune + "\n");
				dateDebut = resultats.getDate(11);
				// System.out.println("dateDebut = "+dateDebut + "\n");
				dateFin = resultats.getDate(12);
				// System.out.println("dateFin = "+dateFin + "\n");
				dateCreation = resultats.getDate(13);
				// System.out.println("datecreation = "+dateCreation + "\n");
				codepost = resultats.getInt(14);
				// System.out.println("codeP = "+codepost + "\n");
				codeINSEE = resultats.getString(15);
				// System.out.println("codeInsee = "+codeINSEE + "\n");
				coord1 = resultats.getFloat(16);
				// System.out.println("coord1 = "+coord1 + "\n");
				coord2 = resultats.getFloat(17);
				// System.out.println("coord2 = "+coord2 + "\n");
				nomDepartement = resultats.getString(18);
				// System.out.println("nomDepartement = "+nomDepartement + "\n");
				nbPlaceLouees = resultats.getInt(19);
				dateAjout = resultats.getDate(20);
				idUtilisateur = resultats.getString(21);
				// System.out.println("id utilisateur "+idUtilisateur);

				// j'instancie la classe festival et je l'ajoute a ma liste de festivale
				fest = new Festival(idFestival, nomFestival, domaine, complementDomaine, region, departement,
						periodicite, moiHabDebut, siteWeb, commune, dateDebut, dateFin, dateCreation, codepost,
						codeINSEE, coord1, coord2, nomDepartement, nbPlaceLouees, dateAjout, idUtilisateur);
				// System.out.println("Element de ma liste "+ fest.getIdUtilisateur());
				festivals.add(fest);
			}
			resultats.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n" + festivals.size());
		return festivals;
		// return null;
	}

	public ArrayList<Festival> filtreDateVilleSql(String dateD, String dateF, String ville) {

		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String s = "null";
		ville = "'" + ville + "'";
		dateD = "'" + dateD + "'";
		dateF = "'" + dateF + "'";
		ArrayList<Festival> festivals = new ArrayList<Festival>();// Liste pour recuperer mon resultat

		// requete si l'utilisateur n'a pas affiné sa recherche
		try {
			Festival fest;
			// PreparedStatement ps;
			Statement ps = conn.createStatement();
			// ps = conn.prepareStatement("SELECT * FROM LesFestivals");
			String query = "SELECT * FROM LesFestivals where " + "dateDebut >= TO_Date(" + dateD
					+ ",'DD-MM-YY') and dateFin <= TO_DATE(" + dateF + ",'DD-MM-YY') and commune = " + ville + "";
			// ps.setInt(1, 0);
			ResultSet resultats = ps.executeQuery(query);

			// Parcours des resulats (objet ResulSet) retournés par executeQuery()
			int idFestival;

			String nomFestival;

			String domaine;
			String complementDomaine;
			String region;
			int departement;
			String periodicite;
			String moiHabDebut;
			String siteWeb;
			String commune;
			Date dateDebut;
			Date dateFin;
			Date dateCreation;
			int codepost;
			String codeINSEE;
			float coord1;
			float coord2;
			String nomDepartement;
			int nbPlaceLouees;
			Date dateAjout;
			String idUtilisateur;

			while (resultats.next()) {
				idFestival = resultats.getInt(1);
				// System.out.println("id festival = "+idFestival + "\n");
				nomFestival = resultats.getString(2);
				// System.out.println("nomFestival = "+nomFestival + "\n");
				domaine = resultats.getString(3);
				// System.out.println("domaine = "+domaine + "\n");
				complementDomaine = resultats.getString(4);
				// System.out.println("complement dom = "+complementDomaine + "\n");
				region = resultats.getString(5);
				departement = resultats.getInt(6);
				periodicite = resultats.getString(7);
				moiHabDebut = resultats.getString(8);
				// System.out.println("moiHabDebut = "+moiHabDebut + "\n");
				siteWeb = resultats.getString(9);
				// System.out.println("siteWeb = "+siteWeb + "\n");
				commune = resultats.getString(10);
				// System.out.println("commune = "+commune + "\n");
				dateDebut = resultats.getDate(11);
				// System.out.println("dateDebut = "+dateDebut + "\n");
				dateFin = resultats.getDate(12);
				// System.out.println("dateFin = "+dateFin + "\n");
				dateCreation = resultats.getDate(13);
				// System.out.println("datecreation = "+dateCreation + "\n");
				codepost = resultats.getInt(14);
				// System.out.println("codeP = "+codepost + "\n");
				codeINSEE = resultats.getString(15);
				// System.out.println("codeInsee = "+codeINSEE + "\n");
				coord1 = resultats.getFloat(16);
				// System.out.println("coord1 = "+coord1 + "\n");
				coord2 = resultats.getFloat(17);
				// System.out.println("coord2 = "+coord2 + "\n");
				nomDepartement = resultats.getString(18);
				// System.out.println("nomDepartement = "+nomDepartement + "\n");
				nbPlaceLouees = resultats.getInt(19);
				dateAjout = resultats.getDate(20);
				idUtilisateur = resultats.getString(21);
				// System.out.println("id utilisateur "+idUtilisateur);

				// j'instancie la classe festival et je l'ajoute a ma liste de festivale
				fest = new Festival(idFestival, nomFestival, domaine, complementDomaine, region, departement,
						periodicite, moiHabDebut, siteWeb, commune, dateDebut, dateFin, dateCreation, codepost,
						codeINSEE, coord1, coord2, nomDepartement, nbPlaceLouees, dateAjout, idUtilisateur);
				// System.out.println("Element de ma liste "+ fest.getIdUtilisateur());
				festivals.add(fest);
			}
			resultats.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n" + festivals.size());
		return festivals;
		// return null;
	}
	
	public ArrayList<Festival> filtreDateVilleDomaineSql(String dateD, String dateF, String ville, String domainesql) {

		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String s = "null";
		ville = "'" + ville + "'";
		dateD = "'" + dateD + "'";
		dateF = "'" + dateF + "'";
		domainesql = "'" + domainesql + "'";
		ArrayList<Festival> festivals = new ArrayList<Festival>();// Liste pour recuperer mon resultat

		// requete si l'utilisateur n'a pas affiné sa recherche
		try {
			Festival fest;
			// PreparedStatement ps;
			Statement ps = conn.createStatement();
			// ps = conn.prepareStatement("SELECT * FROM LesFestivals");
			String query = "SELECT * FROM LesFestivals where " + "dateDebut >= TO_Date(" + dateD
					+ ",'DD-MM-YY') and dateFin <= TO_DATE(" + dateF + ",'DD-MM-YY') and commune = " + ville + "and domaine ="+domainesql+"";
			// ps.setInt(1, 0);
			ResultSet resultats = ps.executeQuery(query);

			// Parcours des resulats (objet ResulSet) retournés par executeQuery()
			int idFestival;

			String nomFestival;

			String domaine;
			String complementDomaine;
			String region;
			int departement;
			String periodicite;
			String moiHabDebut;
			String siteWeb;
			String commune;
			Date dateDebut;
			Date dateFin;
			Date dateCreation;
			int codepost;
			String codeINSEE;
			float coord1;
			float coord2;
			String nomDepartement;
			int nbPlaceLouees;
			Date dateAjout;
			String idUtilisateur;

			while (resultats.next()) {
				idFestival = resultats.getInt(1);
				// System.out.println("id festival = "+idFestival + "\n");
				nomFestival = resultats.getString(2);
				// System.out.println("nomFestival = "+nomFestival + "\n");
				domaine = resultats.getString(3);
				// System.out.println("domaine = "+domaine + "\n");
				complementDomaine = resultats.getString(4);
				// System.out.println("complement dom = "+complementDomaine + "\n");
				region = resultats.getString(5);
				departement = resultats.getInt(6);
				periodicite = resultats.getString(7);
				moiHabDebut = resultats.getString(8);
				// System.out.println("moiHabDebut = "+moiHabDebut + "\n");
				siteWeb = resultats.getString(9);
				// System.out.println("siteWeb = "+siteWeb + "\n");
				commune = resultats.getString(10);
				// System.out.println("commune = "+commune + "\n");
				dateDebut = resultats.getDate(11);
				// System.out.println("dateDebut = "+dateDebut + "\n");
				dateFin = resultats.getDate(12);
				// System.out.println("dateFin = "+dateFin + "\n");
				dateCreation = resultats.getDate(13);
				// System.out.println("datecreation = "+dateCreation + "\n");
				codepost = resultats.getInt(14);
				// System.out.println("codeP = "+codepost + "\n");
				codeINSEE = resultats.getString(15);
				// System.out.println("codeInsee = "+codeINSEE + "\n");
				coord1 = resultats.getFloat(16);
				// System.out.println("coord1 = "+coord1 + "\n");
				coord2 = resultats.getFloat(17);
				// System.out.println("coord2 = "+coord2 + "\n");
				nomDepartement = resultats.getString(18);
				// System.out.println("nomDepartement = "+nomDepartement + "\n");
				nbPlaceLouees = resultats.getInt(19);
				dateAjout = resultats.getDate(20);
				idUtilisateur = resultats.getString(21);
				// System.out.println("id utilisateur "+idUtilisateur);

				// j'instancie la classe festival et je l'ajoute a ma liste de festivale
				fest = new Festival(idFestival, nomFestival, domaine, complementDomaine, region, departement,
						periodicite, moiHabDebut, siteWeb, commune, dateDebut, dateFin, dateCreation, codepost,
						codeINSEE, coord1, coord2, nomDepartement, nbPlaceLouees, dateAjout, idUtilisateur);
				// System.out.println("Element de ma liste "+ fest.getIdUtilisateur());
				festivals.add(fest);
			}
			resultats.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n" + festivals.size());
		return festivals;
		// return null;
	}
	
	public boolean SupprimerSql(String idFestival) {
		

		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String s = "null";
		int id =Integer.parseInt(idFestival);		 
		System.out.println("idFestival avant delelete"+idFestival);
		
		
		
		ArrayList<Festival> festivals = new ArrayList<Festival>();// Liste pour recuperer mon resultat

		// requete si l'utilisateur n'a pas affiné sa recherche
		try {
			Festival fest;
			// PreparedStatement ps;
			Statement ps = conn.createStatement();
			// ps = conn.prepareStatement("SELECT * FROM LesFestivals");
			String query = "DELETE from LesFestivals WHERE idFestival = "+idFestival+"";
			// ps.setInt(1, 0);
			ResultSet resultats = ps.executeQuery(query);

			// Parcours des resulats (objet ResulSet) retournés par executeQuery()


		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}

		System.out.println("\n" + festivals.size());
		return true;
		// return null;
	}
		
	
	
	
	
	
	
	
	
	
	
	public boolean supprimerFestival(String idFestival) {
		if( supprimerF(idFestival)) {
			if(SupprimerSql(idFestival)) {
				return true;
			}else {
				return false;
			}
		}
		return false;
		
	}

	public ArrayList<Festival> affiner(String domaineF, String dateDebutF, String dateFinF, String Ville) {
		System.out.println("domaineF = " + domaineF + "\n");
		System.out.println("dateDebutF = " + dateDebutF + "\n");
		System.out.println("dateFinF = " + dateFinF + "\n");
		System.out.println("Ville = " + Ville + "\n");
		
		
		

		if (aucunFiltre(domaineF, dateDebutF, dateFinF, Ville)) {
			 System.out.println("dans aucun filtre");
			ArrayList<Festival> festivals = new ArrayList<Festival>();
			festivals = this.getFestival();
			System.out.println("apres appelle fonction get  ");
			return festivals;
			
		}
		// System.out.println("apres aucun filtre");
		// filtre uniquement sur ville

		if (villeFiltre(domaineF, dateDebutF, dateFinF, Ville)) {
			ArrayList<Festival> festivals = new ArrayList<Festival>();
			festivals = this.filtreVilleSQL(Ville);
			return festivals;
		}
		// filtre uniquement sur domaine
		if (domaineFiltre(domaineF, dateDebutF, dateFinF, Ville)) {
			ArrayList<Festival> festivals = new ArrayList<Festival>();
			festivals = this.filtreDomaineSql(domaineF);
			return festivals;
		}
		// filtre uniquement sur date debut et fin
		if (dateFiltre(domaineF, dateDebutF, dateFinF, Ville)) {
			ArrayList<Festival> festivals = new ArrayList<Festival>();
			festivals = this.filtreDateSql(dateDebutF, dateFinF);
			return festivals;
		}
		// filtre sur domaine et date debut et fin
		if (dateDomaineFiltre(domaineF, dateDebutF, dateFinF, Ville)) {
			System.out.println("banks to banks 2");
			ArrayList<Festival> festivals = new ArrayList<Festival>();
			festivals = this.filtreDateDomaineSql(dateDebutF, dateFinF, domaineF);
			return festivals;
		}

		// filtre sur ville et date Debut et fin
		if (dateVilleFiltre(domaineF, dateDebutF, dateFinF, Ville)) {
			System.out.println("banks to banks");
			ArrayList<Festival> festivals = new ArrayList<Festival>();
			festivals = this.filtreDateVilleSql(dateDebutF, dateFinF, Ville);
			return festivals;
		}

		// filtre sur ville et domaine
		if (villeDomaineFiltre(domaineF, dateDebutF, dateFinF, Ville)) {
			System.out.println("banks to banks");
			ArrayList<Festival> festivals = new ArrayList<Festival>();
			festivals = this.filtreDomaineVilleSql(domaineF, Ville);
			return festivals;
		}
		
		//filtre sur ville dateDebut et Fin et domaine
		if (VilledateDomaineFiltre(domaineF, dateDebutF, dateFinF, Ville)) {
			System.out.println("banks to banks");
			ArrayList<Festival> festivals = new ArrayList<Festival>();
			festivals = this.filtreDateVilleDomaineSql(dateDebutF, dateFinF, Ville, domaineF);
			return festivals;
		}
		
		
		
		

		// filtre sur domaine et datedebut et date fin
		/*
		 * if (dateDomaineFiltre(domaineF, dateDebutF, dateFinF, Ville)) {
		 * ArrayList<Festival> festivals = new ArrayList<Festival>(); Date dateD = null;
		 * Date dateF = null;
		 * 
		 * try { dateD = new SimpleDateFormat("dd-MM-yyyy").parse(dateDebutF); dateF =
		 * new SimpleDateFormat("dd-MM-yyyy").parse(dateFinF);
		 * 
		 * } catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 * 
		 * ArrayList<Festival> resultat = new ArrayList<Festival>(); for (int i = 0; i <
		 * festivals.size(); i++) { // tester si date dans la base pas nul if
		 * (festivals.get(i).getDateDebut() != null && festivals.get(i).getDateFin() !=
		 * null) { if (EntreDateDomaine(festivals, domaineF, dateD, dateF, i)) {
		 * resultat.add(festivals.get(i)); } } } return resultat; }
		 */
		// filtre sur domaine et datedebut et date fin et ville
		/*
		 * if (VilledateDomaineFiltre(domaineF, dateDebutF, dateFinF, Ville)) {
		 * ArrayList<Festival> festivals = new ArrayList<Festival>();
		 * ArrayList<Festival> resultat = new ArrayList<Festival>(); Date dateD = null;
		 * Date dateF = null;
		 * 
		 * try { dateD = new SimpleDateFormat("dd-MM-yyyy").parse(dateDebutF); dateF =
		 * new SimpleDateFormat("dd-MM-yyyy").parse(dateFinF);
		 * 
		 * } catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } for (int i = 0; i < festivals.size(); i++) { if
		 * (festivals.get(i).getDateDebut() != null && festivals.get(i).getDateFin() !=
		 * null) { if (EntreDateDomaine(festivals, domaineF, dateD, dateF, i) &&
		 * Ville(festivals, Ville, i)) { resultat.add(festivals.get(i)); } } } return
		 * resultat; }
		 */
		// filtre sur dateDebut et dateFin et ville
		/*
		 * if (VilledateDomaineFiltre(domaineF, dateDebutF, dateFinF, Ville)) {
		 * ArrayList<Festival> festivals = new ArrayList<Festival>();
		 * ArrayList<Festival> resultat = new ArrayList<Festival>(); Date dateD = null;
		 * Date dateF = null;
		 * 
		 * try { dateD = new SimpleDateFormat("dd-MM-yyyy").parse(dateDebutF); dateF =
		 * new SimpleDateFormat("dd-MM-yyyy").parse(dateFinF);
		 * 
		 * } catch (ParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } for (int i = 0; i < festivals.size(); i++) { if
		 * (festivals.get(i).getDateDebut() != null && festivals.get(i).getDateFin() !=
		 * null) { if (EntreDate(festivals, domaineF, dateD, dateF, i) &&
		 * Ville(festivals, Ville, i)) { resultat.add(festivals.get(i)); } } } return
		 * resultat; }
		 */

		return null;
	}

	boolean aucunFiltre(String domaineF, String dateDebutF, String dateFinF, String Ville) {
		return ((domaineF == null || domaineF.isEmpty()) && (dateDebutF == null || dateDebutF.isEmpty())
				&& (dateFinF == null || dateFinF.isEmpty()) && (Ville == null || Ville.isEmpty()));

	}

	boolean domaineFiltre(String domaineF, String dateDebutF, String dateFinF, String Ville) {
		// System.out.println("dans boolean");
		return ((domaineF != null) && (dateDebutF == null || dateDebutF.isEmpty())
				&& (dateFinF == null || dateFinF.isEmpty()) && (Ville == null || Ville.isEmpty()));
	}

	boolean villeFiltre(String domaineF, String dateDebutF, String dateFinF, String Ville) {
		return ((domaineF == null || domaineF.isEmpty()) && (dateDebutF == null || dateDebutF.isEmpty())
				&& (dateFinF == null || dateFinF.isEmpty()) && (Ville != null || !Ville.isEmpty()));
	}

	boolean dateFiltre(String domaineF, String dateDebutF, String dateFinF, String Ville) {
		return ((domaineF == null || domaineF.isEmpty()) && (dateDebutF != null || !dateDebutF.isEmpty())
				&& (dateFinF != null || !dateFinF.isEmpty()) && (Ville == null || Ville.isEmpty()));
	}

	boolean dateVilleFiltre(String domaineF, String dateDebutF, String dateFinF, String Ville) {
		return ((domaineF != null ) && (dateDebutF != null )
				&& (dateFinF != null ) && (Ville != null || !Ville.isEmpty()));
	}

	boolean villeDomaineFiltre(String domaineF, String dateDebutF, String dateFinF, String Ville) {
		return ((domaineF != null || !domaineF.isEmpty()) && (dateDebutF == null )
				&& (dateFinF == null ) && (Ville != null || !Ville.isEmpty()));
	}


	boolean dateDomaineFiltre(String domaineF, String dateDebutF, String dateFinF, String Ville) {
		return ((domaineF != null ) && (dateDebutF != null )
				&& (dateFinF != null ) && (Ville == null || Ville.isEmpty()));
	}

	boolean VilledateDomaineFiltre(String domaineF, String dateDebutF, String dateFinF, String Ville) {
		return ((domaineF != null && !domaineF.isEmpty()) && (dateDebutF != null || !dateDebutF.isEmpty())
				&& (dateFinF != null || !dateFinF.isEmpty()) && (Ville != null || !Ville.isEmpty()));
	}

	
	boolean supprimerF(String idFestival) {
		return ((idFestival != null && !idFestival.isEmpty()));
	}
	
	
	
	// possible de le faire en combinant les autres filtres
	/*
	 * boolean EntreDateDomaine(ArrayList<Festival> festivals, String domaineF, Date
	 * dateD, Date dateF, int i) { return
	 * (festivals.get(i).getDomaine().equalsIgnoreCase(domaineF) // test domaines
	 * correspondant && (festivals.get(i).getDateDebut().after(dateD) // test date
	 * apres dateDebut && festivals.get(i).getDateFin().before(dateF) // test date
	 * avant date fin || festivals.get(i).getDateDebut().equals(dateD) // test date
	 * identique debut && festivals.get(i).getDateFin().equals(dateF)));// test date
	 * identique fin }
	 */

	/*
	 * boolean EntreDate(ArrayList<Festival> festivals, String domaineF, Date dateD,
	 * Date dateF, int i) { return ((festivals.get(i).getDateDebut().after(dateD) //
	 * test date apres dateDebut && festivals.get(i).getDateFin().before(dateF) //
	 * test date avant date fin || festivals.get(i).getDateDebut().equals(dateD) //
	 * test date identique debut && festivals.get(i).getDateFin().equals(dateF)));//
	 * test date identique fin }
	 */

	/*
	 * boolean Ville(ArrayList<Festival> festivals, String Ville, int i) { return
	 * (festivals.get(i).getCommune().equalsIgnoreCase(Ville)); }
	 */

	@Override
	public void deleteFestival(int idFestivale) {
		// TODO Auto-generated method stub

	}

}
