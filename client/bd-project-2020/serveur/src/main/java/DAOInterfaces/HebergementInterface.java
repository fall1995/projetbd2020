package DAOInterfaces;

import java.sql.Date;
import java.sql.SQLException;

import MesClasses.Hebergement;

public interface HebergementInterface {
	public abstract void add(Hebergement h) throws SQLException ;
	public abstract void update(Hebergement h);
	public abstract void getHebergementsProches(int iDFestival, int nbPlaces, Date dateDebut, Date dateFin, String typeHebergement);
	public abstract void getOneHebergement(Hebergement h);

}
