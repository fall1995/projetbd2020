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

    public ArrayList<ArrayList<Hebergement>> getHebergementsProches(String iDFestivalString, String nomDepartement) {
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
                    int iDFestival = Integer.parseInt(iDFestivalString);
                    //ArrayList<Hebergement> listeH = h.getHebergementsProchesPartiel(iDFestival, nomDepartement);
                    hebergementsProches.add(h.getHebergementsProchesPartiel(iDFestival, nomDepartement));
                  
                    System.out.println(hebergementsProches.get(0));
                  /*  hebergementsProches.add(r.getHebergementsProchesPartiel(iDFestival, nomDepartement));
                    hebergementsProches.add(c.getHebergementsProchesPartiel(iDFestival, nomDepartement));
                    hebergementsProches.add(v.getHebergementsProchesPartiel(iDFestival, nomDepartement));*/
                
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

}
