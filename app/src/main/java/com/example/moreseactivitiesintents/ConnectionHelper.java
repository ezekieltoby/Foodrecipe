package com.example.moreseactivitiesintents;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.security.Provider;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionHelper {
    Connection con;
    String uname, pass, ip, port, database;
@SuppressLint("NewApi")

    public Connection connectionclass()
    {

        ip= "10.0.2.2";
        database="FoodRecipe";
        uname="tyz";
        pass="tyz123";
        port="1433";
        //50150
//1433
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
         StrictMode.setThreadPolicy(policy);
       // Provider conscrypt = Conscrypt.newProviderBuilder().provideTrustManager(false).build();
        Connection connection= null;
        String ConnectionURL = null;
         try
         {
             Class.forName("net.sourceforge.jtds.jdbc.Driver");
            // ConnectionURL="jdbc:sqlserver://"+ ip + ":"+ port+";"+ "databaseName="+ database+";user="+uname+";password="+pass+";";
             ConnectionURL= "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;
             connection= DriverManager.getConnection(ConnectionURL, uname, pass);

         }
        catch (Exception exception){
             Log.e("Error database connecting connection helper class ", "something in there if you want "+ exception);
        }

        return connection;
    }
}
