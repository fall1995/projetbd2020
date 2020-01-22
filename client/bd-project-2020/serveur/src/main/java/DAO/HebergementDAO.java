package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ConnexionBase.SQLAble;
import DAOInterfaces.HebergementInterface;
import mesClasses.Hebergement;

public class HebergementDAO extends SQLAble implements HebergementInterface {
	static Connection conn;

	@Override
	public void add(Hebergement h) throws SQLException {

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
			ps.setString(9, h.getCategorie());
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
	}

	@Override
	public void update(Hebergement h) {
		// TODO Auto-generated method stub

	}

	@Override
	public void getHebergementsProches(int iDFestival, int nbPlaces, Date dateDebut, Date dateFin, String typeHebergement) {
		try {
			connectToDatabase();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	@Override
	public void getOneHebergement(Hebergement h) {
		// TODO Auto-generated method stub

	}

}
