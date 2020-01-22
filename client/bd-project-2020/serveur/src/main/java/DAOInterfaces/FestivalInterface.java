package DAOInterfaces;

import java.sql.Date;
import java.util.ArrayList;

import mesClasses.Festival;

public interface FestivalInterface {
	public abstract void addFestival();
	public abstract void updateFestival(int nbplace, int idFestivale);

	public abstract ArrayList<Festival>  getFestival(String domaine, String dateDebut, String dateFin, String Ville);

	public abstract void deleteFestival(int idFestivale);

}
