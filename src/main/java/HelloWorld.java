import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.*;

import static org.imgscalr.Scalr.*;
 
public class HelloWorld extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().print("Hello from Java!  Again!\n");
        createThumbnail(null);
    }

    public static void main(String[] args) throws Exception{
        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new HelloWorld()),"/*");
        server.start();
        server.join();   
    }

    public static BufferedImage createThumbnail(BufferedImage img) {
        // Create quickly, then smooth and brighten it.
        img = resize(img, Method.SPEED, 125, OP_ANTIALIAS, OP_BRIGHTER);
 
        // Let's add a little border before we return result.
        return pad(img, 4);
    }
}
