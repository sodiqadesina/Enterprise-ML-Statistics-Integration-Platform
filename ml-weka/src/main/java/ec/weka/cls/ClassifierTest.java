package ec.weka.cls;

import weka.core.Instance;

/*
 *  How to use WEKA API in Java 
 *  Copyright (C) 2014 
 *  @author Dr Noureddin M. Sadawi (noureddin.sadawi@gmail.com)
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it as you wish ... 
 *  I ask you only, as a professional courtesy, to cite my name, web page 
 *  and my YouTube Channel!
 *  
 */

//import required classes
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.File;
import java.util.Random;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesUpdateable;
import weka.classifiers.trees.J48;
import weka.classifiers.functions.SMO;
import weka.classifiers.functions.SMOreg;
public class ClassifierTest {
	
	public static void main(String args[]) throws Exception{
		
    	System.out.println("==1===============Naive Bayes classifier===========================");
		DataSource source = new DataSource("data/iris.arff");
		Instances dataset = source.getDataSet();
		
		//set class index to the last attribute
		dataset.setClassIndex(dataset.numAttributes()-1);
		//create and build the classifier!
		NaiveBayes nb = new NaiveBayes();
		nb.buildClassifier(dataset);
		//print out capabilities
		System.out.println("capabilities of NB");
		System.out.println(nb.getCapabilities().toString());
		
		//loop through the new dataset and make predictions
		for (int i = 0; i < dataset.numInstances(); i++) {
			//get class double value for current instance
			double actualValue = dataset.instance(i).classValue();
			//get Instance object of current instance
			Instance newInst = dataset.instance(i);
		
			//call classifyInstance, which returns a double value for the class
			double predSMO = nb.classifyInstance(newInst);
			System.out.println(actualValue+", "+predSMO);
		}
		
		
		System.out.println("==2==========Naive Bayes classifier=========================");
		
	      ArffLoader loader = new ArffLoader();
	      loader.setFile(new File("data/weather.nominal.arff"));
	      Instances structure = loader.getStructure();
	      structure.setClassIndex(structure.numAttributes() - 1);
	
	      NaiveBayesUpdateable nb1 = new NaiveBayesUpdateable();
	      nb1.buildClassifier(structure);
	      Instance instance;
	      while ((instance = loader.getNextInstance(structure)) != null)
	          nb1.updateClassifier(instance);
	
	      System.out.println(nb1);
	     	
		System.out.println("==3==========Decision tree algorithm C4.5 (J48)=========================");
		SMO svm = new SMO();
		svm.buildClassifier(dataset);
		System.out.println(svm.getCapabilities().toString());
		
		String[] options = new String[4];
		options[0] = "-C"; options[1] = "0.11";
		options[2] = "-M"; options[3] = "3";
		J48 tree = new J48();
		tree.setOptions(options);
		tree.buildClassifier(dataset);
		System.out.println(" capabilities of J48");
		System.out.println(tree.getCapabilities().toString());
		System.out.println(tree.graph());
		
		
		
		System.out.println("===4=====Decision tree algorithm C4.5 (J48)=========================");
	      ArffLoader loader1=new ArffLoader();
	      loader1.setFile(new File("data/weather.nominal.arff"));
	      Instances data1=loader1.getDataSet();
	      data1.setClassIndex(data1.numAttributes()-1);
	      
	      String[] options1=new String[1];
	      options1[0]="-U";
	      J48 tree1 = new J48();
	      tree1.setOptions(options1);
	      tree1.buildClassifier(data1);
	      System.out.println(tree1);  
	     
		  System.out.println("===5=====Decision tree algorithm C4.5 (J48)=========================");
	      
	      Instances train = DataSource.read("data/segment-challenge.arff");
	      Instances test = DataSource.read("data/segment-test.arff");
	      train.setClassIndex(train.numAttributes() - 1);
	      test.setClassIndex(test.numAttributes() - 1);
	
	      if (!train.equalHeaders(test)) {
	          throw new Exception("");
	      }
	      J48 classifier = new J48();
	      classifier.buildClassifier(train);
	
	      for (int i = 0; i < test.numInstances(); i++) {
	          double pred = classifier.classifyInstance(test.instance(i));
	          double[] dist = classifier
	                  .distributionForInstance(test.instance(i));
	          System.out.print((i + 1) + " - "
	                  + test.instance(i).toString(test.classIndex()) + " - "
	                  + test.classAttribute().value((int) pred) + " - ");
	          if (pred != test.instance(i).classValue()) {
	              System.out.print("wrong");
	          } else {
	              System.out.print("correct");
	          }
	          System.out.println(" - " + Utils.arrayToString(dist));
	
	      }
	      
	      
	      System.out.println("===6=====Decision tree algorithm C4.5 (J48)=========================");
		
	      Instances data2 = DataSource.read("data/ionosphere.arff");
	      data2.setClassIndex(data2.numAttributes() - 1);
	      String[] options2 = new String[2];
	      String classname = "weka.classifiers.trees.J48";
	      options2[0] = "-C";
	      options2[1] = "0.25";
	      Classifier classifier2 = (Classifier) Utils.forName(Classifier.class,
	              classname, options2);
	
	      int seed = 1234;
	      int folds = 10;
	
	      Random rand = new Random(seed);
	      Instances newData = new Instances(data2);
	      newData.randomize(rand);
	      if (newData.classAttribute().isNominal()) {
	          newData.stratify(folds);
	      }
	      Evaluation eval = new Evaluation(newData);
	      for (int i = 0; i < folds; i++) {
	          Instances train2 = newData.trainCV(folds, i);
	          Instances test2 = newData.testCV(folds, i);
	          Classifier clsCopy = AbstractClassifier.makeCopy(classifier2);
	          clsCopy.buildClassifier(train2);
	          eval.evaluateModel(clsCopy, test2);
	      }
	      System.out.println("===classifer===");
	      System.out.println("Classifer:"+ Utils.toCommandLine(classifier2));
	      System.out.println("Dataset anme:" + data2.relationName());
	      System.out.println("Folds:" + folds);
	      System.out.println("Randowm seed:" + seed);
	      System.out.println();
	      System.out.println(eval.toSummaryString("=== " + folds, false));
	      
				
	}
}


