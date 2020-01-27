/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAOInterfaces.FestivalInterface;
import DAOInterfaces.HebergementInterface;
import DAOInterfaces.LogementInterface;
import connexionBase.SQLAble;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import mesClassesMetier.Festival;
import mesClassesMetier.Hebergement;
import mesClassesMetier.Hotel;
import mesClassesMetier.Logement;

/**
 *
 * @author bouaziz
 */
public class ChambreHotelDAO extends SQLAble implements LogementInterface{

    @Override
    public void addH(Hebergement h) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Hebergement h) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Logement> getLogement(int iDFestival, String dateDF, String dateFF, int idHebergement) {
        
        
        
        try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
               // String nomDepartementUpper = nomDepartement.toUpperCase();
		//nomDepartementUpper = "'" + nomDepartementUpper + "'";

		ArrayList<Logement> hebergementsProches = new ArrayList<Logement>();
		try {

			
			System.out.println(iDFestival);
			Hotel hebergementProche;
			// PreparedStatement ps;
			Statement ps = conn.createStatement();

			int numLogement;
			int tarifAdulte;
			int tarifEnfant ;
			int nbAdultes;
			int nbEnfants;
			char typeChambre;
			int idHebergementInt;
			
			String query = "SELECT * FROM LesHebergements natural join LesChambres natural join LesPeriodeDeDisponibilites where idHebergement=" + idHebergement+ "";
			// String query = "SELECT * FROM LesHebergements natural join LesHotels where
			// nomRegion LIKE '%"+nomDepartement+"%'";
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
			
				dateAjout = resultats.getDate(18);
				
				idUtilisateur = resultats.getInt(19);
				
				capaciteAcc = resultats.getInt(20);
				nbChambres = resultats.getInt(21);
				
				
				// j'instancie la classe festival et je l'ajoute a ma liste de festivale
				hebergementProche = new Hotel(idHebergement, dateDePublication, nomCommercial, dateDeClassement,
						nomCategorie, classement, adresse, codePostal, commune, numTel, courriel, siteNet, coord1,
						coord2, nomDep, nomRegion, Description, dateAjout, idUtilisateur, capaciteAcc, nbChambres);
				// System.out.println("Element de ma liste "+ fest.getIdUtilisateur());
		
				System.out.println("idHebergement Hotel ="+hebergementProche.getIdHebergement());
				hebergementsProches.add(hebergementProche);
				
			}
			resultats.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("\n" + hebergementsProches.size());
		return hebergementsProches;
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        return null;
        
    }

   
    
}
