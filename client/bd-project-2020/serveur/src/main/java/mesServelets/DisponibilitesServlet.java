package mesServelets;


/**
 *
 * @author bouaziz
 */
import DAO.DisponibiliteLogmentDAO;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.FestivalDAO;
import DAO.HebergementDAO;
import DAO.LogementDAO;
import mesClassesMetier.Festival;
import mesClassesMetier.Hebergement;
import mesClassesMetier.Logement;

public class DisponibilitesServlet extends HttpServlet{
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println( "========================================================== DisponibilitesServlet [doGet] =========================================================" );
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        Enumeration<String> P = request.getParameterNames();
        HashMap<String, String> parametres = new HashMap();
       

        while (P.hasMoreElements()) {
            String p = P.nextElement();
            parametres.put(p, request.getParameter((String) p));
        }
       

        String numLogement;
        
        numLogement = parametres.get("numLogement"); 
    
       
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
        ArrayList<Date> resultat = new ArrayList<Date> ();
        DisponibiliteLogmentDAO LDao = new DisponibiliteLogmentDAO();
        // la methode suivante ne va pas marcher a cause du type Date 
        // 

        // resultat = festDao.getFestival(domaine, datedebut, datefin, ville);
        // String json = new Gson().toJson(resultat);


        
        
System.out.println("avant \n");
        //resultat = festDao.getFestival();
        resultat = LDao.getDateDisponibilitesLogement(numLogement);
        System.out.println("apres \n");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println( new Gson().toJson(resultat));
        System.out.println("tout est ok");


    }
}

