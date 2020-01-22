package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ConnexionBase.SQLAble;
import DAOInterfaces.UtilisateurAuthenticationInterface;

public class UtilisateurAuthenficationDAO extends SQLAble implements UtilisateurAuthenticationInterface{
	
	

	@Override
	public boolean exist(String idclient) throws SQLException  {
		 int res=0;
		Statement ps = conn.createStatement();
		 String query = "SELECT COUNT(*) FROM LesUtilisateurs where idUtilisateur="+idclient+"";
		 ResultSet resultats = ps.executeQuery(query);
		
		    while (resultats.next()) {
		    	res = resultats.getInt(1);
		    }
		    resultats.close();
		    if (res > 0) {
				return true;
			}
		    else {
		    	return false;
			}
		
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
							"INSERT INTO LesUtilisateurs (idUtilisateur,nom ,prenom) VALUES (?,?,?)");
					ps.setString(1, idclient);
					ps.setString(2, nom);
					ps.setString(3, prenom);
					ps.executeQuery();
				} catch (SQLException se) {
					// log the exception
					throw se;
				}
	}
		
	}
  

