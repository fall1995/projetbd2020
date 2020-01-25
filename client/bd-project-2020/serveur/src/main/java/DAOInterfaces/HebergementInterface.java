package DAOInterfaces;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import mesClassesMetier.Festival;

import mesClassesMetier.Hebergement;

public interface HebergementInterface {
	public abstract void add(Hebergement h) throws SQLException ;
	public abstract void update(Hebergement h);
	//public abstract ArrayList<Hebergement>  getHebergementsProches(int iDFestival);
	public abstract void getOneHebergement(Hebergement h);
       // public abstract ArrayList<Hebergement> getHebergementsProches(int iDFestival, String dateDebutF, String dateFinF);
       public abstract ArrayList<Hebergement> getHebergementsProchesPartiel(int iDFestival, String nomDepartement);
}
