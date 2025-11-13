package ec.stats.sb;

import javax.ejb.Local;

@Local
public interface LRStatelessLocal {
    double prediction(double[] inputValues) throws Exception;
}
