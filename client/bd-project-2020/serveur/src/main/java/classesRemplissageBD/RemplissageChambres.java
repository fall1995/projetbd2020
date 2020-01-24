package classesRemplissageBD;

import static java.lang.Math.random;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

public class RemplissageChambres {

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
            RemplissageC(conn);

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

//Question 1
    public static void RemplissageC(Connection conn) throws SQLException {
        Statement st = null;
        ArrayList<Integer> listidHotel = new ArrayList<Integer>();
        ArrayList<Integer> listnumL = new ArrayList<Integer>();
        ArrayList<Integer> listidcamping = new ArrayList<Integer>();
        ArrayList<Integer> listidResidence = new ArrayList<Integer>();
        ArrayList<Integer> listidVilVac = new ArrayList<Integer>();
        ArrayList<Integer> listidVilVacNumLogement = new ArrayList<Integer>();
        ArrayList<Integer> listidVilVacIdHebergement = new ArrayList<Integer>();
        char tabTypeChambre[] = {'i', 'd', 'f'};
        System.out.println("**********" + tabTypeChambre.length);
      
        try {
            st = conn.createStatement();
            
             String query = "SELECT numLogement FROM LesLogements order by (numLogement)";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                String numL = rs.getString(1);
                 System.out.println("numLogement      " +numL );
                int numLogementInt = Integer.parseInt(numL);

               
                listnumL.add(numLogementInt);
            }
            
            
            String query2 = "SELECT idHebergement FROM LesHotels order by (idHebergement)";
            ResultSet rs2 = st.executeQuery(query2);
            while (rs2.next()) {
                
                String idH = rs2.getString(1);
                System.out.println("idHotel : "+idH);
                int idHint = Integer.parseInt(idH);
                listidHotel.add(idHint);

            }
            
            
            String query3 = "SELECT idHebergement FROM LesResidences order by (idHebergement)";
            ResultSet rs3 = st.executeQuery(query3);
            while (rs3.next()) {

                String idResidence = rs3.getString(1);
                System.out.println("idResidence : "+idResidence);
                int idResidenceInt = Integer.parseInt(idResidence);

                listidResidence.add(idResidenceInt);
            }
            
            
            
            
            String query4 = "SELECT idHebergement FROM LesCampings order by (idHebergement)";
            ResultSet rs4 = st.executeQuery(query4);
            while (rs4.next()) {

                String idCamping = rs4.getString(1);
                System.out.println("idCamping : "+idCamping);
                int idCampingInt = Integer.parseInt(idCamping);

                listidcamping.add(idCampingInt);
            }
            
            
 
            String query5 = "SELECT idHebergement FROM LesVillageVacances order by (idHebergement)";
            ResultSet rs5 = st.executeQuery(query5);
            while (rs5.next()) {

                String idVilVac = rs5.getString(1);
                int idVilVacInt = Integer.parseInt(idVilVac);

                listidVilVac.add(idVilVacInt);
            }
             
           
            int iChambre = 0;
            int idHeber = 0;
            for (int i = 0; i < listnumL.size() / 4; i++) {
                // System.out.println("------>      " + iChambre);
                int nbAdulte = 0;
                int nbEnfant = 0;
                Random random = new Random();
                int tarifAdulte = random.nextInt(50 + 1 - 30) + 30;

                int tarifEnfant = random.nextInt(30 + 1 - 10) + 10;
                int typeC = random.nextInt((tabTypeChambre.length - 1) + 1 - 0) + 0;

                switch (tabTypeChambre[typeC]) {
                    case 'i':
                        nbAdulte = 1;
                        System.out.println("1");
                        break;
                    case 'd':
                        nbAdulte = 2;
                        System.out.println("2");
                        break;
                    default:
                        System.out.println("3");
                        nbAdulte = 2;
                        nbEnfant = 3;
                        break;
                }
                // System.out.println("------------------>"+listnumL.get(iChambre));

                String StarifAdulte = tarifAdulte + "";
                StarifAdulte = "'" + StarifAdulte + "'";

                String StarifEnfant = tarifEnfant + "";
                StarifEnfant = "'" + StarifEnfant + "'";

                String SnbAdulte = nbAdulte + "";
                SnbAdulte = "'" + SnbAdulte + "'";

                String SnbEnfant = nbEnfant + "";
                SnbEnfant = "'" + SnbEnfant + "'";

                String numLogement2 = listnumL.get(iChambre) + "";
                numLogement2 = "'" + numLogement2 + "'";

                String idHotel2 = listidHotel.get(idHeber) + "";
                idHotel2 = "'" + idHotel2 + "'";

                String t = tabTypeChambre[typeC] + "";
                t = "'" + t + "'";

                System.out.println("Chambre " + numLogement2 + StarifAdulte + StarifEnfant + SnbAdulte + SnbEnfant + t + idHotel2);
                int nb = st.executeUpdate("INSERT INTO LesChambres(numLogement, tarifAdulte, tarifEnfant, nbAdultes, nbEnfants, typeChambre,idHebergement) VALUES (" + numLogement2 + " , " + StarifAdulte + " , " + StarifEnfant + " , " + SnbAdulte + " , " + SnbEnfant + " , " + t + ", " + idHotel2 + ")");
                System.out.println("Nombre de lignes mises à jour = " + nb);
                if (idHeber == listidHotel.size() - 1) {
                    idHeber = 0;
                } else {
                    idHeber++;
                }
                iChambre++;
            }

            
            
            
            int idRes = 0;
            for (int k = 0; k < listnumL.size() / 4; k++) {

                Random random = new Random();
                int tarif = random.nextInt(50 + 1 - 30) + 30;

                int nbPersCons = random.nextInt(10 + 1 - 2) + 2;

                String Starif = tarif + "";
                Starif = "'" + Starif + "'";

                String SnbPersCons = nbPersCons + "";
                SnbPersCons = "'" + SnbPersCons + "'";

                String numLogement2 = listnumL.get(iChambre) + "";
                numLogement2 = "'" + numLogement2 + "'";

                String idResidence = listidResidence.get(idRes) + "";
                idResidence = "'" + idResidence + "'";

                System.out.println("Residence     >" + numLogement2 + Starif + SnbPersCons + idResidence);
                int nb = st.executeUpdate("INSERT INTO LesLogementsResidences(numLogement, tarif, nbPersonneConseille, idHebergement) VALUES (" + numLogement2 + " , " + Starif + " , " + SnbPersCons + " , " + idResidence + ")");
                System.out.println("Nombre de lignes mises à jour = " + nb);
                if (idRes == listidResidence.size() - 1) {
                    idRes = 0;
                } else {
                    idRes++;
                }
                iChambre++;
            }


         
            
            int idcamp = 0;
            for (int j = 0; j < listnumL.size() / 4; j++) {
                

                Random random = new Random();
                int tarif = random.nextInt(50 + 1 - 30) + 30;

                // System.out.println("------------------>"+listnumL.get(iChambre));
                String Starif = tarif + "";
                Starif = "'" + Starif + "'";

                String numLogement2 = listnumL.get(iChambre) + "";
                numLogement2 = "'" + numLogement2 + "'";

                String idCaming2 = listidcamping.get(idcamp) + "";
                
                idCaming2 = "'" + idCaming2 + "'";
                // System.out.println("&&&&&&&&&&"+idCaming2);

                System.out.println("Camping      >" + numLogement2 + Starif + idCaming2);
                int nb = st.executeUpdate("INSERT INTO LesLogementsCampings(numLogement, tarif, idHebergement) VALUES (" + numLogement2 + " , " + Starif + ", " + idCaming2 + ")");
                System.out.println("Nombre de lignes mises à jour = " + nb);
                if (idcamp == listidcamping.size() - 1) {
                    idcamp = 0;
                } else {
                    idcamp++;
                }
                iChambre++;
            }
            

            
             int idVillage = 0;
            for (int l = 0; l < listnumL.size() / 4; l++) {
                
  

                String numLogement2 = listnumL.get(iChambre) + "";
                numLogement2 = "'" + numLogement2 + "'";

                String idVill2 = listidVilVac.get(idVillage) + "";
                
                idVill2 = "'" + idVill2 + "'";
               

                System.out.println("VillageDeVacances      >" + numLogement2 + idVill2);
               int nb = st.executeUpdate("INSERT INTO LesEmplacementsVillages(numLogement, idHebergement) VALUES (" + numLogement2 + ", " + idVill2 + ")");
                System.out.println("Nombre de lignes mises à jour = " + nb);
                if (idVillage == listidVilVac.size() - 1) {
                    idVillage = 0;
                } else {
                    idVillage++;
                }
                iChambre++;
            }

            
            
            
            
            
            
            String query6 = "SELECT numLogement,idHebergement FROM LesEmplacementsVillages order by (numLogement)";
            ResultSet rs6 = st.executeQuery(query6);
            while (rs6.next()) {

                String numLogement = rs6.getString(1);
                int numLogementInt = Integer.parseInt(numLogement);
                listidVilVacNumLogement.add(numLogementInt);
                
                String idHebergement = rs6.getString(2);
                int idHebergementInt = Integer.parseInt(idHebergement);
                listidVilVacIdHebergement.add(idHebergementInt);
                System.out.println(+numLogementInt+ "   aywahhh  "+ idHebergementInt);
            }
            
            
            
            int size = listidVilVacNumLogement.size();
           
            //  Remplissage LesEmplacementsChambres
            int countLogement = 0;
            
            for (int x = 0; x <listidVilVacNumLogement.size()/2; x++) {
                System.out.println("countLogement ---->"  +countLogement);
                int nbAdulte = 0;
                int nbEnfant = 0;
                Random random = new Random();
                int tarifAdulte = random.nextInt(40 + 1 - 20) + 20;

                int tarifEnfant = random.nextInt(20 + 1 - 5) + 5;
                int typeC = random.nextInt((tabTypeChambre.length - 1) + 1 - 0) + 0;

                switch (tabTypeChambre[typeC]) {
                    case 'i':
                        nbAdulte = 1;
                      
                        break;
                    case 'd':
                        nbAdulte = 2;
                      
                        break;
                    default:
                        
                        nbAdulte = 2;
                        nbEnfant = 3;
                        break;
                }
                

                String StarifAdulte = tarifAdulte + "";
                StarifAdulte = "'" + StarifAdulte + "'";

                String StarifEnfant = tarifEnfant + "";
                StarifEnfant = "'" + StarifEnfant + "'";

                String SnbAdulte = nbAdulte + "";
                SnbAdulte = "'" + SnbAdulte + "'";

                String SnbEnfant = nbEnfant + "";
                SnbEnfant = "'" + SnbEnfant + "'";

                String numLogement2 = listidVilVacNumLogement.get(countLogement) + "";
                numLogement2 = "'" + numLogement2 + "'";

                String idH = listidVilVacIdHebergement.get(countLogement) + "";
                idH = "'" + idH + "'";

                System.out.println("Chambre Village   " + numLogement2 + StarifAdulte + StarifEnfant + SnbAdulte + SnbEnfant  + idH);
                int nb = st.executeUpdate("INSERT INTO LesEmplacementsChambres(numLogement, tarifAdulte, tarifEnfant, nbAdultes, nbEnfants,idHebergement) VALUES (" + numLogement2 + " , " + StarifAdulte + " , " + StarifEnfant + " , " + SnbAdulte + " , " + SnbEnfant + " , "  + idH + ")");
                System.out.println("Nombre de lignes mises à jour = " + nb);
                countLogement++;
            }

            
            
            
              //  Remplissage LesEmplacementsCollectif
            
           
            for (int r = 0; r <  size/2; r++) {
              
                System.out.println("countLogement ---->"  +countLogement);
                int tarif = 15;

                String Starif = tarif + "";
                Starif = "'" + Starif + "'";

                String numLogement2 = listidVilVacNumLogement.get(countLogement) + "";
                numLogement2 = "'" + numLogement2 + "'";

                String idH = listidVilVacIdHebergement.get(countLogement) + "";
                idH = "'" + idH + "'";

                System.out.println("Chambre Collectif  " + numLogement2 + Starif  + idH);
                int nb = st.executeUpdate("INSERT INTO LesEmplacementsCollectifs(numLogement, tarif,idHebergement) VALUES (" + numLogement2 + " , " + Starif +  " , "  + idH + ")");
                System.out.println("Nombre de lignes mises à jour = " + nb);
                
                countLogement++;
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
