package DAOInterfaces;

import java.sql.SQLException;

import mesClasses.Hebergement;

public interface HebergementInterface {
	public abstract void add(Hebergement h) throws SQLException ;
	public abstract void update(Hebergement h);
	public abstract void getAllHebergements();
	public abstract void getOneHebergement(Hebergement h);

}
