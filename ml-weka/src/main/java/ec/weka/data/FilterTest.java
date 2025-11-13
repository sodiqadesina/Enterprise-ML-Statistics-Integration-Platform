package ec.weka.data;


import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.Standardize;

public class FilterTest {
    public static void main(String[] args) throws Exception {
    	
    	System.out.println("======================test1===============================");
    	
        Instances train = DataSource.read("data/segment-challenge.arff");
        Instances test = DataSource.read("data/segment-test.arff");
        train.setClassIndex(train.numAttributes() - 1);
        test.setClassIndex(test.numAttributes() - 1);
        if (!train.equalHeaders(test)) {
            throw new Exception("training and test not compatiable\n" + train.equalHeadersMsg(test));
        }
        Remove rm = new Remove();
        rm.setAttributeIndices("1");
        J48 j48 = new J48();
        j48.setUnpruned(true);

        FilteredClassifier fc = new FilteredClassifier();
        fc.setFilter(rm);
        fc.setClassifier(j48);
        fc.buildClassifier(train);
        for (int i = 0; i < test.numInstances(); i++) {
            double pred = fc.classifyInstance(test.instance(i));
            System.out.print("index: " + (i + 1));
            System.out.print(", class: " + test.classAttribute().value((int) test.instance(i).classValue()));
            System.out.println(", predict class: " + test.classAttribute().value((int) pred));
        }
        
        System.out.println("======================test2===============================");
		train = DataSource.read("data/segment-challenge.arff");
		test = DataSource.read("data/segment-test.arff"); 
		Standardize filter = new Standardize();
		filter.setInputFormat(train);
		Instances newTrain = Filter.useFilter(train, filter);
		Instances newTest = Filter.useFilter(test, filter);
		System.out.println("new trainer");
		System.out.println(newTrain);
		System.out.println("new test");
		System.out.println(newTest);
		
    }
}



//import java.util.Random;
//import weka.core.Instances;
//import weka.core.converters.ConverterUtils.DataSource;
//import weka.filters.Filter;
//import weka.filters.unsupervised.attribute.Add;
//
//public class FilterTest {
//    public static void main(String[] args) throws Exception {
//        Instances data = DataSource.read("data/weather.numeric.arff");
//        Instances result = null;
//
//        Add filter;
//        result = new Instances(data);
//
//        filter = new Add();
//        filter.setAttributeIndex("last");
//        filter.setAttributeName("NumericAttribute");
//        filter.setInputFormat(result);
//        result = Filter.useFilter(result, filter);
//
//        filter = new Add();
//        filter.setAttributeIndex("last");
//        filter.setNominalLabels("A,B,C");
//        filter.setAttributeName("NominalAttribute");
//        filter.setInputFormat(result);
//        result = Filter.useFilter(result, filter);
//
//        System.out.println(result);
//        
//        Random rand = new Random(1234);
//        for (int i = 0; i < result.numInstances(); i++) {
//            result.instance(i).setValue(result.numAttributes() - 2,
//                    rand.nextDouble());
//            result.instance(i).setValue(result.numAttributes() - 1,
//                    rand.nextInt(3));
//        }
//        System.out.println(result);
//
//    }
//}