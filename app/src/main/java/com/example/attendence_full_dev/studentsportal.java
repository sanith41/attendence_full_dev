package com.example.attendence_full_dev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class studentsportal extends AppCompatActivity {
    EditText stusn,studentpass;
    Button lg;
    Connection connection;
    String stusername="",password="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentsportal);

        stusn = findViewById(R.id.stusn);
        studentpass = findViewById(R.id.studentpass);
        lg = findViewById(R.id.b2);

        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stusername = stusn.getText().toString();
                password = studentpass.getText().toString();
                // new task().execute();

//database connection
                databasemng c = new databasemng();
                connection = c.conclass();

                if(c!=null){
                    try{
                        Toast.makeText(studentsportal.this, stusername, Toast.LENGTH_SHORT).show();

//execute sql query
                        String sqlstatement2 = "select * from studentise where usn='"+stusername+"' and mob_no='"+password+"'";
                        Statement smt2 = connection.createStatement();
                        ResultSet set = smt2.executeQuery(sqlstatement2);

                        String s="";
                        s= String.valueOf(set.next());

//if login successs redirect to next scanning page
                        if (s=="true") {


                            Intent i=new Intent(getApplicationContext(), studentdetails.class);

                            startActivity(i);

                            Toast.makeText(studentsportal.this, "login successful", Toast.LENGTH_SHORT).show();
                            stusn.setText(null);
                            studentpass.setText(null);
                            stusername="";
                            password="";
                            s="false";


                        }
                        else {
                            Toast.makeText(studentsportal.this, "invalid login", Toast.LENGTH_SHORT).show();
                            stusername="";
                            password="";
                            stusn.setText(null);
                            studentpass.setText(null);



                        }
                    }catch (Exception e) {
                        Log.e("ERROR:", e.getMessage());
                    }
                }
                else{
                    Toast.makeText(studentsportal.this, "database not linked", Toast.LENGTH_SHORT).show();
                }
            }



        });
    }
}