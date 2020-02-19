
package gov.usda.nrcs.wcc.awdbWebService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for instantaneousDataFilter.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="instantaneousDataFilter"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="ALL"/&gt;
 *     &lt;enumeration value="FIRST_OF_DAY"/&gt;
 *     &lt;enumeration value="MIDNIGHT_ONLY"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "instantaneousDataFilter")
@XmlEnum
public enum InstantaneousDataFilter {

    ALL,
    FIRST_OF_DAY,
    MIDNIGHT_ONLY;

    public String value() {
        return name();
    }

    public static InstantaneousDataFilter fromValue(String v) {
        return valueOf(v);
    }

}
