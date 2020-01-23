package DAOInterfaces;

import java.sql.SQLException;

import mesClasses.LesUtilisateurs;

public interface UtilisateurAuthenticationInterface {
	public abstract void addClient(String idclient, String nom, String prenom) throws SQLException;
	public abstract boolean exist(String idclient) throws SQLException;
	public abstract LesUtilisateurs getClient(String idClient) throws SQLException;
	

}
