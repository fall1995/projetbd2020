/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.HebergementDAO.conn;
import connexionBase.SQLAble;
import DAOInterfaces.HebergementInterface;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import mesClassesMetier.Hebergement;
import mesClassesMetier.Hotel;

/**
 *
 * @author bouaziz
 */
public class HotelDAO extends SQLAble implements HebergementInterface{

     
    @Override
    public void add(Hebergement h) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Hebergement h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    @Override
    public void getOneHebergement(Hebergement h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

    @Override
    public ArrayList<Hebergement> getHebergementsProchesPartiel(int iDFestival, String nomDepartement) {
         try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         nomDepartement = "'"+nomDepartement+"'";
      
	ArrayList<Hebergement> hebergementsProches = new ArrayList<Hebergement>();
        try {
            
            

            System.out.println("-------->"+nomDepartement);
            System.out.println(iDFestival);
			Hotel hebergementProche;
			// PreparedStatement ps;
			Statement ps = conn.createStatement();   
	
			int idHebergement;
			Date dateDePublication;
			String nomCommercial;
			Date dateDeClassement;
			String nomCategorie;
			String classement;
			String adresse;
			String codePostal;
			String commune;
			String numTel;
                        
			String courriel;
			String siteNet;
			String coordonnee1;
			String coordonnee2;
			String nomDep;
			String nomRegion;
			String Description;
                        Date dateAjout;
			int idUtilisateur;
                        int capaciteAcc;
                        int nbChambres;
                        System.out.println("-+++++++++>"+nomDepartement);
                        String query = "SELECT * FROM LesHebergements natural join LesHotels where nomRegion="+nomDepartement+"";
                        //String query = "SELECT * FROM LesHebergements natural join LesHotels where nomRegion LIKE '%"+nomDepartement+"%'";
			ResultSet resultats = ps.executeQuery(query);
			while (resultats.next()) {
				idHebergement = resultats.getInt(1);
				// System.out.println("id festival = "+idFestival + "\n");
				dateDePublication = resultats.getDate(2);
				// System.out.println("nomFestival = "+nomFestival + "\n");
				nomCommercial = resultats.getString(3);
				// System.out.println("domaine = "+domaine + "\n");
				dateDeClassement = resultats.getDate(4);
				// System.out.println("complement dom = "+complementDomaine + "\n");
				nomCategorie = resultats.getString(5);
				classement = resultats.getString(6);
				adresse = resultats.getString(7);
				codePostal = resultats.getString(8);
				// System.out.println("moiHabDebut = "+moiHabDebut + "\n");
				commune = resultats.getString(9);
				// System.out.println("siteWeb = "+siteWeb + "\n");
				numTel = resultats.getString(10);
				// System.out.println("commune = "+commune + "\n");
				courriel = resultats.getString(11);
				// System.out.println("dateDebut = "+dateDebut + "\n");
				siteNet = resultats.getString(12);
				// System.out.println("dateFin = "+dateFin + "\n");
				coordonnee1 = resultats.getString(13);
                                float coord1 = Float.parseFloat(coordonnee1);
				// System.out.println("datecreation = "+dateCreation + "\n");
				coordonnee2 = resultats.getString(14);
                                float coord2 = Float.parseFloat(coordonnee2);
				// System.out.println("codeP = "+codepost + "\n");
				nomDep = resultats.getString(15);
				// System.out.println("codeInsee = "+codeINSEE + "\n");
				nomRegion = resultats.getString(16);
				// System.out.println("coord1 = "+coord1 + "\n");
				Description = resultats.getString(17);
				// System.out.println("coord2 = "+coord2 + "\n");
				nomDepartement = resultats.getString(18);
				// System.out.println("nomDepartement = "+nomDepartement + "\n");
				dateAjout = resultats.getDate(19);
				idUtilisateur = resultats.getInt(20);
                                capaciteAcc = resultats.getInt(21);
                                nbChambres = resultats.getInt(22);
				// System.out.println("id utilisateur "+idUtilisateur);

				// j'instancie la classe festival et je l'ajoute a ma liste de festivale
				hebergementProche = new Hotel(idHebergement, dateDePublication, nomCommercial, dateDeClassement, nomCategorie, classement,
						adresse, codePostal, commune, numTel, courriel, siteNet, coord1, coord2,
						nomDep, nomRegion, Description, dateAjout, idUtilisateur, capaciteAcc, nbChambres);
				// System.out.println("Element de ma liste "+ fest.getIdUtilisateur());
				hebergementsProches.add(hebergementProche);
                                System.out.println("izannnnn"+hebergementProche.getAdresse());
			}
			resultats.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n" + hebergementsProches.size());
		return hebergementsProches;
		// return null;
         
         
        
    }

   
    
}
