package ec.weka;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.DenseInstance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Blob;
import java.io.ObjectInputStream;
import java.io.FileWriter;
import java.sql.DriverManager;
import java.util.ArrayList;

public class ModelDBPredict {

    public static void main(String[] args) throws Exception {
        // Database settings
    	  String url = "jdbc:mysql://localhost:3306/test";  
          String user = "root";                                  
          String password = "";                         
                         
        String tableName = "ecmodel";
        String modelName = "weka-lr";
        String queryString = "3198,9669,5,1,1,0";

        // Split query string to retrieve prediction values
        String[] queryStringArray = queryString.split(",");
        double[] queryValues = new double[queryStringArray.length];
        for (int i = 0; i < queryStringArray.length; i++) {
            queryValues[i] = Double.parseDouble(queryStringArray[i]);
        }

        // Database connection and model loading
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Classifier model = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT object FROM " + tableName + " WHERE name = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, modelName);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Blob blob = rs.getBlob("object");
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(blob.getBinaryStream());
                    model = (Classifier) ois.readObject();
                    System.out.println("Model loaded successfully from the database.");
                } finally {
                    if (ois != null) {
                        ois.close();
                    }
                }
            } else {
                System.err.println("Model not found in the database.");
                return;
            }
        } finally {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        }

        // Prediction setup
        Instances dataStructure = new Instances(new DataSource("data/house.arff").getStructure());
        dataStructure.setClassIndex(dataStructure.numAttributes() - 1);

        Instance instance = new DenseInstance(dataStructure.numAttributes());
        instance.setDataset(dataStructure);
        for (int i = 0; i < queryValues.length; i++) {
            instance.setValue(i, queryValues[i]);
        }

        // Perform prediction
        double prediction = model.classifyInstance(instance);
        System.out.println("Prediction result: " + prediction);
    }
}
