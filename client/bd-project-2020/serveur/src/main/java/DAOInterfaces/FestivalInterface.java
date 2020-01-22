package DAOInterfaces;

import java.sql.Date;
import java.util.ArrayList;

public interface FestivalInterface {
	public abstract void addFestival();
	public abstract void updateFestival(int nbplace, int idFestivale);
	public abstract ArrayList getFestival(String domaine, Date dateDebut, Date dateFin, String Ville);
	public abstract void deleteFestival(int idFestivale);

}
