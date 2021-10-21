
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
                con = connectionClass(ConnectionClass.un.toString(),ConnectionClass.pass.toString(),ConnectionClass.db.toString(),ConnectionClass.ip.toString());
                if(con == null){
                    z = "Check Your Internet Connection";
                }
                else{
                    String sql = "INSERT INTO BigStomach (FoodName, CookingTime, Category, Recipe) VALUES ('"+name.getText()+"','"+time.getText()+"','"+category.getText()+"','"+ingredients.getText()+"')";
                    stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                }

            }catch (Exception e){
                isSuccess = false;
                z = e.getMessage();
            }

            return z;
        }
    }
        @SuppressLint("NewApi")
    public Connection connectionClass(String user, String password, String database, String server) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + server+"/" + database +  ";name=" + name + ";time=" + time + "category" + category + "ingredients" + ingredients + ";";
            connection = DriverManager.getConnection(connectionURL);
        } catch (Exception exception) {
            Log.e("Error ", exception.getMessage());
        }
return connection;

    }

}
