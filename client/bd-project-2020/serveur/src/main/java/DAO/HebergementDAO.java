package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connexionBase.SQLAble;
import DAOInterfaces.HebergementInterface;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import mesClassesMetier.Festival;
import mesClassesMetier.Hebergement;

public class HebergementDAO extends SQLAble implements HebergementInterface {

    static Connection conn;

    @Override
  
    public void addH(Hebergement h) throws SQLException {
        /*
        try {
            connectToDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO Hebergement (idHebergement   ,dateDePublication ,nomCommercial,dateDeClassement ,nomCategorie ,classement ,adresse ,categorie ,codePostal ,commune  ,numTel ,courreil ,siteNet ,coordonnees,nomEPC,nomDep  ,nomRegion,Description ,dateAjout,idUtilisateur  ) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            ps.setInt(1, h.getIdHebergement());
            ps.setDate(2, h.getDateDePublication());
            ps.setString(3, h.getNomCommercial());
            ps.setDate(4, h.getDateDeClassement());
            ps.setDate(5, h.getDateDePublication());
            ps.setString(6, h.getNomCategorie());
            ps.setString(7, h.getClassement());
            ps.setString(8, h.getAdresse());
            ps.setInt(10, h.getCodePostal());
            ps.setString(11, h.getCommune());
            ps.setInt(12, h.getNumTel());
            ps.setString(13, h.getCourreil());
            ps.setString(14, h.getSiteNet());
            ps.setDouble(15, h.getCoordonnees());
            ps.setDouble(16, h.getNomEPCI());
            ps.setString(17, h.getNomDep());
            ps.setString(18, h.getNomRegion());
            ps.setString(19, h.getDescription());
            ps.setDate(20, h.getDateAjout()); // toDate
            ps.setInt(21, h.getIdUtilisateur());

            ps.execute();
            ps.close();
        } catch (SQLException se) {
            // log the exception
            throw se;

        }
*/
    }

    public ArrayList<Hebergement> getHebergementsProches(String iDFestival) {
         //To change body of generated methods, choose Tools | Templates.
         try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         String s = "null";
	ArrayList<Hebergement>  hebergementsProches = new ArrayList<Hebergement> ();
        
			
                    HotelDAO h =  new HotelDAO();
                    ResidenceDAO r =  new ResidenceDAO();
                    CampingDAO c =  new CampingDAO();
                    VillagesVacancesDAO v =  new VillagesVacancesDAO();
                    //int iDFestivalInt = Integer.parseInt(iDFestival);
                    
                    ArrayList<Hebergement> listeH = h.getHebergementsProchesPartiel(iDFestival);
                    for(int i =0; i <listeH.size(); i++ ){
                        hebergementsProches.add(listeH.get(i));
                    }
                    
                    ArrayList<Hebergement> listeR = r.getHebergementsProchesPartiel(iDFestival);
                    for(int i =0; i <listeR.size(); i++ ){
                        hebergementsProches.add(listeR.get(i));
                    }
                    
                      ArrayList<Hebergement> listeC = c.getHebergementsProchesPartiel(iDFestival);
                    for(int i =0; i <listeC.size(); i++ ){
                        hebergementsProches.add(listeC.get(i));
                    }
                    
                      ArrayList<Hebergement> listeV = v.getHebergementsProchesPartiel(iDFestival);
                    for(int i =0; i <listeV.size(); i++ ){
                        hebergementsProches.add(listeV.get(i));
                    }
                    
                
                    return hebergementsProches;       
        
    }

    @Override
    public void update(Hebergement h) {
        // TODO Auto-generated method stub

    }

    @Override
    public void getOneHebergement(Hebergement h) {

    }

    
    
    
    
    
    public ArrayList<Hebergement> filtreTypeHebergementSQL(String idFestival, String typeHebergement) {

		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                //int idFestivalInt = Integer.parseInt(idFestival);
		String typeHebergementUpperCase = typeHebergement.toUpperCase();
		ArrayList<Hebergement> hebergements = new ArrayList<Hebergement>();// Liste pour recuperer mon resultat
        switch (typeHebergementUpperCase) {
            case "HOTEL":
                HotelDAO h = new HotelDAO();
                hebergements = h.getHebergementsProchesPartiel(idFestival);
                break;
            case "RESIDENCE":
                ResidenceDAO r = new ResidenceDAO();
                hebergements = r.getHebergementsProchesPartiel(idFestival);
                break;
            case "CAMPING":
                CampingDAO c = new CampingDAO();
                hebergements = c.getHebergementsProchesPartiel(idFestival);
                break;
            default:
                VillagesVacancesDAO v = new VillagesVacancesDAO();
                hebergements = v.getHebergementsProchesPartiel(idFestival);
                break;
        }
                return hebergements;
                  

	}
	
    
    
    
    public ArrayList<Hebergement> filtreClassementSQL(ArrayList<Hebergement> l, String classement) {

		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
               
		
		char charC = classement.charAt(0);
                System.out.println(charC);
		ArrayList<Hebergement> hebergementsApreC = new ArrayList<Hebergement>();// Liste pour recuperer mon resultat
                
                
                for(int i = 0; i < l.size(); i++){
                    char c = l.get(i).getClassement().charAt(0);
                  
                    if(c == charC) {
                        //System.out.println("charC      c "+charC+"     "+c);
                        //System.out.println("hebergementsHotelClassement---->"+hebergementsHotelClassement.get(i).getClassement()  );
                        hebergementsApreC.add(l.get(i));
                    }
                }
                
                for(int j = 0; j<hebergementsApreC.size();j++ ){
                    System.out.println("Apres supression hebergementsClassement---->"+hebergementsApreC.get(j).getIdHebergement());
                }
                
                
                
        return hebergementsApreC;
	}
    
    
    
    
    
    
    
    
    public ArrayList<Hebergement> filtreTypeHebergementClassementSql(String iDFestival, String typeHebergement, String classement) {
        System.out.println("ici ---------------------->filtreTypeHebergementClassementSql");
		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
                ArrayList<Hebergement> liste = new ArrayList<Hebergement>();
                liste = this.filtreTypeHebergementSQL(iDFestival, typeHebergement);
                
                ArrayList<Hebergement> listeAprC = new ArrayList<Hebergement>();
                
                listeAprC = this.filtreClassementSQL(liste, classement);
                
                

                
		return listeAprC;
		// return null;
        
	}

	
    
    /*
    public ArrayList<Hebergement> filtreClassementTypeHebergementSql(String iDFestival, String nomDepartement, String typeHebergement, String classement) {

		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
               ArrayList<ArrayList<Hebergement>> hebergementclassement = new ArrayList<ArrayList<Hebergement>>();
                hebergementclassement = this.filtreClassementSQL(iDFestival, nomDepartement, classement);
                
                ArrayList<Hebergement> hebergementclassementType = new ArrayList<Hebergement>();
                String typeHebergementUpperCase = typeHebergement.toUpperCase();
                switch (typeHebergementUpperCase) {
            case "HOTEL":
                hebergementclassementType = hebergementclassement.get(0);
                break;
            case "RESIDENCE":
               hebergementclassementType = hebergementclassement.get(1);
                break;
            case "CAMPING":
                hebergementclassementType = hebergementclassement.get(2);
                break;
            default:
                hebergementclassementType = hebergementclassement.get(3);
                break;
        }
                
		return hebergementclassementType;
		// return null;
	}
    
    
    */
    
    
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<Hebergement> affiner(String iDFestival, String typeHebergement, String classement) {
		System.out.println("iDFestival = " + iDFestival + "\n");
		System.out.println("typeHebergement = " + typeHebergement + "\n");
		System.out.println("classement = " + classement + "\n");
                ArrayList<Hebergement> hebergements = new ArrayList<Hebergement>();
		
                
                if (aucunFiltre(iDFestival, typeHebergement, classement)) {
		
                   
			hebergements = this.filtreTypeHebergementSQL(iDFestival, typeHebergement);
                        
			return hebergements;
		}
		// System.out.println("apres aucun filtre");
		// filtre uniquement sur ville

		if (TypeHeberegementClassementFiltre(iDFestival, typeHebergement, classement)) {
                    System.out.println("TypeHeberegementClassementFiltre");
                    
                    ArrayList<Hebergement> h = new ArrayList<Hebergement>();
			h = this.filtreTypeHebergementClassementSql(iDFestival, typeHebergement, classement);
			
                        return h;
		}
		// filtre uniquement sur domaine
		
		// filtre uniquement sur date debut et fin
		
                
                return null;
	}
    
        
    
    boolean aucunFiltre(String idFestival, String typeHebergement, String classement) {
	return ((idFestival != null && !idFestival.isEmpty()) && (typeHebergement != null || !typeHebergement.isEmpty()) && (classement == null || classement.isEmpty()));

	}


	/*boolean classementFiltre(String idFestival, String typeHebergement, String classement) {
		System.out.println("classementFiltreeeeeeeeeeeeeeeeee");
		return ((idFestival != null && !idFestival.isEmpty()) && (idFestival != null || !idFestival.isEmpty())
				&& (typeHebergement == null || typeHebergement.isEmpty()) && (classement != null || !classement.isEmpty()));
	}*/

	
        boolean TypeHeberegementClassementFiltre(String idFestival, String typeHebergement, String classement) {
		 System.out.println("TypeHeberegementClassementFiltre");
		return ((idFestival != null && !idFestival.isEmpty()) && (idFestival != null || !idFestival.isEmpty())
				&& (typeHebergement != null || !typeHebergement.isEmpty()) && (classement != null || !classement.isEmpty()));
	}

    @Override
    public ArrayList<Hebergement> getHebergementsProchesPartiel(String iDFestival) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
