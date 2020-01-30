package mesServelets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.ReservationLogementDAO;

public class ValiderServelet extends HttpServlet  {
	
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
	        //System.out.println("numLogement ====>"+numLogement);
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
