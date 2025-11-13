package ec.stats;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import ec.stats.sb.StatsStatelessLocal;

@WebService(endpointInterface = "ec.stats.StatsWS")
public class StatsWSImpl implements StatsWS {
    
    @EJB(lookup = "java:global/stats-ear/ec.asgmt-stats-ejb-0.2.0/StatsStateless!ec.stats.sb.StatsStatelessLocal")
    private StatsStatelessLocal statsBean;

    @WebMethod
    public double getCount() {
        return statsBean.getCount();
    }

    @WebMethod
    public double getMin() {
        return statsBean.getMin();
    }

    @WebMethod
    public double getMax() {
        return statsBean.getMax();
    }

    @WebMethod
    public double getMean() {
        return statsBean.getMean();
    }

    @WebMethod
    public double getSTD() {
        return statsBean.getSTD();
    }
}
