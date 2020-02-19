package edu.western.JavaApp2;

import gov.usda.nrcs.wcc.awdbWebService.*;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static int count;
    public static List<String>stationsWantedList = new ArrayList<>();

    public static void main(String[] args) {
//        AWDB awdb = new AWDB();
//        AwdbWebService service = awdb.get_webService();

//        List<String> pList = new ArrayList<>();
//
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");pList.add("737:CO:SNTL");
//        String today = dateFormat.format(new Date());
//        Calendar hoursAgo = GregorianCalendar.getInstance();
//        hoursAgo.add(Calendar.HOUR, -7);
//        String hourssAgo = dateFormat.format(hoursAgo.getTime());
//
//        List<InstantaneousData> test = service.getInstantaneousData(pList,"SNWD",1,null,hourssAgo,today,InstantaneousDataFilter.ALL,UnitSystem.ENGLISH);
//
//        System.out.println(test.get(0).getValues().get(1).getValue());
//
//        Data[] values = service.getData(pList,
//                "SNWD", 1, null, Duration.DAILY, true, hourssAgo, today, true)
//                .toArray(new Data[0]);
//
//        System.out.println(values[0].getValues().);


        System.out.println("Automation Starting");
        stationsWantedList = stationsWanted();
        //populateStationInfo();
        updateStationMetaData();
    }

    public static List<String> stationsWanted(){
        List<String> stations = new ArrayList<>();
        String[] stationsArray = {"701:CO:SNTL","1100:CO:SNTL","680:CO:SNTL","542:CO:SNTL","1141:CO:SNTL",
                "380:CO:SNTL","737:CO:SNTL","618:CO:SNTL","669:CO:SNTL"};

        for (String temp : stationsArray){
            stations.add(temp);
        }

        return stations;
    }

    public static void updateStationMetaData() {
        AWDB awdb = new AWDB();
        AwdbWebService service = awdb.get_webService();

        for (String temp : stationsWantedList){
            String[] vals = awdb.LastHour(temp, service);
            PushData pushData = new PushData(service.getStationMetadata(temp).getName(),temp,vals[0],vals[1],vals[2],vals[3],vals[4]);
            pushData.pushData();
        }
    }

    public static void populateStationInfo() {
        List<String> allStations;
        List<String> sntlStations = new ArrayList<>();
        List<StationMetaData> metaData = new ArrayList<>();
        List<String> sntlNames = new ArrayList<>();
        List<Float> sntlLatitude = new ArrayList<>();
        List<Float> sntlLongitude = new ArrayList<>();
        List<String> networkCds = Arrays.asList("SNTL");
        List<String> stateCds = Arrays.asList("CO");



        AWDB awdb = new AWDB();
        AwdbWebService service = awdb.get_webService();
//        allStations = service.getStations(null,stateCds,networkCds,null,null,
//                null,null,null,null,null,null,
//                null,null, null, true);



//        for (String s: allStations) {
//            System.out.println(s);
//
//        }


//      Fill in sntlStations with only SNTL sites
//        for (String s: allStations) {
//            if (s.contains("SNTL")){
//                sntlStations.add(s);
//            }
//        }

//      Grab metadata for stations using triplets
//      This should take a long time. Run over night! But for now try 4 stations

        for (String temp : stationsWantedList){
            metaData.add(service.getStationMetadata(temp));
        }


//        add all names and coordinates
        for (int i = 0; i < metaData.size(); i++){
            sntlNames.add(metaData.get(i).getName());
            sntlLatitude.add(metaData.get(i).getLatitude().floatValue());
            sntlLongitude.add(metaData.get(i).getLongitude().floatValue());
        }

        //This will automatically build sntl stations table in the database
        System.out.println(metaData.size());
        for (int i = 0; i < metaData.size(); i++){
            PushStationName push = new PushStationName(stationsWantedList.get(i), sntlNames.get(i),
                    sntlLatitude.get(i), sntlLongitude.get(i));
            push.pushName();

        }
    }
}
