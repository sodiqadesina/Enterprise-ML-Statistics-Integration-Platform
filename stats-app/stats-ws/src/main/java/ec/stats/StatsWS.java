package ec.stats;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface StatsWS {
    @WebMethod double getCount();
    @WebMethod double getMin();
    @WebMethod double getMax();
    @WebMethod double getMean();
    @WebMethod double getSTD();
}
