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

    public ArrayList<ArrayList<Hebergement>> getHebergementsProches(String iDFestival, String nomDepartement) {
         //To change body of generated methods, choose Tools | Templates.
         try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         String s = "null";
	ArrayList<ArrayList<Hebergement>>  hebergementsProches = new ArrayList<ArrayList<Hebergement>> ();
        
			
                    HotelDAO h =  new HotelDAO();
                    ResidenceDAO r =  new ResidenceDAO();
                    CampingDAO c =  new CampingDAO();
                    VillagesVacancesDAO v =  new VillagesVacancesDAO();
                    int iDFestivalInt = Integer.parseInt(iDFestival);
                    
                    //ArrayList<Hebergement> listeH = h.getHebergementsProchesPartiel(iDFestival, nomDepartement);
                    hebergementsProches.add(h.getHebergementsProchesPartiel(iDFestivalInt, nomDepartement));
                  
                    
                   hebergementsProches.add(r.getHebergementsProchesPartiel(iDFestivalInt, nomDepartement));
                    hebergementsProches.add(c.getHebergementsProchesPartiel(iDFestivalInt, nomDepartement));
                    hebergementsProches.add(v.getHebergementsProchesPartiel(iDFestivalInt, nomDepartement));
                
                    return hebergementsProches;       
        
    }

    @Override
    public void update(Hebergement h) {
        // TODO Auto-generated method stub

    }

    @Override
    public void getOneHebergement(Hebergement h) {

    }

    @Override
    public ArrayList<Hebergement> getHebergementsProchesPartiel(int iDFestival, String nomDepartement) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    public ArrayList<Hebergement> filtreTypeHebergementSQL(String idFestival,String nomDepartement , String typeHebergement) {

		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                int idFestivalInt = Integer.parseInt(idFestival);
		String typeHebergementUpperCase = typeHebergement.toUpperCase();
		ArrayList<Hebergement> hebergements = new ArrayList<Hebergement>();// Liste pour recuperer mon resultat
        switch (typeHebergementUpperCase) {
            case "HOTEL":
                HotelDAO h = new HotelDAO();
                hebergements = h.getHebergementsProchesPartiel(idFestivalInt, nomDepartement);
                break;
            case "RESIDENCE":
                ResidenceDAO r = new ResidenceDAO();
                hebergements = r.getHebergementsProchesPartiel(idFestivalInt, nomDepartement);
                break;
            case "CAMPING":
                CampingDAO c = new CampingDAO();
                hebergements = c.getHebergementsProchesPartiel(idFestivalInt, nomDepartement);
                break;
            default:
                VillagesVacancesDAO v = new VillagesVacancesDAO();
                hebergements = v.getHebergementsProchesPartiel(idFestivalInt, typeHebergement);
                break;
        }
                return hebergements;
                  

	}
	
    
    
    
    public ArrayList<ArrayList<Hebergement>> filtreClassementSQL(String idFestival,String nomDepartement, String classement) {

		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
               
		
		char charC = classement.charAt(0);
                System.out.println(charC);
		ArrayList<ArrayList<Hebergement>> hebergements = new ArrayList<ArrayList<Hebergement>>();// Liste pour recuperer mon resultat
                hebergements = this.getHebergementsProches(idFestival, nomDepartement);
                 
                ArrayList<Hebergement> hebergementsHotelClassement = new ArrayList<Hebergement>();
                hebergementsHotelClassement = hebergements.get(0);
                ArrayList<Hebergement> hebergementsHotelClassementApresSuppression = new ArrayList<Hebergement>();
                for(int i = 0; i < hebergementsHotelClassement.size(); i++){
                    char c = hebergementsHotelClassement.get(i).getClassement().charAt(0);
                  
                    if(c == charC) {
                        //System.out.println("charC      c "+charC+"     "+c);
                        //System.out.println("hebergementsHotelClassement---->"+hebergementsHotelClassement.get(i).getClassement()  );
                        hebergementsHotelClassementApresSuppression.add(hebergementsHotelClassement.get(i));
                    }
                }
                
                for(int j = 0; j<hebergementsHotelClassementApresSuppression.size();j++ ){
                    System.out.println("Apres supression hebergementsHotelClassement---->"+hebergementsHotelClassementApresSuppression.get(j).getClassement()  );
                }
                
                ArrayList<Hebergement> hebergementsResidenceClassement = new ArrayList<Hebergement>();
                hebergementsResidenceClassement = hebergements.get(1);
                ArrayList<Hebergement> hebergementsRClassementApresSuppression = new ArrayList<Hebergement>();
                for(int i = 0; i < hebergementsResidenceClassement.size(); i++){
                    char c = hebergementsResidenceClassement.get(i).getClassement().charAt(0);
                    System.out.println("charC      c "+charC+"  ++++++++   "+c);
                    
                    if(c == charC) {
                        //System.out.println("charC      c "+charC+"     "+c);
                        //System.out.println("hebergementsHotelClassement---->"+hebergementsHotelClassement.get(i).getClassement()  );
                        hebergementsRClassementApresSuppression.add(hebergementsResidenceClassement.get(i));
                    }
                }
                
                
                 for(int j = 0; j<hebergementsRClassementApresSuppression.size();j++ ){
                    System.out.println("Apres supression hebergementsHotelClassement---->"+hebergementsRClassementApresSuppression.get(j).getClassement()  );
                }
                
                
                ArrayList<Hebergement> hebergementsCampiingClassement = new ArrayList<Hebergement>();
                hebergementsCampiingClassement = hebergements.get(2);
                
                ArrayList<Hebergement> hebergementsCClassementApresSuppression = new ArrayList<Hebergement>();
                for(int i = 0; i < hebergementsCampiingClassement.size(); i++){
                    char c = hebergementsCampiingClassement.get(i).getClassement().charAt(0);
                    System.out.println("charC      c "+charC+c);
                    if(c == charC){
                         //System.out.println("hebergementsCampingClassement---->"+hebergementsCampiingClassement.get(i).getClassement()  );
                       // hebergementsCampiingClassement.remove(i);
                       hebergementsCClassementApresSuppression.add(hebergementsCampiingClassement.get(i));
                    }
                }
                
                 for(int j = 0; j<hebergementsCClassementApresSuppression.size();j++ ){
                    System.out.println("Apres supression hebergementsHotelClassement---->"+hebergementsCClassementApresSuppression.get(j).getClassement()  );
                }
                
                ArrayList<Hebergement> hebergementsVillageClassement = new ArrayList<Hebergement>();
                hebergementsVillageClassement = hebergements.get(3);
                ArrayList<Hebergement> hebergementsVVClassementApresSuppression = new ArrayList<Hebergement>();
                for(int i = 0; i < hebergementsVillageClassement.size(); i++){
                   char c = hebergementsVillageClassement.get(i).getClassement().charAt(0);
                   System.out.println("charC      c "+charC+c);
                    if(c == charC){
                        System.out.println("hebergementsVillageClassement---->"+hebergementsVillageClassement.get(i).getClassement()  );
                        hebergementsVillageClassement.remove(i);
                         hebergementsVVClassementApresSuppression.add(hebergementsVillageClassement.get(i));
                    }
                }
                
                
                 for(int j = 0; j<hebergementsVVClassementApresSuppression.size();j++ ){
                    System.out.println("Apres supression hebergementsHotelClassement---->"+hebergementsVVClassementApresSuppression.get(j).getClassement()  );
                }
                ArrayList<ArrayList<Hebergement>> hebergementsFiltrClassement = new ArrayList<ArrayList<Hebergement>>();// Liste pour recuperer mon resultat
                hebergementsFiltrClassement.add(hebergementsHotelClassementApresSuppression);
                hebergementsFiltrClassement.add(hebergementsRClassementApresSuppression);
                hebergementsFiltrClassement.add(hebergementsCClassementApresSuppression);
                hebergementsFiltrClassement.add(hebergementsVVClassementApresSuppression);
               
                
        return hebergementsFiltrClassement;
	}
    
    
    
    
    
    
    
    
    public ArrayList<Hebergement> filtreTypeHebergementClassementSql(String iDFestival, String nomDepartement, String typeHebergement, String classement) {
        System.out.println("ici ---------------------->filtreTypeHebergementClassementSql");
		// connection à la base
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                ArrayList<Hebergement> hebergementClassement = new ArrayList<Hebergement>();
                hebergementClassement = this.filtreTypeHebergementSQL(iDFestival, nomDepartement,typeHebergement);
                
                for(int i = 0; i < hebergementClassement.size(); i++){
                    if(hebergementClassement.get(i).getClassement().equals(classement) == false){
                        hebergementClassement.remove(i);
                    }
                }
                
		return hebergementClassement;
		// return null;
        
	}

	
    
    
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public ArrayList<ArrayList<Hebergement>> affiner(String iDFestival, String nomDepartement, String typeHebergement, String classement) {
		System.out.println("iDFestival = " + iDFestival + "\n");
		System.out.println("nomDepartement = " + nomDepartement + "\n");
		System.out.println("typeHebergement = " + typeHebergement + "\n");
		System.out.println("classement = " + classement + "\n");
                ArrayList<ArrayList<Hebergement>> hebergements = new ArrayList<ArrayList<Hebergement>>();
		
                
                if (aucunFiltre(iDFestival, nomDepartement, typeHebergement, classement)) {
		
                    
                   
			hebergements = this.getHebergementsProches(iDFestival, nomDepartement);
                        
			return hebergements;
		}
		// System.out.println("apres aucun filtre");
		// filtre uniquement sur ville

		if (typeHebergementFiltre(iDFestival, nomDepartement, typeHebergement, classement)) {
                    
                    
                    ArrayList<Hebergement> h = new ArrayList<Hebergement>();
			h = this.filtreTypeHebergementSQL(iDFestival,nomDepartement, typeHebergement);
			hebergements.add(h);
                        return hebergements;
		}
		// filtre uniquement sur domaine
		if (classementFiltre(iDFestival, nomDepartement, typeHebergement, classement)) {
		System.out.println("-------typeHebergementFiltre----------------");
                    hebergements = this.filtreClassementSQL(iDFestival, nomDepartement, classement);
			return hebergements;
			
		}
		// filtre uniquement sur date debut et fin
		if (TypeHeberegementClassementFiltre(iDFestival, nomDepartement, typeHebergement, classement)) {
			System.out.println("ici----------Less Deux tzzzzzzz");	
			ArrayList<Hebergement> hTypeHClassement = new ArrayList<Hebergement>();
			hTypeHClassement = this.filtreClassementTypeHebergementSql(iDFestival, nomDepartement, typeHebergement, classement);
			hebergements.add(hTypeHClassement);
                        return hebergements;
		}
		
                
                return null;
	}
    
        
    
    boolean aucunFiltre(String idFestival, String nomDepartement, String typeHebergement, String classement) {
	return ((idFestival != null && !idFestival.isEmpty()) && (idFestival != null || !idFestival.isEmpty())
				&& (typeHebergement == null || typeHebergement.isEmpty()) && (classement == null || classement.isEmpty()));

	}

	boolean typeHebergementFiltre(String idFestival, String nomDepartement, String typeHebergement, String classement) {
		// System.out.println("dans boolean");
		return ((idFestival != null && !idFestival.isEmpty()) && (idFestival != null || !idFestival.isEmpty())
				&& (typeHebergement != null || !typeHebergement.isEmpty()) && (classement == null || classement.isEmpty()));
	}

	boolean classementFiltre(String idFestival, String nomDepartement, String typeHebergement, String classement) {
		System.out.println("classementFiltreeeeeeeeeeeeeeeeee");
		return ((idFestival != null && !idFestival.isEmpty()) && (idFestival != null || !idFestival.isEmpty())
				&& (typeHebergement == null || typeHebergement.isEmpty()) && (classement != null || !classement.isEmpty()));
	}

	
        boolean TypeHeberegementClassementFiltre(String idFestival, String nomDepartement, String typeHebergement, String classement) {
		System.out.println("prq iciciciciciicicicici");
		return ((idFestival != null && !idFestival.isEmpty()) && (idFestival != null || !idFestival.isEmpty())
				&& (typeHebergement != null || !typeHebergement.isEmpty()) && (classement != null || !classement.isEmpty()));
	}
    
    
    
    
    
    
    
    
    
    
    
    
    
    

}
