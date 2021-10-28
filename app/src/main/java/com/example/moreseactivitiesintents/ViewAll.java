package com.example.moreseactivitiesintents;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewAll extends AppCompatActivity {
    Connection connect;
    String ConnectionResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewall);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);

    }

    public void GetTextFromSQL(View v) {
      // if (true){ throw new Error();}
        TextView tx1 = (TextView) findViewById(R.id.textView);
        TextView tx2 = (TextView) findViewById(R.id.textView2);
        TextView tx3 = (TextView) findViewById(R.id.textView3);
        TextView tx4 = (TextView) findViewById(R.id.textView4);
        try  {

            ConnectionHelper connectionHelper = new ConnectionHelper();
            Log.i("checks ", "before connect");
            connect = connectionHelper.connectionclass();
            Log.d("checks ", "after connect");

            if(connect!=null) {
                String query = "Select * from dbo.BigStomach";
                Statement st= connect.createStatement();
                ResultSet rs= st.executeQuery(query);
                Log.i("checks ", "zeke");

                while(rs.next())
                {
                    Log.i("checks ", rs.getString(1 ));
                    tx1.setText(rs.getString(1));
                    tx2.setText(rs.getString(2));
                    tx3.setText(rs.getString(3));
                    tx4.setText(rs.getString(4));
                }
            }
            else
            {
                ConnectionResult="Check Connection";
            }

        } catch (Exception e) {
            Log.e("error database file ", "on view all " + e.getMessage() );
            e.printStackTrace();
        }




    }
}