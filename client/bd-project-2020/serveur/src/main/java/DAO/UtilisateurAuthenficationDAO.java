package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ConnexionBase.SQLAble;
import DAOInterfaces.UtilisateurAuthenticationInterface;

public class UtilisateurAuthenficationDAO extends SQLAble implements UtilisateurAuthenticationInterface{
	static Connection conn;
	

	@Override
	public boolean exist() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addClient(String idclient, String nom, String prenom) throws SQLException {
		
		// connection à la base
				try {
					connectToDatabase();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {

					PreparedStatement ps = conn.prepareStatement(
							"INSERT INTO LesUtilisateurs (idUtilisateur   ,nom ,prenom) VALUES (idclient,nom,prenom)");
				} catch (SQLException se) {
					// log the exception
					throw se;
				}
	}
		
	}
  

