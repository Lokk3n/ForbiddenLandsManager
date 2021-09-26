package ForbiddenLandsServer;

import ForbiddenLandsServer.HibernateUtil.HibernateUtil;
import ForbiddenLandsServer.NettyServer.NettyServer;
import org.hibernate.Session;

import java.util.Date;

public class FLServer {

    static int port = 3663;
    static NettyServer server;

    public static void main(String[] args) {
        FLServer mgr = new FLServer();
        mgr.createAndStoreEvent(new Date());
        HibernateUtil.getSessionFactory().close();

        server = new NettyServer(port);
        try {
            server.start();
            System.out.println("Server started on port: " + 3663);
        }
        catch(Exception e){
            System.out.println("Exception thrown");
            e.printStackTrace();
        }
    }

    private void createAndStoreEvent(Date theDate) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        ServerStartEvent theServerStartEvent = new ServerStartEvent();
        theServerStartEvent.setTitle("Server started");
        theServerStartEvent.setDate(theDate);
        session.save(theServerStartEvent);

        session.getTransaction().commit();
    }
}
