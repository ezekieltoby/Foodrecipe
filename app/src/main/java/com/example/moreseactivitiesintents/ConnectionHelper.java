package com.example.moreseactivitiesintents;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    String uname, pass, ip, port, database;
@SuppressLint("NewApi")

    public Connection connectionclass()
    {

        ip= "192.168.1.237";
        database="FoodRecipe";
        uname="tyz";
        pass="tyz123";
        port="1433";
        //50150
//1433
      //  StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       // StrictMode.setThreadPolicy(policy);
        Connection connection= null;
        String ConnectionURL =null;
         try
         {
             //Class.forName("net.sourceforge.jtds.jdbc.Driver");
             ConnectionURL="jdbc:sqlserver://"+ ip + ":"+ port+";"+ "databaseName="+ database+";user="+uname+";password="+pass+";";
             connection= DriverManager.getConnection(ConnectionURL);

         }
        catch (Exception exception){
             Log.e("Error database connecting ", exception.getMessage());
        }

        return connection;
    }
}
