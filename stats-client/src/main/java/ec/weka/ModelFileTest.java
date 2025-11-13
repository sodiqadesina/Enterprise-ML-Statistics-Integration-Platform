package ec.weka;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;
import java.io.FileWriter;
import java.io.IOException;

public class ModelFileTest {

    public static void main(String[] args) throws Exception {
        String modelFile = "C:/enterprise/tmp/model/weka_regression.bin";
        String testDataFile = "data/house_test.arff";
        String outputFile = "test_result.txt";

        Instances testData = DataSource.read(testDataFile);
        testData.setClassIndex(testData.numAttributes() - 1);

        LinearRegression model = (LinearRegression) SerializationHelper.read(modelFile);
        
        Evaluation eval = new Evaluation(testData);
        eval.evaluateModel(model, testData);

        FileWriter writer = null;
        try {
            writer = new FileWriter(outputFile);
            writer.write("Model Evaluation Results:\n");
            writer.write(eval.toSummaryString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("Testing completed. Results saved to " + outputFile);
    }
}
