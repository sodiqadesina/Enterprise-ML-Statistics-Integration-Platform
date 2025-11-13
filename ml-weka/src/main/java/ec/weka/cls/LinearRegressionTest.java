package ec.weka.cls;

import java.util.Arrays;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ConverterUtils.DataSource;


public class LinearRegressionTest {

	public static void main(String[] args) throws Exception {

		//training dataset
		Instances trainingDataSet = DataSource.read("data1/house.arff");
		Instances testingDataSet = DataSource.read("data1/house_test.arff");
		Instances predicationDataSet = DataSource.read("data1/house_unknown.arff");
		
		trainingDataSet.setClassIndex(trainingDataSet.numAttributes()-1);
		testingDataSet.setClassIndex(testingDataSet.numAttributes()-1);	
		 predicationDataSet.setClassIndex(testingDataSet.numAttributes()-1);	
		
		//linear regression algorithm setting and configure
		LinearRegression cls = new LinearRegression();
		
		// set attribute selection method, no method
		SelectedTag method = new SelectedTag(LinearRegression.SELECTION_NONE, LinearRegression.TAGS_SELECTION);
		cls.setAttributeSelectionMethod(method); 
		//cls.setEliminateColinearAttributes(false);
		
		//training the model
		cls.buildClassifier(trainingDataSet);
		System.out.println(cls);
		
	    //get coefficients of the linear regression model
		double[] lmCoeffs = cls.coefficients();
		System.out.println(Arrays.toString(lmCoeffs));
	
		// model testing
		Evaluation eval = new Evaluation(trainingDataSet);
		eval.evaluateModel(cls, testingDataSet);
		System.out.println("Linear Regression Model Evaluation");
		System.out.println(eval.toSummaryString());

		//predict
		Instance predicationDataInstance = predicationDataSet.lastInstance();
		double value = cls.classifyInstance(predicationDataInstance);
		System.out.println(value);
		
	    // label instances
	    for (int i = 0; i < predicationDataSet.numInstances(); i++) {
	      double clsLabel = cls.classifyInstance(predicationDataSet.instance(i));
	      System.out.println(predicationDataSet.instance(i));
	      predicationDataSet.instance(i).setClassValue(clsLabel);
	    }
	    System.out.println(predicationDataSet.toString());
		
		//save model
		weka.core.SerializationHelper.write("model/house_regression.bin", cls);
		//load model
		Classifier cls1 = (Classifier) weka.core.SerializationHelper.read("model/house_regression.bin");
		value = cls1.classifyInstance(predicationDataInstance);
		System.out.println(value);
		
	}
	
}