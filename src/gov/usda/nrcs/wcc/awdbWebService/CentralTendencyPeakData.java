
package gov.usda.nrcs.wcc.awdbWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for centralTendencyPeakData complex type.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="centralTendencyPeakData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://www.wcc.nrcs.usda.gov/ns/awdbWebService}averagesPeakData"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="centralTendencyType" type="{http://www.wcc.nrcs.usda.gov/ns/awdbWebService}centralTendencyType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "centralTendencyPeakData", propOrder = {
    "centralTendencyType"
})
public class CentralTendencyPeakData
    extends AveragesPeakData
{

    @XmlSchemaType(name = "string")
    protected CentralTendencyType centralTendencyType;

    /**
     * Gets the value of the centralTendencyType property.
     * 
     * @return
     *     possible object is
     *     {@link CentralTendencyType }
     *     
     */
    public CentralTendencyType getCentralTendencyType() {
        return centralTendencyType;
    }

    /**
     * Sets the value of the centralTendencyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link CentralTendencyType }
     *     
     */
    public void setCentralTendencyType(CentralTendencyType value) {
        this.centralTendencyType = value;
    }

}
