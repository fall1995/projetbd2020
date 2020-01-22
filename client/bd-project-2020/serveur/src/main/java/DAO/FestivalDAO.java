package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ConnexionBase.SQLAble;
import DAOInterfaces.FestivalInterface;
import mesClasses.Festival;

public class FestivalDAO extends SQLAble implements FestivalInterface {
    

	
	@Override
	public void addFestival() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void updateFestival(int nbplace, int idFestivale) {
		// TODO Auto-generated method stub
		
	}




	
	@Override //modifier string de date
	public ArrayList<Festival>  getFestival(String domaineF, String dateDebutF, String dateFinF, String Ville) {
		
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
			    //ps = conn.prepareStatement("SELECT * FROM LesFestivals");  
			    String query = "SELECT * FROM LesFestivals where idFestival= 2";
			   // ps.setInt(1, 0);
			    System.out.println(" apres ps \n");
			    ResultSet resultats = ps.executeQuery(query);
			  
			    // Parcours des resulats (objet ResulSet) retournés par executeQuery()
			 
			    boolean encore = resultats.next();
			    while (encore) {
			    	String idFestival= resultats.getString(1);
			    	System.out.println("id festival = "+idFestival + "\n");
			        String nomFestival = resultats.getString(2);
			        System.out.println("nomFestival = "+nomFestival + "\n");
			        String domaine = resultats.getString(3);
			        String complementDomaine = resultats.getString(4);
			        String region =  resultats.getString(5);
			        int departement = resultats.getInt(6);
			        String periodicite=resultats.getString(7);
			        String moiHabDebut= resultats.getString(8);
			        String siteWeb=resultats.getString(9);
			        String commune = resultats.getString(10);
			        Date dateDebut = resultats.getDate(11);
			        Date dateFin = resultats.getDate(12);
			        Date dateCreation = resultats.getDate(13);
			        int codepost = resultats.getInt(14);
			        String codeINSEE =  resultats.getString(15);
			        float coord1 = resultats.getFloat(16);
			        float coord2 = resultats.getFloat(17);
			        String nomDepartement = resultats.getString(18);
			        int nbPlaceLouees = resultats.getInt(19);
			        Date dateAjout = resultats.getDate(20);
			        int idUtilisateur = resultats.getInt(21);
			        
			        // j'instancie la classe festival et je l'ajoute a ma liste de festivale
			        fest = new Festival(idFestival, nomFestival, domaine, complementDomaine, region, departement, periodicite, moiHabDebut, siteWeb, commune, dateDebut, dateFin, dateCreation, codepost, codeINSEE, coord1,coord2, nomDepartement, nbPlaceLouees, dateAjout, idUtilisateur);
			        festivals.add(fest);
			    }
			    resultats.close();
			    
			    
			   // if (domaineF.equalsIgnoreCase(s) && dateDebutF==null && dateFinF==null && Ville.equalsIgnoreCase(s) ) {
			    	//return festivals;
			   // }
			    // cas ou on sasit seulement le domaine
			/*    if ((!(domaineF.equalsIgnoreCase(s)) && dateDebutF==null && dateFinF==null && Ville.equalsIgnoreCase(s))) {
			    	ArrayList<Festival>  resultat = new ArrayList<Festival>();
			    	for (int i = 0; i < festivals.size(); i++) {
		    			if(festivals.get(i).getDomaine().equalsIgnoreCase(domaineF)){
		    				resultat.add(festivals.get(i));
		    			}
		    		}
			    	return resultat;
				}
			    // cas ou il saisit le domaine et la date de debut et fin
			    // on tient pas compte de la date de fin car on va pas lui donner pas la possiblité d'envoyer seulemnt la dat debut
			    if ((domaineF.equalsIgnoreCase(s) && dateDebutF !=null  && Ville.equalsIgnoreCase(s))) {
			    	ArrayList<Festival> resultat = new ArrayList<Festival>();
			    	for (int i = 0; i < festivals.size(); i++) {
		    			if(festivals.get(i).getDomaine().equalsIgnoreCase(domaineF) && (festivals.get(i).getDateDebut().before(dateDebutF) || festivals.get(i).getDateDebut().after(dateDebutF)) && Ville.equalsIgnoreCase(s)){
		    				resultat.add(festivals.get(i));
		    			}
		    		}
			    	return resultat;
				}
			    // cas ou il saisit les 3 en mm temps
			    if (domaineF.equalsIgnoreCase(s) && dateDebutF !=null  && Ville.equalsIgnoreCase(s)) {
			    	ArrayList<Festival>  resultat = new ArrayList<Festival>();
			    	for (int i = 0; i < festivals.size(); i++) {
		    			if(!festivals.get(i).getDomaine().equalsIgnoreCase(domaineF) && (!festivals.get(i).getDateDebut().before(dateDebutF) || !festivals.get(i).getDateDebut().after(dateDebutF)) && !Ville.equalsIgnoreCase(s) ){
		    				resultat.add(festivals.get(i));
		    			}
		    		}
			    	return resultat;
				}*/
			    
			    // reste 2 ou 3 cas a faire
			    
			    
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			System.out.println("\n"+festivals.size());
			return festivals;
		//return null;
	}




	@Override
	public void deleteFestival(int idFestivale) {
		// TODO Auto-generated method stub
		
	}

}
