package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
	public ArrayList<Festival>  getFestival() {
		
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
			    String query = "SELECT * FROM LesFestivals";
			   // ps.setInt(1, 0);
			    System.out.println(" apres ps \n");
			    ResultSet resultats = ps.executeQuery(query);
			  
			    // Parcours des resulats (objet ResulSet) retournés par executeQuery()
			    String idFestival;
		    	
		        String nomFestival ;
		       
		        String domaine;
		        String complementDomaine ;
		        String region ;
		        int departement ;
		        String periodicite;
		        String moiHabDebut;
		        String siteWeb;
		        String commune;
		        Date dateDebut ;
		        Date dateFin ;
		        Date dateCreation;
		        int codepost;
		        String codeINSEE ;
		        float coord1 ;
		        float coord2 ;
		        String nomDepartement;
		        int nbPlaceLouees ;
		        Date dateAjout;
		        int idUtilisateur;
			   
			    while (resultats.next()) {
			    	 idFestival= resultats.getString(1);
			    	System.out.println("id festival = "+idFestival + "\n");
			         nomFestival = resultats.getString(2);
			        System.out.println("nomFestival = "+nomFestival + "\n");
			         domaine = resultats.getString(3);
			         System.out.println("domaine = "+domaine + "\n");
			         complementDomaine = resultats.getString(4);
			         System.out.println("complement dom = "+complementDomaine + "\n");
			         region =  resultats.getString(5);
			         departement = resultats.getInt(6);
			         periodicite=resultats.getString(7);
			         moiHabDebut= resultats.getString(8);
			         System.out.println("moiHabDebut  = "+moiHabDebut + "\n");
			         siteWeb=resultats.getString(9);
			         System.out.println("siteWeb  = "+siteWeb + "\n");
			         commune = resultats.getString(10);
			         System.out.println("commune  = "+commune + "\n");
			         dateDebut = resultats.getDate(11);
			         System.out.println("dateDebut  = "+dateDebut + "\n");
			         dateFin = resultats.getDate(12);
			         System.out.println("dateFin  = "+dateFin + "\n");
			         dateCreation = resultats.getDate(13);
			         codepost = resultats.getInt(14);
			         codeINSEE =  resultats.getString(15);
			         coord1 = resultats.getFloat(16);
			         coord2 = resultats.getFloat(17);
			         nomDepartement = resultats.getString(18);
			         System.out.println("nomDepartement  = "+nomDepartement + "\n");
			         nbPlaceLouees = resultats.getInt(19);
			         dateAjout = resultats.getDate(20);
			         idUtilisateur = resultats.getInt(21);
			        System.out.println("id utilisateur "+idUtilisateur);
			        
			        // j'instancie la classe festival et je l'ajoute a ma liste de festivale
			        fest = new Festival(idFestival, nomFestival, domaine, complementDomaine, region, departement, periodicite, moiHabDebut, siteWeb, commune, dateDebut, dateFin, dateCreation, codepost, codeINSEE, coord1,coord2, nomDepartement, nbPlaceLouees, dateAjout, idUtilisateur);
			        System.out.println("Element de ma liste "+ fest.getIdUtilisateur());
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

	public ArrayList<Festival> affiner(ArrayList<Festival> festivals, String domaineF, String dateDebutF, String dateFinF, String Ville){
		
		//aucun filtre 
		 if ((domaineF == null || domaineF.isEmpty()) && (dateDebutF == null || dateDebutF.isEmpty()) && (dateFinF == null || dateFinF.isEmpty()) && (Ville == null || Ville.isEmpty()) ) {
			 return festivals;
    }
		//filtre uniquement sur domaine
		 if ((domaineF != null || !domaineF.isEmpty()) && (dateDebutF == null || dateDebutF.isEmpty()) && (dateFinF == null || dateFinF.isEmpty()) && (Ville == null || Ville.isEmpty()) ) {
			 ArrayList<Festival>  resultat = new ArrayList<Festival>();
		    	for (int i = 0; i < festivals.size(); i++) {
	    			if(festivals.get(i).getDomaine().equalsIgnoreCase(domaineF)){
	    				resultat.add(festivals.get(i));
	    			}
	    		}
		    	return resultat;
    }
		//filtre sur domaine et datedebut et date fin
		 if ((domaineF != null && !domaineF.isEmpty()) && (dateDebutF != null || !dateDebutF.isEmpty()) && (dateFinF != null || !dateFinF.isEmpty()) && (Ville == null || Ville.isEmpty()) ) {
			 ArrayList<Festival> resultat = new ArrayList<Festival>();
			 Date dateD=new SimpleDateFormat("dd/MM/yyyy").parse(dateDebutF); 
			 
			 
		    	for (int i = 0; i < festivals.size(); i++) {
	    			if(festivals.get(i).getDomaine().equalsIgnoreCase(domaineF) && (festivals.get(i).getDateDebut().before(dateD) || festivals.get(i).getDateDebut().after(dateDebutF)) && Ville.equalsIgnoreCase(s)){
	    				resultat.add(festivals.get(i)); 
	    			}
	    		}
		 
		return resultat;
		 }
		return festivals; 
		
	}


	@Override
	public void deleteFestival(int idFestivale) {
		// TODO Auto-generated method stub
		
	}

}
