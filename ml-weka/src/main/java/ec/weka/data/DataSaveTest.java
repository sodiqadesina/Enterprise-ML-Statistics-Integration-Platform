package ec.weka.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSink;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.converters.XRFFSaver;

public class DataSaveTest {
	public static void main(String[] args) {
		try {
			Instances data = DataSource.read("data/iris.arff");
			DataSink.write("data/write_iris.csv", data);

			FileOutputStream arff = new FileOutputStream("data/write_iris.arff");
			DataSink.write(arff, data);
			arff.close();

			XRFFSaver saver = new XRFFSaver();
			saver.setInstances(data);
			saver.setFile(new File("data/write_iris.xrff"));
			saver.writeBatch();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("done");

	}
}

// https://segmentfault.com/a/1190000010736347
  
//import weka.core.Instances;
//import weka.core.converters.ConverterUtils.DataSource;
//import weka.core.converters.DatabaseSaver;
//
//public class Test {
//    public static void main(String[] args) throws Exception {
//        Instances data = DataSource.read("data/iris.arff");
//        DatabaseSaver saver = new DatabaseSaver();
//        saver.setDestination("jdbc:mysql://localhost:3306/new_schema", "root",
//                "zxy123456");
//        saver.setTableName("write_iris");
//        saver.setRelationForTableName(false);
//        saver.setInstances(data);
//        saver.writeBatch();
//        System.out.println("done");
//
//    }
//}
//
//
//import weka.core.Instances;
//import weka.core.converters.ConverterUtils.DataSource;
//import weka.core.converters.DatabaseSaver;
//
//public class Test {
//    public static void main(String[] args) throws Exception {
//        Instances data = DataSource.read("data/iris.arff");
//        DatabaseSaver saver = new DatabaseSaver();
//        saver.setDestination("jdbc:mysql://localhost:3306/new_schema", "root",
//                "zxy123456");
//        saver.setTableName("write_iris");
//        saver.setRelationForTableName(false);
//
//        saver.setRetrieval(DatabaseSaver.INCREMENTAL);
//        saver.setInstances(data);
//        for (int i = 0; i < data.numInstances(); i++) {
//            saver.writeIncremental(data.instance(i));
//        }
//        saver.writeIncremental(null);
//        System.out.println("done");
//
//    }
//}


