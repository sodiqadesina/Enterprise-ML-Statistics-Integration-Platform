package ec.weka.cls;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Debug;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

/**
 *
 * @author Taha Emara 
 * Website: http://www.emaraic.com 
 * Email : taha@emaraic.com
 * Created on: Jul 1, 2017
 * Github link: https://github.com/emara-geek/weka-example
 */
public class AnnClassifierTest {

    public static final String DATASETPATH = "data/iris.2D.arff";
    public static final String MODElPATH = "model/iris2D_ann.bin";

    public static void main(String[] args) throws Exception {
        
        AnnGenerator mg = new AnnGenerator();

        Instances dataset = mg.loadDataset(DATASETPATH);

        Filter filter = new Normalize();

        // divide dataset to train dataset 80% and test dataset 20%
        int trainSize = (int) Math.round(dataset.numInstances() * 0.8);
        int testSize = dataset.numInstances() - trainSize;

        dataset.randomize(new Debug.Random(1));// if you comment this line the accuracy of the model will be droped from 96.6% to 80%
        
        //Normalize dataset
        filter.setInputFormat(dataset);
        Instances datasetnor = Filter.useFilter(dataset, filter);

        Instances traindataset = new Instances(datasetnor, 0, trainSize);
        Instances testdataset = new Instances(datasetnor, trainSize, testSize);

        // build classifier with train dataset             
        MultilayerPerceptron ann = (MultilayerPerceptron) mg.buildClassifier(traindataset);

        // Evaluate classifier with test dataset
        String evalsummary = mg.evaluateModel(ann, traindataset, testdataset);
        System.out.println("Evaluation: " + evalsummary);

        //Save model 
        mg.saveModel(ann, MODElPATH);

        //classifiy a single instance 
        AnnClassifier cls = new AnnClassifier();
        String classname =cls.classifiy(Filter.useFilter(cls.createInstance(1.6, 0.2, 0), filter), MODElPATH);
        System.out.println("\n The class name for the instance with petallength = 1.6 and petalwidth =0.2 is  " +classname);

    }

}
