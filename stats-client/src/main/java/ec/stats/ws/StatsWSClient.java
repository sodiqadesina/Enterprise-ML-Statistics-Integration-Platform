package ec.stats.ws;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class StatsWSClient {
    public static void main(String[] args) {
        try {
            // Definin WSDL URL
            URL wsdlURL = new URL("http://localhost:8080/stats-ws/StatsWSImpl?wsdl");

           
            QName SERVICE_NAME = new QName("http://stats.ec/", "StatsWSImplService");
            QName PORT_NAME = new QName("http://stats.ec/", "StatsWSImplPort");

            // Creating service and port
            Service service = Service.create(wsdlURL, SERVICE_NAME);
            StatsWS statsService = service.getPort(PORT_NAME, StatsWS.class);

            // Invoke methods and display results
            System.out.println("Count: " + statsService.getCount());
            System.out.println("Min: " + statsService.getMin());
            System.out.println("Max: " + statsService.getMax());
            System.out.println("Mean: " + statsService.getMean());
            System.out.println("STD: " + statsService.getSTD());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
