package com.example.attendence_full_dev;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class login extends AppCompatActivity {
    EditText phone,pass;
    Button lg;
    String username="",password="";
    Connection connection;
    int s=0;
    Spinner spinner;
    String topass;

    String[] courses = { "mces", "dbms",
            "fafl", "dm","unix","elective","neuralnetworks","webprogram","os","ml"};





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        lg = findViewById(R.id.lg);


        spinner=findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, courses);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                Toast.makeText(getApplicationContext(), courses[position], Toast.LENGTH_LONG).show();
                topass=courses[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });














            lg.setOnClickListener(new View.OnClickListener() {




                //get values from input field


            @Override
            public void onClick(View v) {



                username = phone.getText().toString();
                password = pass.getText().toString();
               // new task().execute();

//database connection
                databasemng c = new databasemng();
                connection = c.conclass();
                //AlertDialog.Builder builder=new AlertDialog.Builder(login.this);
                //builder.setMessage("connecting reaced");
                if(c!=null){
                    try{

//execute sql query
                        String sqlstatement = "select * from teacher where faculty_name='"+username+"' and password='"+password+"'";
                        Statement smt = connection.createStatement();
                        ResultSet set = smt.executeQuery(sqlstatement);
                        //System.out.println(set);
//setting input fields blank
                        //phone.setText(null);
                        //pass.setText(null);


                        String s="";
                        s= String.valueOf(set.next());

//if login successs redirect to next scanning page
                        if (s=="true") {


                            Intent i=new Intent(getApplicationContext(), scanning.class);
                            i.putExtra("key",topass);
                            startActivity(i);

                            Toast.makeText(login.this, "login successful", Toast.LENGTH_SHORT).show();
                            phone.setText(null);
                            pass.setText(null);
                            username="";
                            password="";
                            s="false";


                       }
                        else {
                            Toast.makeText(login.this, "invalid login", Toast.LENGTH_SHORT).show();
                            username="";
                            password="";
                            phone.setText(null);
                            pass.setText(null);



                        }
                    }catch (Exception e){
                        Log.e("ERROR:",e.getMessage());
                    }

//this message displayed if problem with database
                }
                else{
                    Toast.makeText(login.this, "database not linked", Toast.LENGTH_SHORT).show();
                }
            }



        });
        }







    }


