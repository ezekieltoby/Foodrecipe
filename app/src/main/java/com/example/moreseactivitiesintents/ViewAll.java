package com.example.moreseactivitiesintents;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

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
    }

    public void GetTextFromSQL(View v) {
        TextView tx1 = (TextView) findViewById(R.id.textView);
        TextView tx2 = (TextView) findViewById(R.id.textView2);
        TextView tx3 = (TextView) findViewById(R.id.textView3);
        TextView tx4 = (TextView) findViewById(R.id.textView4);
        try {
            ConnectionHelper connectionHelper = new ConnectionHelper();
            connect = connectionHelper.connectionclass();
            if(connect!=null) {
                String query = "Select * from BigStomach";
                Statement st= connect.createStatement();
                ResultSet rs= st.executeQuery(query);

                while(rs.next())
                {
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
        } catch (Exception ex)
        {
            Log.e("error", ex.getMessage());
        }

    }
}