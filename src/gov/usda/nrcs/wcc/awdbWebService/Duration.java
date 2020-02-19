
package gov.usda.nrcs.wcc.awdbWebService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for duration.
 * 
 * <p>The following schema fragment specifies the expected         content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="duration"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="DAILY"/&gt;
 *     &lt;enumeration value="MONTHLY"/&gt;
 *     &lt;enumeration value="SEMIMONTHLY"/&gt;
 *     &lt;enumeration value="WATER_YEAR"/&gt;
 *     &lt;enumeration value="CALENDAR_YEAR"/&gt;
 *     &lt;enumeration value="HOURLY"/&gt;
 *     &lt;enumeration value="SEASONAL"/&gt;
 *     &lt;enumeration value="ANNUAL"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "duration")
@XmlEnum
public enum Duration {

    DAILY,
    MONTHLY,
    SEMIMONTHLY,
    WATER_YEAR,
    CALENDAR_YEAR,
    HOURLY,
    SEASONAL,
    ANNUAL;

    public String value() {
        return name();
    }

    public static Duration fromValue(String v) {
        return valueOf(v);
    }

}
