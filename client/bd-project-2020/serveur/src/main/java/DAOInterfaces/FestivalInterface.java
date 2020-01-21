package DAOInterfaces;

import java.sql.Date;

public interface FestivalInterface {
	public abstract void addFestival();
	public abstract void updateFestival(int nbplace, int idFestivale);
	public abstract void deleteFestival(int iDFestival, String domaine, Date dateDebut, Date dateFin, String Ville);
	

}
