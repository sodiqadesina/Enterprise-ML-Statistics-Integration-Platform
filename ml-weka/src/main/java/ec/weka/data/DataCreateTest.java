package ec.weka.data;

import java.util.ArrayList;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

public class DataCreateTest {
	
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
