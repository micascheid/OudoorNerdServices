package edu.western.JavaApp2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class PushStationName {

    public String stationName;
    public String triplet;
    public Float latitude;
    public Float longitude;
    public Statement stmt;

    public PushStationName(String triplet, String stationName, Float latitude, Float longitude){
        this.triplet = triplet;
        this.stationName = stationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public void pushName(){
        try {


            // Creating database connection
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://outsidenerd.com:3306/WeatherData", //url & db
                    "micalinscheid", //MySQL username
                    "studly1997G*");   //MySQL password


            stmt = con.createStatement();

            // create a sql date object so we can use it in our INSERT statement

            //the mysql insert statement
            String query = "insert into StationsInfo (stationName, triplet, latitude, longitude)"
                    + "values (?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, stationName);
            preparedStmt.setString(2, triplet);
            preparedStmt.setFloat(3, latitude);
            preparedStmt.setFloat(4, longitude);
            preparedStmt.execute();


            //close
            con.close();

        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

        }


}


