package ec.weka.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.converters.XRFFSaver;

public class DataLoadTest {
	
	public static void main(String[] args) throws Exception {
		
		ArffLoader loader = new ArffLoader();
		loader.setSource(new File("data/iris.arff"));
		Instances data = loader.getDataSet();
		System.out.println(data.classIndex());
		System.out.println("done");

		
		Instances data1 = DataSource.read("data/iris.arff");
		data1.setClassIndex(data1.numAttributes() - 1);
		System.out.println(data1.classIndex());
		System.out.println("class: " + data1.classAttribute().value((int) data1.instance(1).classValue()));			
		System.out.println("instance: " +data1.instance(1).toString() );
		System.out.println("instance attribute: " +data1.instance(1).attribute(0).toString() );
		System.out.println("instance attribute value: " + data1.instance(1).value(data1.attribute(0)) );
		
		System.out.println("labelled class attribure: " + data1.classAttribute().toString() );
		data1.setClassIndex(0);
		System.out.println("labelled class attribure after relabelling:" + data1.classAttribute().toString() );
		System.out.println("labelled class value of instance: " + data1.instance(1).classValue() );
		System.out.println("done");
		
		
		DataSource source = new DataSource("data/iris.arff");
		Instances data2 = source.getDataSet();
		System.out.println(data2.classAttribute().toString());
		System.out.println("done");
	
	}
}

/*
 // https://segmentfault.com/a/1190000010736347
  
  import weka.core.Instances; import weka.experiment.InstanceQuery;
  
  public class Test { public static void main(String[] args) throws Exception {
	  	InstanceQuery query = new InstanceQuery();
	  	query.setDatabaseURL("jdbc:mysql://localhost:3306/new_schema");
	  	query.setUsername("root"); query.setPassword(*******");
	  	query.setQuery("select * from iris"); 
	  	Instances data = query.retrieveInstances(); System.out.println("done"); 
  	  } 
  }
  
  
  import weka.core.Instances; import weka.core.converters.DatabaseLoader;

  public class Test { public static void main(String[] args) throws Exception {
  		DatabaseLoader loader = new DatabaseLoader();
  		loader.setSource("jdbc:mysql://localhost:3306/new_schema", "root", "*******"); 
  		loader.setQuery("select * from iris"); Instances data =loader.getDataSet(); 
  	 } 
  } 
  
  
  import weka.core.Instance; import
  weka.core.Instances; import weka.core.converters.DatabaseLoader;
  
  public class Test { public static void main(String[] args) throws Exception {
        DatabaseLoader loader = new DatabaseLoader();
  		loader.setSource("jdbc:mysql://localhost:3306/new_schema", "root", "zxy123456"); 
  		loader.setQuery("select  from iris"); 
  		Instances structure = loader.getStructure(); 
  		Instances data = new Instances(structure); 
  		Instance inst; 
  		while ((inst = loader.getNextInstance(structure)) != null)
  			data.add(inst); System.out.println("done"); 
  	} 
  }
  
  

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class Test {
    public static void main(String[] args) throws Exception {
        Instances data = DataSource.read("data/iris.arff");
        System.out.println(data);
        System.out.println("----------------");
        String[] options = new String[2];
        options[0] = "-R";
        options[1] = "1";
        Remove rm = new Remove();
        rm.setOptions(options);
        rm.setInputFormat(data);
        Instances inst1 = Filter.useFilter(data, rm);
        System.out.println(inst1);

    }
}

import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class Test {
    public static void main(String[] args) throws Exception {
        ArrayList<Attribute> atts;
        ArrayList<Attribute> attsRel;
        ArrayList<String> attVals;
        ArrayList<String> attValsRel;

        Instances data;
        Instances dataRel;

        double[] vals;
        double[] valsRel;
        int i = 0;

        atts = new ArrayList<Attribute>();
        atts.add(new Attribute("att1"));

        attVals = new ArrayList<String>();
        for (i = 0; i < 5; i++) {
            attVals.add("val" + (i + 1));
        }
        atts.add(new Attribute("att2", attVals));

        atts.add(new Attribute("att3", (ArrayList<String>) null));

        atts.add(new Attribute("att4", "yyyy-MM-dd"));

        attsRel = new ArrayList<Attribute>();
        attsRel.add(new Attribute("att5.1"));
        attValsRel = new ArrayList<String>();
        for (i = 0; i < 5; i++) {
            attValsRel.add("val5." + (i + 1));
        }
        attsRel.add(new Attribute("att5.2", attValsRel));
        dataRel = new Instances("att5", attsRel, 0);
        atts.add(new Attribute("att5", dataRel, 0));

        
        data=new Instances("MyRelation",atts,0);
        
        vals=new double[data.numAttributes()];
        vals[0]=Math.PI;
        vals[1]=attVals.indexOf("val3");
        vals[2]=data.attribute(2).addStringValue("a string");
        vals[3]=data.attribute(3).parseDate("2017-8-19");
        dataRel=new Instances(data.attribute(4).relation(),0);
        valsRel=new double[2];
        valsRel[0]=Math.PI+1;
        valsRel[1]=attValsRel.indexOf("val5.3");
        dataRel.add(new DenseInstance(1,valsRel));
        vals[4]=data.attribute(4).addRelation(dataRel);
        data.add(new DenseInstance(1,vals));
        System.out.println(data);
        
                
    }
}

import java.util.Random;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class Test {
    public static void main(String[] args) throws Exception {
        Instances data = DataSource.read("data\\iris.arff");
        System.out.println(data);

        long seed = 123456;
        Instances data3 = new Instances(data);
        data3.randomize(new Random(seed));
        System.out.println(data3);

    }
}

import java.util.Random;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Add;

public class Test {
    public static void main(String[] args) throws Exception {
        Instances data = DataSource.read("data\\weather.numeric.arff");
        Instances result = null;

        Add filter;
        result = new Instances(data);

        filter = new Add();
        filter.setAttributeIndex("last");
        filter.setAttributeName("NumericAttribute");
        filter.setInputFormat(result);
        result = Filter.useFilter(result, filter);

        filter = new Add();
        filter.setAttributeIndex("last");
        filter.setNominalLabels("A,B,C");
        filter.setAttributeName("NominalAttribute");
        filter.setInputFormat(result);
        result = Filter.useFilter(result, filter);

        Random rand = new Random(1234);
        for (int i = 0; i < result.numInstances(); i++) {
            result.instance(i).setValue(result.numAttributes() - 2,
                    rand.nextDouble());
            result.instance(i).setValue(result.numAttributes() - 1,
                    rand.nextInt(3));
        }

        System.out.println("è¿‡æ»¤å�Žçš„æ•°æ�®é›†ï¼š");
        System.out.println(result);

    }
}


import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Standardize;

public class Test {
    public static void main(String[] args) throws Exception {
        Instances train = DataSource.read("data\\segment-challenge.arff");
        Instances test = DataSource.read("data\\segment-test.arff");

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

package weka.api;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.Remove;

public class Test {
    public static void main(String[] args) throws Exception {
        Instances train = DataSource.read("data\\segment-challenge.arff");
        Instances test = DataSource.read("data\\segment-test.arff");

        train.setClassIndex(train.numAttributes() - 1);
        test.setClassIndex(test.numAttributes() - 1);
        if (!train.equalHeaders(test)) {
            throw new Exception("è®­ç»ƒé›†ä¸Žæµ‹è¯•æœºä¸�å…¼å®¹ï¼š\n" + train.equalHeadersMsg(test));
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
            System.out.print(", class: " + test.classAttribute()
                    .value((int) test.instance(i).classValue()));
            System.out.println(", predict class: "
                    + test.classAttribute().value((int) pred));
        }

    }
}
 */
