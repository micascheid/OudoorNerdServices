package edu.western.JavaApp2;

import java.math.*;
import gov.usda.nrcs.wcc.awdbWebService.AwdbWebService;
import gov.usda.nrcs.wcc.awdbWebService.AwdbWebService_Service;
import gov.usda.nrcs.wcc.awdbWebService.HourlyData;
import gov.usda.nrcs.wcc.awdbWebService.HourlyDataValue;

import javax.swing.text.DateFormatter;
import javax.xml.namespace.QName;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class AWDB {

    public AwdbWebService m_webService;
    public int smallest = 10;


    public AwdbWebService get_webService(){
        try
        {
            URL wsURL = new URL("https://www.wcc.nrcs.usda.gov/awdbWebService/services?wsdl");
            AwdbWebService_Service lookup = new AwdbWebService_Service(wsURL, new
                    QName("http://www.wcc.nrcs.usda.gov/ns/awdbWebService", "AwdbWebService"));
            m_webService  = lookup.getAwdbWebServiceImplPort();

        }
        catch (Exception e)
        {
            System.out.println("failed to make instance" + e.getMessage());
        }

//        List<HourlyData> testV2 = HourlyPull("737:CO:SNTL", m_webService);
//        ArrayList<String[]> tobs = new ArrayList<>();
//
//        for (int i = 0; i < testV2.get(0).getValues().size(); i++) {
//            String val = Integer.toString(testV2.get(0).getValues().get(i).getValue().intValue());
//            String time = testV2.get(0).getValues().get(i).getDateTime();
//            String vt[] = {val, time};
//            tobs.add(vt);
//            //tobs.add(testV2.get(0).getValues().get(i).getDateTime());
//            //System.out.println(testV2.get(0).getValues().get(i).getDateTime());
//
//        }
//
//        toString(tobs);

        return m_webService;

    }

    public String[] LastHour(String p_stationTriplet, AwdbWebService service){

        String[] vals = new String[5];
        int size1;
        int size2;
        int size3;
        int size4;
        int small;
        String temp;
        String windA;
        String snowD;
        String waterEq;
        String val1DateEndS;
        Date val1Date;

        ArrayList<Integer> sizes =  new ArrayList<>();

        Date test1;
        Date test2;


        //The following will take the current time and take us 5 hours into the past to
        //query data. We will then take the last value in the list to take as our query.
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = dateFormat.format(new Date());
        Calendar hoursAgo = GregorianCalendar.getInstance();
        hoursAgo.add(Calendar.HOUR, -7);
        String hourssAgo = dateFormat.format(hoursAgo.getTime());
        List<String> stationTripletListSingle =  new ArrayList<>();
        stationTripletListSingle.add(p_stationTriplet);

        List<HourlyData> values1 = m_webService.getHourlyData(stationTripletListSingle,
                "TOBS", 1, null, hourssAgo, today,
                null, null);


        List<HourlyData> values2 = m_webService.getHourlyData(stationTripletListSingle,
                "WSPDV", 1, null, hourssAgo, today,
                null, null);


        List<HourlyData> values3 = m_webService.getHourlyData(stationTripletListSingle,
                "SNWD", 1, null, hourssAgo, today,
                null, null);

        List<HourlyData> values4 = m_webService.getHourlyData(stationTripletListSingle,
                "WTEQ", 1, null, hourssAgo, today,
                null, null);


        size1 = values1.get(0).getValues().size();
        size2 = values2.get(0).getValues().size();
        size3 = values3.get(0).getValues().size();
        size4 = values4.get(0).getValues().size();

        sizes.add(size1);
        sizes.add(size2);
        sizes.add(size3);
        sizes.add(size4);

        small = smallest(sizes)-1;
        System.out.println("small: " + small);

        //date
        vals[0] = values1.get(0).getValues().get(small).getDateTime();

        //temp
        if (sizes.get(0) == 0) {
            vals[1] = "null";
        } else {
            temp = String.valueOf(values1.get(0).getValues().get(small).getValue().intValue());
            vals[1] = temp;
        }

        //wind avg
        if (sizes.get(1) == 0) {
            vals[2] = "null";
        }else {
            windA = String.valueOf(values2.get(0).getValues().get(small).getValue().intValue());
            vals[2] = windA;
        }

        //snow depth
        if (sizes.get(2) == 0) {
            vals[3] = "null";
        }else {
            snowD = String.valueOf(values3.get(0).getValues().get(small).getValue().intValue());
            vals[3] = snowD;
        }

        //water equivalence
        if (sizes.get(3) == 0) {
            vals[4] = "null";
        }else {
            waterEq = String.valueOf(values4.get(0).getValues().get(small).getValue().intValue());
            vals[4] = waterEq;
        }

        return vals;
    }

    public static void toString(ArrayList<String[]> vals){

        for (String[] f: vals) {
            System.out.println(f[0] + " " + f[1]);
        }
    }


    public Integer smallest(ArrayList<Integer> sizes) {
        for (int i = 0; i < sizes.size(); i++){
            for (int j = 0; j < sizes.size()-1; j++){
                int smalltest = Math.min(sizes.get(i), sizes.get(j+1));
                if (smalltest < smallest && smalltest > 0){
                    smallest = smalltest;
                }
            }
        }
        System.out.println(smallest);
        return smallest;
    }
}






