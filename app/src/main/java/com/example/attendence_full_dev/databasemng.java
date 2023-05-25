package com.example.attendence_full_dev;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;

public class databasemng {
    Connection con;

    @SuppressLint("NewApi")
    public Connection conclass() {
        // The device where app is running and device where database resides should be connected
        // through a network.
        // Provide the IP address of the device where the database resides.
        // Change username and password accordingly.
        String ip = "192.168.29.143", port = "1433", db = "attendence", username = "sanith", password = "sanith9659";
        StrictMode.ThreadPolicy a = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(a);
        String ConnectURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectURL = "jdbc:jtds:sqlserver://" + ip + ":" + port + ";" +
                    "databasename=" + db + ";user=" + username + ";" + "password=" + password + ";";
            DriverManager.setLoginTimeout(1);
            con = DriverManager.getConnection(ConnectURL);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }
        return con;
    }
}
