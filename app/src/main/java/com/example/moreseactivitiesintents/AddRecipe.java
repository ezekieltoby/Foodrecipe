
package com.example.moreseactivitiesintents;

        import androidx.appcompat.app.AppCompatActivity;

        import android.annotation.SuppressLint;
        import android.content.Intent;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.os.StrictMode;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.PreparedStatement;
        import java.sql.Statement;

public class AddRecipe extends AppCompatActivity {
    Connection connect;
    EditText name, time, category, ingredients;
    Button submit;
    TextView status;


    Connection con;
    Statement stmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addrecipe);

        name = (EditText) findViewById(R.id.name);
        time = (EditText) findViewById(R.id.time);
        category = (EditText) findViewById(R.id.category);
        ingredients = (EditText) findViewById(R.id.ingredients);
        submit = (Button) findViewById(R.id.submitbtn);
        status = (TextView)findViewById(R.id.statusbtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
    new AddRecipe.submitrecipe().execute("");
            }
        });
    }

    public class submitrecipe  extends AsyncTask<String, String , String> {
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            status.setText("sending dat to database");

        }

        @Override
        protected void onPostExecute(String s) {
            status.setText("Registration is successful");
            name.setText("");
            time.setText("");
            category.setText("");
            ingredients.setText("");
        }

        @Override
        protected String doInBackground(String... strings) {
            try{
                com.example.moreseactivitiesintents.ConnectionHelper connectionHelper = new com.example.moreseactivitiesintents.ConnectionHelper();
                Log.i("inserts ", "before connect");
                connect = connectionHelper.connectionclass();
                Log.d("inserts ", "after connect");

                if(connect == null) {
                    z = "Check Your Internet Connection";
                }
                else{
                    String sql = "INSERT INTO BigStomach (FoodName, CookingTime, Category, Recipe) VALUES ('"+name.getText()+"','"+time.getText()+"','"+category.getText()+"','"+ingredients.getText()+"')";
                    stmt = connect.createStatement();
                    stmt.executeUpdate(sql);
                    Log.i("checks", "doInBackground: " + sql);
                }

            }catch (Exception e){
                isSuccess = false;
                z = e.getMessage();
                Log.e("checks", "doInBackground: " + z);
            }

            return z;
        }
    }
        @SuppressLint("NewApi")
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


}
