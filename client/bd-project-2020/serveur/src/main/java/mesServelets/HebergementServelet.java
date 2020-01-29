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
import DAO.HebergementDAO;
import mesClassesMetier.Festival;
import mesClassesMetier.Hebergement;

public class HebergementServelet extends HttpServlet{
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println( "========================================================== HebergementServlet [doGet] =========================================================" );
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        Enumeration<String> P = request.getParameterNames();
        HashMap<String, String> parametres = new HashMap();
       

        while (P.hasMoreElements()) {
            String p = P.nextElement();
            parametres.put(p, request.getParameter((String) p));
        }
       

        String  idFestival, typeHebergement, classement ;
        idFestival = parametres.get("idFestival"); 
        typeHebergement = parametres.get("typeHebergement");
        classement = parametres.get("classement");
       
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
        ArrayList<Hebergement> resultat = new ArrayList<Hebergement> ();
        HebergementDAO HebDao = new HebergementDAO();
        // la methode suivante ne va pas marcher a cause du type Date 
        // 

        // resultat = festDao.getFestival(domaine, datedebut, datefin, ville);
        // String json = new Gson().toJson(resultat);


        
        
System.out.println("avant \n");
        //resultat = festDao.getFestival();
        resultat = HebDao.affiner(idFestival, typeHebergement, classement);
        System.out.println("apres \n");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println( new Gson().toJson(resultat));
        System.out.println("tout est ok");


    }
}

