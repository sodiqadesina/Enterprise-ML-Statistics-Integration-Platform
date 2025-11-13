package ec.weka;

import ec.stats.sb.LRStatelessLocal;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/predict")
public class LRServlet extends HttpServlet {

	 @EJB
    private LRStatelessLocal lrStateless;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parse query string
        String name = request.getParameter("name");
        String query = request.getParameter("query");

        // Validate input
        if (name == null || query == null || !name.equals("weka-lr")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input parameters");
            return;
        }

        // Convert query string to double array
        String[] features = query.split(",");
        double[] inputValues = new double[features.length];
        try {
            for (int i = 0; i < features.length; i++) {
                inputValues[i] = Double.parseDouble(features[i]);
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid input format");
            return;
        }

        // Set up Weka instances
        ArrayList<Attribute> houseAttributes = new ArrayList<>();
        houseAttributes.add(new Attribute("houseSize"));
        houseAttributes.add(new Attribute("lotSize"));
        houseAttributes.add(new Attribute("bedrooms"));
        houseAttributes.add(new Attribute("granite"));
        houseAttributes.add(new Attribute("bathroom"));
        houseAttributes.add(new Attribute("sellingPrice")); // Output attribute

        Instances predictInstance = new Instances("HousePredict", houseAttributes, 0);
        predictInstance.setClassIndex(predictInstance.numAttributes() - 1);

        // Add input values as a new instance
        predictInstance.add(new DenseInstance(1, inputValues));

        // Invoke the prediction method
        double prediction;
        try {
            prediction = lrStateless.prediction(inputValues);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Prediction error");
            e.printStackTrace();
            return;
        }

        // Output the prediction result
        response.setContentType("text/plain");
        try (PrintWriter out = response.getWriter()) {
            out.println("Predicted Selling Price: " + prediction);
        }
    }
}
