package ec.rs;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import ec.stats.sb.StatsStatelessLocal;


@Path("/rest")
@RequestScoped
public class StatsRS {

    // Using JNDI name to avoid ambiguity
    @EJB(lookup = "java:global/stats-ear/ec.asgmt-stats-ejb-0.2.0/StatsStateless!ec.stats.sb.StatsStatelessLocal")
    private StatsStatelessLocal statsBean;

    
    @GET
    @Path("/max")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMax() {
        return "{\"max\":\"" + statsBean.getMax() + "\"}";
    }

    @GET
    @Path("/min")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMin() {
        return "{\"min\":\"" + statsBean.getMin() + "\"}";
    }

    @GET
    @Path("/mean")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMean() {
        return "{\"mean\":\"" + statsBean.getMean() + "\"}";
    }

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCount() {
        return "{\"count\":\"" + statsBean.getCount() + "\"}";
    }

    @GET
    @Path("/std")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSTD() {
        return "{\"std\":\"" + statsBean.getSTD() + "\"}";
    }
}
