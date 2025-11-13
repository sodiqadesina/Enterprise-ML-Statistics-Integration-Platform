package ec.ml.cars;

import weka.classifiers.Classifier;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
 
public class Predict{
  /**
   * predict the class of unlabeled data using a stored model
   */
	
	
 
  public static void main(String[] args) throws Exception {
  
	//String DATA_PATH = "/home/rinlab/workspace/ML_Models/data/";
	  //String DATA_PATH = "/data/";
	String DATA_PATH = "data/";  
	String MODEL= " ";
    
    /* usage:
     * 
     * arguments:
     *  1: service (cars or ta)
     *  predict car acceptability or TA evaluation.
     *  
     *  2: machine learning prediction model to use (svm or knn or bayes)
     *  
     *  3: user input instance to predict its result
     *  
     *  4: output file to save the result
     * 
     */
    
    System.out.println("Usage: ");
    System.out.println("java -jar Predict.jar <service> <ML_model> <instance> <output_file> ");
    System.out.println("Example: ");
    System.out.println("java -jar Predict.jar cars svm <your path >/car_predict.arff"
    		+ " <your path >/car_predict_result.arff ");
    
    System.out.println(args[0]);
   
    
    if (args[0].equals("cars") && args[1].equals("svm") ){
    	MODEL=DATA_PATH+"car_svm.bin";
    }
    else if (args[0].equals("cars") && args[1].equals("knn")){
    	MODEL=DATA_PATH+"car_knn.bin";
    }
    
    else if (args[0].equals("cars") && args[1].equals("bayes")){
    	MODEL=DATA_PATH+"car_bayes.bin";
    }
    
    
    else if (args[0].equals("ta") && args[1].equals("svm")){
    	MODEL=DATA_PATH+"tae_svm.bin";
    }
    
    else if (args[0].equals("ta") && args[1].equals("bayes")){
    	MODEL=DATA_PATH+"tae_bayes.bin";
    }
    
    else if (args[0].equals("ta") && args[1].equals("knn")){
    	MODEL=DATA_PATH+"tae_knn.bin";
    }
    
    else {
    	
        
        System.out.println("Usage: ");
        System.out.println("java -jar Predict.jar <service> <ML_model> <instance> <output_file> ");
        System.out.println("Example: ");
        System.out.println("java -jar Predict.jar cars svm <your path >/tae_predict.arff"
        		+ " <your path >/car_predict_result.arff ");
    }
    
    
    System.out.println(MODEL);
    
    // load unlabeled data to test classifier 
    Instances unlabeled = new Instances(
                            new BufferedReader(
                              new FileReader(args[2])));
    
    
 // set class attribute
    unlabeled.setClassIndex(unlabeled.numAttributes() - 1);
    
    // create copy
    Instances labeled = new Instances(unlabeled);
    
    
    System.out.println("number of input instances:" + unlabeled.numInstances());
    System.out.println();
    
    // deserialize model
    // any saved model can be used to perform the prediction (car_svm.model, car_bayes.model or car_knn.model)
    Classifier cls1 = (Classifier) weka.core.SerializationHelper.read(MODEL);

    
    // label instances
    for (int i = 0; i < unlabeled.numInstances(); i++) {
      double clsLabel = cls1.classifyInstance(unlabeled.instance(i));
      System.out.println(unlabeled.instance(i));
      System.out.println(clsLabel + " -> " + unlabeled.classAttribute().value((int) clsLabel));
      labeled.instance(i).setClassValue(clsLabel);
    }
    // save prediction result
    BufferedWriter writer = new BufferedWriter(
                              new FileWriter(args[3]));
    writer.write(labeled.toString());
    writer.newLine();
    writer.flush();
    writer.close();
    
  }
}