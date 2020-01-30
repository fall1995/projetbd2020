package mesServelets;


/**
 *
 * @author bouaziz
 */
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.FestivalDAO;
import DAO.HebergementDAO;
import DAO.LogementDAO;
import DAO.PaquetsBilletsDAO;
import DAO.ReservationLogementDAO;
import Timer.TimerT;
import mesClassesMetier.Festival;
import mesClassesMetier.Hebergement;
import mesClassesMetier.Logement;

public class LogementServelet extends HttpServlet{
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println( "========================================================== LogementServelet [doGet] =========================================================" );
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        Enumeration<String> P = request.getParameterNames();
        HashMap<String, String> parametres = new HashMap();
       

        while (P.hasMoreElements()) {
            String p = P.nextElement();
            parametres.put(p, request.getParameter((String) p));
        }
       

        String iDFestival;
        String dateDF;
        String dateFF;
        String idHebergement;
        String typeHebergement;
        iDFestival = parametres.get("idFestival"); 
        dateDF = parametres.get("dateDF");
        dateFF = parametres.get("dateFF");
        idHebergement = parametres.get("idHebergement");
        typeHebergement = parametres.get("typeHebergement");
       
       /* SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date newDateDebut;
        java.util.Date newDateFin;
        try {
			newDateDebut = sdf1.parse(datedebut);
			newDateFin = sdf1.parse(datefin);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        
        
        /*
         * Dans cette partie faudra transformer datedebut et datefin en Date
         */
        ArrayList<Logement> resultat = new ArrayList<Logement> ();
        LogementDAO LDao = new LogementDAO();
        // la methode suivante ne va pas marcher a cause du type Date 
        // 

        // resultat = festDao.getFestival(domaine, datedebut, datefin, ville);
        // String json = new Gson().toJson(resultat);


        
        
System.out.println("avant \n");
        //resultat = festDao.getFestival();
        resultat = LDao.getLogement(iDFestival, dateDF, dateFF, idHebergement, typeHebergement);
        System.out.println("apres \n");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println( new Gson().toJson(resultat));
        System.out.println("tout est ok");


    }
	
	
	
	
	
	
	

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("================================================== LogementResa servlet [doPost] ==================================================");

        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
        response.addHeader("Access-Control-Max-Age", "1728000");

        // Extract userId from HTTP parameters
        String res = " ";
        Enumeration<String> P = request.getParameterNames();
        HashMap<String, String> parametres = new HashMap();
        // Instancier Utilisateur dans le DAO
        //Client client = new Client();

        while (P.hasMoreElements()) {
            String p = P.nextElement();
            parametres.put(p, request.getParameter((String) p));
        }
        // Call the database and return result
        boolean insert = false;
        String idUtilisateur = parametres.get("idUtilisateur");
        String numLogement = parametres.get("numLogement");
        String jour = parametres.get("jour");
        String nbPlaceAdulte = parametres.get("nbPlaceAdulte");
        String nbPlaceEnfant = parametres.get("nbPlaceEnfant");
        System.out.println("numLogement ====>"+numLogement);
        System.out.println("utilisagzghj ====>"+idUtilisateur);
        //System.out.println("nbpA ====>"+nbPlaceAdulte);
       // System.out.println("nbpEnf ====>"+nbPlaceEnfant);
       // System.out.println("nbpEnf ====>"+jour);

   


        
      
        int nbPlaceAdulteInt = Integer.parseInt(nbPlaceAdulte);
        int nbPlaceEnfantInt = Integer.parseInt(nbPlaceEnfant);
        int numLogementInt = Integer.parseInt(numLogement);
        

        /*	if (postValide(parametres, id,nom,prenom )) {*/
        ReservationLogementDAO LogementDAO = new ReservationLogementDAO();
        try {
			insert = LogementDAO.creerResaLogementChambre(idUtilisateur, numLogementInt, jour, nbPlaceAdulteInt, nbPlaceEnfantInt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //creer timer pour supprimer resa 
       /* if (insert == true) {
        	//timer 
        	TimerT t1 = new TimerT();
        	Timer t = new Timer();
        	
        	//appeler la methode pour check et supprimer si il faut
        	//t.schedule(t1, 10000);
        	
        	
        }*/
        
        
        //LesUtilisateurs utilisateur = new LesUtilisateurs(id, nom, prenom);
        response.setStatus(HttpServletResponse.SC_OK);
        // response.getWriter().println(new Gson().toJson(utilisateur));

        System.out.println("bingo des bengos !!!");

        //response.setStatus(HttpServletResponse.SC_OK);
        // A revoir 
        response.getWriter().println(new Gson().toJson(insert));
        /*}
				else {
				    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				    response.getWriter().println("Parametre non complet");
				}*/
    }
    

}

