package ec.stats.sb;

import weka.classifiers.Classifier;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Attribute;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

@Stateless
public class LRStateless implements LRStatelessLocal {

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    @Override
    public double prediction(double[] inputValues) throws Exception {
        // Fetch the model data as byte[] directly
        TypedQuery<byte[]> query = em.createQuery(
                "SELECT m.object FROM Model m WHERE m.name = :modelName",
                byte[].class);
        query.setParameter("modelName", "weka-lr");
        byte[] modelData = query.getSingleResult();

        // Deserialize the Weka model from byte array
        Classifier model;
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(modelData))) {
            model = (Classifier) ois.readObject();
        }

        // Prepare the Weka Instances structure
        Instances structure = createStructureForPrediction();
        Instance instance = new DenseInstance(structure.numAttributes());
        instance.setDataset(structure);

        // Set values for the instance
        for (int i = 0; i < inputValues.length; i++) {
            instance.setValue(i, inputValues[i]);
        }

        // Perform prediction
        return model.classifyInstance(instance);
    }

    private Instances createStructureForPrediction() {
        ArrayList<Attribute> houseAttributes = new ArrayList<>();
        houseAttributes.add(new Attribute("houseSize"));
        houseAttributes.add(new Attribute("lotSize"));
        houseAttributes.add(new Attribute("bedrooms"));
        houseAttributes.add(new Attribute("granite"));
        houseAttributes.add(new Attribute("bathroom"));
        houseAttributes.add(new Attribute("sellingPrice")); // Target attribute
        return new Instances("HousePredict", houseAttributes, 0);
    }
}
