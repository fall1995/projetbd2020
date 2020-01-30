package mesServelets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.LogementDAO;
import DAO.ResPlacesDAO;
import DAO.ResLogDao;
import mesClassesMetier.LesReservationsLogements;
import mesClassesMetier.LesReservationsPlaces;
import mesClassesMetier.Logement;

public class PanierLogServelet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println( "========================================================== PanierLogServelet [doGet] =========================================================" );
        response.setContentType("application/json");
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        Enumeration<String> P = request.getParameterNames();
        HashMap<String, String> parametres = new HashMap();
       

        while (P.hasMoreElements()) {
            String p = P.nextElement();
            parametres.put(p, request.getParameter((String) p));
        }
       

        String idUtilisateur;
    
        idUtilisateur = parametres.get("idUtilisateur"); 
        System.out.println("--------->"+idUtilisateur);
		ArrayList<LesReservationsLogements> resultat = new ArrayList<LesReservationsLogements>();
		ResLogDao resLogDao = new ResLogDao();



        
        
System.out.println("massinsissiana \n");
        resultat = resLogDao.resLog(idUtilisateur);
        System.out.println("apres \n");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println( new Gson().toJson(resultat));
        System.out.println("tout est ok");


    }
	
}
