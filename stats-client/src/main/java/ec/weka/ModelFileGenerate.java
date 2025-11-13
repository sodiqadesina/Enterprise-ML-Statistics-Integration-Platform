package ec.weka;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ConverterUtils.DataSource;
import java.io.FileWriter;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import weka.core.SerializationHelper;

public class ModelFileGenerate {

    public static void main(String[] args) throws Exception {
        String dataFile = "data/house.arff";
        String modelFile = "C:/enterprise/tmp/model/weka_regression.bin";
        String jsonModelFile = "C:/enterprise/tmp/model/weka_regression.json";
        
        Instances trainingData = DataSource.read(dataFile);
        trainingData.setClassIndex(trainingData.numAttributes() - 1);

        LinearRegression model = new LinearRegression();
        SelectedTag method = new SelectedTag(LinearRegression.SELECTION_NONE, LinearRegression.TAGS_SELECTION);
        model.setAttributeSelectionMethod(method);
        model.buildClassifier(trainingData);

        // Save binary model
        SerializationHelper.write(modelFile, model);
        
        // Save JSON model
        FileWriter fileWriter = null;
        JsonWriter jsonWriter = null;
        try {
            fileWriter = new FileWriter(jsonModelFile);
            jsonWriter = Json.createWriter(fileWriter);
            
            JsonObject jsonModel = Json.createObjectBuilder()
                .add("model_type", "LinearRegression")
                .add("model_name", "weka_lr")
                .add("input_features", Json.createArrayBuilder()
                        .add("houseSize")
                        .add("lotSize")
                        .add("bedrooms")
                        .add("granite")
                        .add("bathroom"))
                .add("output_feature", "sellingPrice")
                .add("coefficients", Json.createArrayBuilder()
                        .add(model.coefficients()[0])
                        .add(model.coefficients()[1])
                        .add(model.coefficients()[2])
                        .add(model.coefficients()[3])
                        .add(model.coefficients()[4]))
                .add("intercept", model.coefficients()[model.coefficients().length - 1])
                .build();
                
            jsonWriter.writeObject(jsonModel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (jsonWriter != null) {
                jsonWriter.close();
            }
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        System.out.println("Model saved as binary and JSON.");
    }
}
