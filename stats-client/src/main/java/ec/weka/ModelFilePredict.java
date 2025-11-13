package ec.weka;

import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SerializationHelper;
import weka.core.converters.ConverterUtils.DataSource;
import java.io.FileWriter;
import java.io.IOException;

public class ModelFilePredict {

    public static void main(String[] args) throws Exception {
        String modelFile = "C:/enterprise/tmp/model/weka_regression.bin";
        String predictFile = "data/house_predict.arff";
        String outputFile = "result.txt";

        Instances predictData = DataSource.read(predictFile);
        predictData.setClassIndex(predictData.numAttributes() - 1);

        Classifier model = (Classifier) SerializationHelper.read(modelFile);

        FileWriter writer = null;
        try {
            writer = new FileWriter(outputFile);
            writer.write("Predicted Values:\n");
            for (int i = 0; i < predictData.numInstances(); i++) {
                Instance instance = predictData.instance(i);
                double prediction = model.classifyInstance(instance);
                writer.write("Instance " + (i + 1) + ": " + prediction + "\n");
            }
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
        System.out.println("Prediction completed. Results saved to " + outputFile);
    }
}
