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
import mesClassesMetier.Festival;

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
       

        /*String  domaine, ville, datedebut, datefin ;
        domaine = parametres.get("domaine"); 
        ville = parametres.get("ville");
        datedebut = parametres.get("dateDebut");
        datefin = parametres.get("dateFin");*/
     
        
        String idFestival ;
        idFestival = parametres.get("idFestival");
        
        
        
        /*
         * Dans cette partie faudra transformer datedebut et datefin en Date
         */
        
        //decommenter ca pour affiner
       // ArrayList<Festival> resultat = new ArrayList<Festival>();
        boolean resultat ;
        FestivalDAO festDao = new FestivalDAO();
        // la methode suivante ne va pas marcher a cause du type Date 
        // 

        // resultat = festDao.getFestival(domaine, datedebut, datefin, ville);
        // String json = new Gson().toJson(resultat);


        
        

       //decommenter ca pour affiner
      //  resultat = festDao.affiner(domaine,datedebut, datefin, ville);
        resultat = festDao.supprimerFestival(idFestival);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println( new Gson().toJson(resultat));
        System.out.println("tout est ok");


    }
}

