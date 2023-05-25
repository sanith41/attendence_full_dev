package com.example.attendence_full_dev;

import static com.example.attendence_full_dev.R.layout.activity_scanning;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class scanning extends AppCompatActivity implements OnClickListener {
    Button scan;
    TextView messageText,test;
    String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_scanning);

        // referencing and initializing
        // the button and textviews
        scan = findViewById(R.id.scan);
        messageText = findViewById(R.id.t1);
        test = findViewById(R.id.test);

        input = (String)getIntent().getExtras().getSerializable("key");
        //Toast.makeText(scanning.this,input, Toast.LENGTH_SHORT).show();


        //messageFormat = findViewById(R.id.te);

        // adding listener to the button
        scan.setOnClickListener(this);




    }

    @Override
    public void onClick(View v) {
        // we need to create the object
        // of IntentIntegrator class
        // which is the class of QR library
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Scan a barcode or QR Code");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        // if the intentResult is null then
        // toast a message as "cancelled"
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(getBaseContext(), "Cancelled", Toast.LENGTH_SHORT).show();
            } else {
                // if the intentResult is not null we'll set
                // the content and format of scan message
                //messageText.setText(intentResult.getContents());




             //String qr = intentResult.getContents().toString();
             String qr=intentResult.getContents().toString();



             String[] arr=qr.split("%");
             String table=arr[0];
             System.out.println(table);
             String usn=arr[1];
             String name=arr[2];
               // test.setText(usn+" "+name+" "+table);
                String[] tablename=table.split("//");
                String shit=tablename[0];
                String branch=tablename[1];
                test.setText(usn+" "+name+" "+branch);




                databasemng c = new databasemng();
                Connection connection = c.conclass();
                if(c!=null){
                    try {
                        //coreection here
                       ArrayList<String> checkduplicate = new ArrayList<String>();
                       Boolean t=checkduplicate.contains(usn);

                            if (t=true) {
                                Toast.makeText(this, "duplicate entires not allowed", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                String check = "UPDATE "+branch+" SET "+input+" = "+input+"+1 WHERE usn='"+usn+"';";
                                Statement smt2 = connection.createStatement();
                                ResultSet set2 = smt2.executeQuery(check);
                                System.out.println(set2);
                                checkduplicate.add(usn);
                            }





                    }catch (Exception e){
                        Log.e("ERROR:",e.getMessage());
                    }


                }




            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
