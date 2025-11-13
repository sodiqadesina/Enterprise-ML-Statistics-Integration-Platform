package ec.ml.tae;

/* check Weka wiki for more detail on using weka library with java
https://weka.wikispaces.com/Use%20Weka%20in%20your%20Java%20code#Classifying%20instances
*/

import weka.classifiers.Classifier;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class use_bayes_model {

	public static void main(String[] args) throws Exception {
		String DATA_PATH = "data1/";
		String MODEL_PATH = "model/";
		
		// load unlabeled data to test classifier
		Instances unlabeled = new Instances(new BufferedReader(new FileReader(DATA_PATH + "tae_unlabeled.arff")));

		// set class attribute
		unlabeled.setClassIndex(unlabeled.numAttributes() - 1);

		// create copy
		Instances labeled = new Instances(unlabeled);

		System.out.println("unlabeled.numInstances number:" + unlabeled.numInstances());

		// deserialize model
		Classifier cls1 = (Classifier) weka.core.SerializationHelper.read(MODEL_PATH + "tae_bayes.bin");

		// label instances
		for (int i = 0; i < unlabeled.numInstances(); i++) {
			double clsLabel = cls1.classifyInstance(unlabeled.instance(i));
			System.out.println(clsLabel + " -> " + unlabeled.classAttribute().value((int) clsLabel));
			labeled.instance(i).setClassValue(clsLabel);
		}
		// save labeled data
		// you can compare the classifier predicted classes with actual classes
		// in file tae_test.arff
		BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_PATH + "tae_labeled.arff"));
		writer.write(labeled.toString());
		writer.newLine();
		writer.flush();
		writer.close();

	}

}