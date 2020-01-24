package mesServelets;

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
import mesClasses.Festival;

public class FestivalServelet extends HttpServlet{
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println( "========================================================== FestivalServlet [doGet] =========================================================" );
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        Enumeration<String> P = request.getParameterNames();
        HashMap<String, String> parametres = new HashMap();
       

        while (P.hasMoreElements()) {
            String p = P.nextElement();
            parametres.put(p, request.getParameter((String) p));
        }
       

        String  domaine, ville, datedebut, datefin ;
        domaine = parametres.get("domaine"); 
        ville = parametres.get("ville");
        datedebut = parametres.get("dateDebut");
        datefin = parametres.get("dateFin");
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
        ArrayList<Festival> resultat = new ArrayList<Festival>();
        FestivalDAO festDao = new FestivalDAO();
        // la methode suivante ne va pas marcher a cause du type Date 
        // 

        // resultat = festDao.getFestival(domaine, datedebut, datefin, ville);
        // String json = new Gson().toJson(resultat);


        
        

        //resultat = festDao.getFestival();
        resultat = festDao.affiner(resultat,domaine,datedebut, datefin, ville);
        //System.out.println("apres \n");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println( new Gson().toJson(resultat));
        System.out.println("tout est ok");


    }
}

