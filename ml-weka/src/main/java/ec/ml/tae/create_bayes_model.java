package ec.ml.tae;

/* check Weka wiki for more detail on using weka library with java
https://weka.wikispaces.com/Use%20Weka%20in%20your%20Java%20code#Classifying%20instances
*/

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;

public class create_bayes_model {

	public static void main(String[] args) throws Exception {
		String DATA_PATH = "data1/";
		String MODEL_PATH = "model/";
		
		// create Naive Bayes classifier
		Classifier cls = new NaiveBayes();

		// train the classifier
		Instances inst = new Instances(new BufferedReader(new FileReader(DATA_PATH + "tae.arff")));
		inst.setClassIndex(inst.numAttributes() - 1);
		cls.buildClassifier(inst);

		// serialize model and save it as a .model file
		weka.core.SerializationHelper.write(MODEL_PATH + "tae_bayes.bin", cls);

	}

}