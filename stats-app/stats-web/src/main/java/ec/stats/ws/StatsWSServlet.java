package ec.stats.ws;

import ec.stats.ws.StatsWS;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

@WebServlet("/statsws")
public class StatsWSServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    	 // Definin WSDL URL
        URL wsdlURL = new URL("http://localhost:8080/stats-ws/StatsWSImpl?wsdl");

       
        QName SERVICE_NAME = new QName("http://stats.ec/", "StatsWSImplService");
        QName PORT_NAME = new QName("http://stats.ec/", "StatsWSImplPort");

        // Creating service and port
        Service service = Service.create(wsdlURL, SERVICE_NAME);
        StatsWS statsService = service.getPort(PORT_NAME, StatsWS.class);

        // Fetch statistics summary data from the web service
        double count = statsService.getCount();
        double min = statsService.getMin();
        double max = statsService.getMax();
        double mean = statsService.getMean();
        double std = statsService.getSTD();

        // Prepare HTML response
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
            out.println("<title>Statistics Summary</title></head>");
            out.println("<body><h1>Statistics Summary Information</h1>");
            out.println("<p>Count: " + count + "</p>");
            out.println("<p>Min: " + min + "</p>");
            out.println("<p>Max: " + max + "</p>");
            out.println("<p>Mean: " + mean + "</p>");
            out.println("<p>Standard Deviation: " + std + "</p>");
            out.println("</body></html>");
        } catch (Exception ex) {
            throw new ServletException(ex);
        } finally {
            out.close();
        }
    }
}
