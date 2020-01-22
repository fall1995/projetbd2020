package DAOInterfaces;

import java.sql.SQLException;

public interface UtilisateurAuthenticationInterface {
	public abstract void addClient(String idclient, String nom, String prenom) throws SQLException;
	public abstract boolean exist(String idclient) throws SQLException;
	

}
