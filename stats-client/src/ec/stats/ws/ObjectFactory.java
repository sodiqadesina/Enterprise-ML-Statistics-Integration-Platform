
package ec.stats.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ec.stats.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetMean_QNAME = new QName("http://stats.ec/", "getMean");
    private final static QName _GetCount_QNAME = new QName("http://stats.ec/", "getCount");
    private final static QName _GetCountResponse_QNAME = new QName("http://stats.ec/", "getCountResponse");
    private final static QName _GetMaxResponse_QNAME = new QName("http://stats.ec/", "getMaxResponse");
    private final static QName _GetMeanResponse_QNAME = new QName("http://stats.ec/", "getMeanResponse");
    private final static QName _GetMinResponse_QNAME = new QName("http://stats.ec/", "getMinResponse");
    private final static QName _GetMin_QNAME = new QName("http://stats.ec/", "getMin");
    private final static QName _GetSTDResponse_QNAME = new QName("http://stats.ec/", "getSTDResponse");
    private final static QName _GetSTD_QNAME = new QName("http://stats.ec/", "getSTD");
    private final static QName _GetMax_QNAME = new QName("http://stats.ec/", "getMax");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ec.stats.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCountResponse }
     * 
     */
    public GetCountResponse createGetCountResponse() {
        return new GetCountResponse();
    }

    /**
     * Create an instance of {@link GetMaxResponse }
     * 
     */
    public GetMaxResponse createGetMaxResponse() {
        return new GetMaxResponse();
    }

    /**
     * Create an instance of {@link GetMeanResponse }
     * 
     */
    public GetMeanResponse createGetMeanResponse() {
        return new GetMeanResponse();
    }

    /**
     * Create an instance of {@link GetMinResponse }
     * 
     */
    public GetMinResponse createGetMinResponse() {
        return new GetMinResponse();
    }

    /**
     * Create an instance of {@link GetMin }
     * 
     */
    public GetMin createGetMin() {
        return new GetMin();
    }

    /**
     * Create an instance of {@link GetSTDResponse }
     * 
     */
    public GetSTDResponse createGetSTDResponse() {
        return new GetSTDResponse();
    }

    /**
     * Create an instance of {@link GetSTD }
     * 
     */
    public GetSTD createGetSTD() {
        return new GetSTD();
    }

    /**
     * Create an instance of {@link GetMax }
     * 
     */
    public GetMax createGetMax() {
        return new GetMax();
    }

    /**
     * Create an instance of {@link GetMean }
     * 
     */
    public GetMean createGetMean() {
        return new GetMean();
    }

    /**
     * Create an instance of {@link GetCount }
     * 
     */
    public GetCount createGetCount() {
        return new GetCount();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stats.ec/", name = "getMean")
    public JAXBElement<GetMean> createGetMean(GetMean value) {
        return new JAXBElement<GetMean>(_GetMean_QNAME, GetMean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCount }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stats.ec/", name = "getCount")
    public JAXBElement<GetCount> createGetCount(GetCount value) {
        return new JAXBElement<GetCount>(_GetCount_QNAME, GetCount.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCountResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stats.ec/", name = "getCountResponse")
    public JAXBElement<GetCountResponse> createGetCountResponse(GetCountResponse value) {
        return new JAXBElement<GetCountResponse>(_GetCountResponse_QNAME, GetCountResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMaxResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stats.ec/", name = "getMaxResponse")
    public JAXBElement<GetMaxResponse> createGetMaxResponse(GetMaxResponse value) {
        return new JAXBElement<GetMaxResponse>(_GetMaxResponse_QNAME, GetMaxResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMeanResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stats.ec/", name = "getMeanResponse")
    public JAXBElement<GetMeanResponse> createGetMeanResponse(GetMeanResponse value) {
        return new JAXBElement<GetMeanResponse>(_GetMeanResponse_QNAME, GetMeanResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMinResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stats.ec/", name = "getMinResponse")
    public JAXBElement<GetMinResponse> createGetMinResponse(GetMinResponse value) {
        return new JAXBElement<GetMinResponse>(_GetMinResponse_QNAME, GetMinResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stats.ec/", name = "getMin")
    public JAXBElement<GetMin> createGetMin(GetMin value) {
        return new JAXBElement<GetMin>(_GetMin_QNAME, GetMin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSTDResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stats.ec/", name = "getSTDResponse")
    public JAXBElement<GetSTDResponse> createGetSTDResponse(GetSTDResponse value) {
        return new JAXBElement<GetSTDResponse>(_GetSTDResponse_QNAME, GetSTDResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSTD }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stats.ec/", name = "getSTD")
    public JAXBElement<GetSTD> createGetSTD(GetSTD value) {
        return new JAXBElement<GetSTD>(_GetSTD_QNAME, GetSTD.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetMax }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://stats.ec/", name = "getMax")
    public JAXBElement<GetMax> createGetMax(GetMax value) {
        return new JAXBElement<GetMax>(_GetMax_QNAME, GetMax.class, null, value);
    }

}
