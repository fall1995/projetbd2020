package DAOInterfaces;

import java.sql.Date;
import java.util.ArrayList;

import mesClassesMetier.Festival;

public interface FestivalInterface {
	public abstract void addFestival();
	public abstract void updateFestival(int nbplace, int idFestivale);

	public abstract ArrayList<Festival>  getFestival();

	public abstract void deleteFestival(int idFestivale);

}
