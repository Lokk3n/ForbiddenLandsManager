package ForbiddenLandsServer.NettyServer.Handlers;

import Common.protocol.Login.LoginRequest;
import Common.protocol.Login.LoginResponse;
import Common.protocol.PingPong.Ping;
import ForbiddenLandsServer.HibernateUtil.Entities.ServerStartEvent;
import ForbiddenLandsServer.HibernateUtil.Entities.UserAccount;
import ForbiddenLandsServer.HibernateUtil.HibernateUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class LoginHandler extends SimpleChannelInboundHandler<LoginRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequest loginRequest) {
        System.out.println("Entered login handler");
        LoginResponse response = new LoginResponse();
        response.setRequestId(loginRequest.getRequestId());

        System.out.println(loginRequest.getUsername());
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String requestedUsername = loginRequest.getUsername();
        System.out.println(requestedUsername);

        UserAccount userAccount = null;
        try {
            String hql = "FROM UserAccount UA WHERE UA.username = :requestedUsername";
//            Query query = session.createQuery("FROM UserAccount WHERE UserAccount.username = :requestedUsername");
            Query query = session.createQuery(hql);
            query.setParameter("requestedUsername", loginRequest.getUsername());
            List results = query.list();
            if(results.size() > 0) userAccount = (UserAccount) results.get(0);
        }
        catch(Exception e){
            e.printStackTrace();
        }
//        System.out.println("Baza Login " + userAccount.getUsername());
//        System.out.println("Baza Hasło " + userAccount.getPassword());
//        System.out.println("Requ Hasło " + loginRequest.getPassword());
        if(userAccount != null && userAccount.getPassword().equals(loginRequest.getPassword())) {
            System.out.println("Password matched");
            UUID token = UUID.randomUUID();
            response.setToken(token.toString());
            userAccount.setToken(token.toString());
            session.update(userAccount);
        }

        session.getTransaction().commit();
        session.close();
        ctx.writeAndFlush(response);
    }
}
