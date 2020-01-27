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




public class PaquetsBilletsDAO extends SQLAble{

	
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
			String query = "select * from LesPaquetsPlaces where idFestival = "+idFestival+"";
			// ps.setInt(1, 0);
			ResultSet resultats = ps.executeQuery(query);

			// Parcours des resulats (objet ResulSet) retournés par executeQuery()
			

			int jour   ;
			int tarifSansCateg  ;
			int tarifCateg1    ;
			int tarifCateg2  ;
			int nbPlacesCateg1  ;
			int nbPlacesCateg2   ;
			int nbPlacesSansCateg  ;
			int nbPlacesRestantesCateg1 ;
			int nbPlacesRestantesCateg2 ;
			int nbPlacesRestantesSansCateg ;
			//int idFestival  ;

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
			

				// j'instancie la classe  et je l'ajoute a ma liste 
				billets = new PaquetsBillets(jour, tarifSansCateg, tarifCateg1, tarifCateg2, nbPlacesCateg1, nbPlacesCateg2, 
						nbPlacesSansCateg, nbPlacesRestantesCateg1, nbPlacesRestantesCateg2, nbPlacesRestantesSansCateg, idFestival);
				// System.out.println("Element de ma liste "+ fest.getIdUtilisateur());
				paquetsB.add(billets);
			}
			resultats.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		//System.out.println("\n" + festivals.size());
		return paquetsB;
		// return null;
	}
	
	
	public void creerPanierRes(String idUtilisateur) throws SQLException  {
		try {
			connectToDatabase();
			int res=0;
			Statement ps = conn.createStatement();
			idUtilisateur="'"+idUtilisateur+"'";
			
			//pour rajouter timerr
			/*DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();*/
			int nb = ps.executeUpdate("INSERT INTO LesReservations ( idUtilisateur, prix) VALUES ("+idUtilisateur+","+0+")"); 
			  System.out.println("Nombre de lignes insérées = " + nb);
			  ps.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		 

	}
	
	
	
	public void reserverBillets (String idUtilisateur, int idFestival,int jour, int nbPlaceSansCateg,int nbPlaceCateg1,int nbPlaceCateg2) {
		try {
			this.creerPanierRes(idUtilisateur);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
	
	
	
	public ArrayList<PaquetsBillets> billetFestival(int idFestival){
		System.out.println("banks to banks");
		ArrayList<PaquetsBillets>  paquets = new ArrayList<PaquetsBillets> ();
		paquets = this.billetFestivalSql(idFestival);
		return paquets;
		
	
	}
}
