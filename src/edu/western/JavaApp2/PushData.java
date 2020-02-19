package edu.western.JavaApp2;
import java.sql.*;

import java.sql.*;

public class PushData {
    public String stationName;
    public String triplet;
    public String dateTime;
    public String temp;
    public String windA;
    public String snowD;
    public String waterEq;

    public Statement stmt;
    public ResultSet rs;


    public PushData(String stationName, String triplet, String dateTime, String temp, String windA, String snowD, String waterEq){
        this.stationName = stationName;
        this.triplet = triplet;
        this.dateTime = dateTime;
        this.temp = temp;
        this.windA = windA;
        this.snowD = snowD;
        this.waterEq = waterEq;

    }


    public void pushData(){
        try
        {
            // Creating database connection
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://outsidenerd.com:3306/WeatherData", //url & db
                    "micalinscheid", //MySQL username
                    "studly1997G*");   //MySQL password


            stmt = con.createStatement();

            // create a sql date object so we can use it in our INSERT statement

            //the mysql insert statement
            String query = "insert into AllStations (stationName, triplet, dateTime, temp, windA, snowD, waterEq)"
                    + " values (?, ?, ?, ?, ?, ?, ?)";

            // create the mysql insert preparedstatement
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString(1, stationName);
            preparedStmt.setString(2,triplet);
            preparedStmt.setString(3, dateTime);
            preparedStmt.setString(4, temp);
            preparedStmt.setString(5, windA);
            preparedStmt.setString(6, snowD);
            preparedStmt.setString(7, waterEq);
            preparedStmt.execute();


            //Basic Query from database


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
