package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import connexionBase.SQLAble;
import DAOInterfaces.UtilisateurAuthenticationInterface;
import mesClassesMetier.LesUtilisateurs;
import oracle.jdbc.OracleConnection.CommitOption;

public class UtilisateurAuthenficationDAO extends SQLAble implements UtilisateurAuthenticationInterface {

	public boolean exist(String idclient) throws SQLException  {
		try {
			connectToDatabase();
		} catch (Exception e) {
			// TODO: handle exception
		}
		 int res=0;
		Statement ps = conn.createStatement();
		idclient="'"+idclient+"'";
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
			idclient ="'"+idclient+"'";
			nom ="'"+nom+"'";
			prenom ="'"+prenom+"'";
			//// avoiiiirrrr
			
			System.out.println("id Client = "+ idclient);
			System.out.println("prenom = "+ prenom);
			System.out.println("nom = "+ nom);
		//	PreparedStatement ps = conn.prepareStatement("INSERT INTO LesUtilisateurs (idUtilisateur,nom ,prenom) VALUES (?,?,?)");
		//	ps.setString(1, idclient);
		//	ps.setString(2, nom);
			
			//ps.setString(3, prenom);
			Statement ps1 = conn.createStatement();
			Statement ps = conn.createStatement();
			
			int nb8 = ps1.executeUpdate("INSERT INTO LesUtilisateurs ( idUtilisateur,nom,prenom) VALUES (" + idclient +","+nom+","+prenom+ ")");
			System.out.println("insert utilis =="+ nb8);
			int nb7 = ps.executeUpdate("INSERT INTO LesClients ( idUtilisateur) VALUES (" + idclient + ")");
			System.out.println("insert clients =="+ nb7);
			
			conn.commit();
			ps.close();
			ps1.close();

			
		} catch (SQLException se) {
			// log the exception
			throw se;
		}
	}

	@Override
	public LesUtilisateurs getClient(String idClient) throws SQLException {
		Statement ps = conn.createStatement();
		String query = "SELECT * FROM LesUtilisateurs where idUtilisateur=" + idClient + "";
		ResultSet resultats = ps.executeQuery(query);
		String idcli = null;
		String nomutili;
		String nom = null;
		String prenom = null;
		String mail;
		String tel;
		String adresse;
		while (resultats.next()) {
			idcli = resultats.getString(1);
			nomutili = resultats.getString(2);
			nom = resultats.getString(3);
			prenom = resultats.getString(4);
			mail = resultats.getString(5);
			tel = resultats.getString(6);
			adresse = resultats.getString(7);
		}
		resultats.close();
		LesUtilisateurs ut = new LesUtilisateurs(idcli, nom, prenom);
		return ut;
	}

}
