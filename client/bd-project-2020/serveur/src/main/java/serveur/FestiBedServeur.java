package serveur;

import javax.servlet.http.HttpServlet;
import java.util.*;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;

import Timer.TimerT;
import mesServelets.*;

/**
 * @author Groupe6 PizzaServer
 */
public class FestiBedServeur extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Server server;

    /**
     * Methode qui permet de demarrer le server
     *
     * @throws Exception
     */
    void start() throws Exception {
        int maxThreads = 100;
        int minThreads = 10;
        int idleTimeout = 120;
        QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);

        server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8090);
        server.setConnectors(new Connector[]{connector});

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(BlockingServlet.class, "/status");
        //servletHandler.addServletWithMapping(ClientAuthentificationServlet.class, "/api/authentification");
       
        servletHandler.addServletWithMapping(AuthentificationServelet.class, "/api/authentification");
        servletHandler.addServletWithMapping(FestivalServelet.class, "/api/festivals");
        servletHandler.addServletWithMapping(HebergementServelet.class, "/api/hebergements");
        servletHandler.addServletWithMapping(LogementServelet.class, "/api/logements");
        servletHandler.addServletWithMapping(DisponibilitesServlet.class, "/api/disponibilitesLogement");

        servletHandler.addServletWithMapping(BilletServelet.class, "/api/billets");
        servletHandler.addServletWithMapping(PanierServelet.class, "/api/panier");
       /* TimerT t1 = new TimerT();
        Timer t = new Timer();
        t.schedule(t1, 10000); //  executes for every 10 seconds*/
        
        
        server.start();
        
        
    }

    /**
     * Methode qui permet d' arreter le server
     *
     * @throws Exception
     */
    void stop() throws Exception {
        System.out.println("Server stop");
        server.stop();
    }

    /**
     * Methode main qui permet de demarrer le server
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        FestiBedServeur server = new FestiBedServeur();
        server.start();
    }

}
