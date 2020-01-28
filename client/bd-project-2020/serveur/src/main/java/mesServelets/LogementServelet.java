package mesServelets;


/**
 *
 * @author bouaziz
 */
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
        System.out.println("================================================== LogementServlet [doPost] ==================================================");

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
        //boolean insert = false;
        String idUtilisateur = parametres.get("idUtilisateur");
        String idF = parametres.get("idFestival");
        String j = parametres.get("jour");
        String nbPlaceS = parametres.get("nbPlaceSansCateg");
        String nbPlaceC1 = parametres.get("nbPlaceCateg1");
        String nbPlaceC2 = parametres.get("nbPlaceCateg2");
        System.out.println("nbPlaceS =" + nbPlaceS);
        System.out.println("nbPlaceC1 =" + nbPlaceC1);
        System.out.println("nbPlaceC2 =" + nbPlaceC2);

        System.out.println("jour =" + j);

        int idFestival = Integer.parseInt(idF);
        int jour = Integer.parseInt(j);
        int nbPlaceSansCateg = Integer.parseInt(nbPlaceS);
        int nbPlaceCateg1 = Integer.parseInt(nbPlaceC1);
        int nbPlaceCateg2 = Integer.parseInt(nbPlaceC2);

        /*	if (postValide(parametres, id,nom,prenom )) {*/
        PaquetsBilletsDAO paquetDAO = new PaquetsBilletsDAO();
        insert = paquetDAO.reserverBillets(idUtilisateur, idFestival, jour, nbPlaceSansCateg, nbPlaceCateg1, nbPlaceCateg2);

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

