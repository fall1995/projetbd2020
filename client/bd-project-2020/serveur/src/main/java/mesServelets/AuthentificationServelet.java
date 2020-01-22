package mesServelets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.UtilisateurAuthenficationDAO;

public class AuthentificationServelet extends HttpServlet{
	
	static  boolean postValide(HashMap<String, String> parametres, String id,  String nom,  String prenom) {
	        boolean res = false;
	        //on verifie si le parametre contient une clé
	        if (parametres.containsKey(id) && parametres.containsKey(nom) && parametres.containsKey(prenom)) {
	            //verification si les valeurs ne sont pas null
	            if (parametres.get(id) != null && parametres.get(nom) != null && parametres.get(prenom) != null) {
	                res = true;
	            }
	        }

	        return res;
	    }

	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        System.out.println( "================================================== ClientAuthentificationServlet [doPost] ==================================================" );
	        System.out.print("L'identification du client ...");
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
	       
	            String id = parametres.get("idClient");
				String nom = parametres.get("nom");
				String prenom = parametres.get("prenom");
				if (postValide(parametres, id,nom,prenom )) {
					UtilisateurAuthenficationDAO utiliDao = new UtilisateurAuthenficationDAO();
					try {
						if (!utiliDao.exist(id)) {
							utiliDao.addClient(id, nom, prenom);
							 System.out.println("le client est crée avec succès !");
						} else {
							  System.out.println("le client est logé avec succès !");
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
           
				
				
				response.setStatus(HttpServletResponse.SC_OK);
				// A revoir 
				//response.getWriter().println(new Gson().toJson(client));
				}
				else {
				    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				    response.getWriter().println("Parametre non complet");
				}
	       
	    }
}


