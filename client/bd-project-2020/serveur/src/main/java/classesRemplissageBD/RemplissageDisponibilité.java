package classesRemplissageBD;

import static java.lang.Math.random;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

public class RemplissageDisponibilité {

    static final String CONN_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";

    static final String USER = "bouazizl";
    static final String PASSWD = "Lamine96";

    static Connection conn;

    public static void main(String args[]) {

        try {

            // Enregistrement du driver Oracle
            System.out.print("Loading Oracle driver... ");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("loaded");

            // Etablissement de la connection
            System.out.print("Connecting to the database... ");
            conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
            System.out.println("connected");

            // Desactivation de l'autocommit
            conn.setAutoCommit(false);
            System.out.println("Autocommit disabled");

            // code m�tier de la fonctionnalit�
            // Liberation des ressources et fermeture de la connexion...
            RemplissageDispo(conn);

            conn.close();

            System.out.println("bye.");

            // traitement d'exception
        } catch (SQLException e) {
            System.err.println("failed");
            System.out.println("Affichage de la pile d'erreur");
            e.printStackTrace(System.err);
            System.out.println("Affichage du message d'erreur");
            System.out.println(e.getMessage());
            System.out.println("Affichage du code d'erreur");
            System.out.println(e.getErrorCode());

        }
    }

    public static int randBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static void RemplissageDispo(Connection conn) throws SQLException {
        Statement st = null;

        ArrayList<Integer> listnumL = new ArrayList<Integer>();

        try {
            st = conn.createStatement();

            String query = "SELECT numLogement FROM LesLogements order by (numLogement)";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String numL = rs.getString(1);
                System.out.println("numLogement      " + numL);
                int numLogementInt = Integer.parseInt(numL);
                listnumL.add(numLogementInt);
            }

            for (int l = 0; l < listnumL.size() ; l++) {
                ArrayList<String> listAlea = new ArrayList<String>();
                String numLogement2 = listnumL.get(l) + "";
                numLogement2 = "'" + numLogement2 + "'";
                int alea = randBetween(1, 365);
                System.out.println("Aleatoire ---------------------------->"+alea);
                for (int i = 0; i < 1; i++) {
                    
                    //Générer une date aléatoire
                    GregorianCalendar gc = new GregorianCalendar();
                    int year = randBetween(2019, 2019);;
                    gc.set(gc.YEAR, year);
                    int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
                    gc.set(gc.DAY_OF_YEAR, dayOfYear);
                    String date = gc.get(gc.DAY_OF_MONTH) + "-" + (gc.get(gc.MONTH) + 1) + "-" + gc.get(gc.YEAR);
                    
                    
                    
                    if(listAlea.contains(date)== false){
                        listAlea.add(date);
                        date = "'" + date + "'";
                        System.out.println("disponibilité      >" + date + numLogement2);
                         
                        int nb = st.executeUpdate("INSERT INTO LesPeriodeDeDisponibilites(dateDispo, numLogement) VALUES (" + date + ", " + numLogement2 + ")");
                        System.out.println("Nombre de lignes mises à jour = " + nb);
                        
                        //System.out.println(listAlea.get(i));
                    }
                    
                    
                    
                   
                    
                }


            }

            st.close();

            // System.out.println(" ta chaine:"+ numCage);
        } finally {
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}
