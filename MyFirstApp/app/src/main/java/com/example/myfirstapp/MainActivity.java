package com.example.myfirstapp;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.myfirstapp.IsChecked.IsChecked;
import com.example.myfirstapp.IsChecked.IsCheckedEarthquakes;
import com.example.myfirstapp.IsChecked.IsCheckedFires;
import com.example.myfirstapp.IsChecked.IsCheckedFloods;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
    TODO de facut json cu datele de trimis: preferintele, ca in fisier, si locatia adaugata la sfarsit
    pe server sa raspund ce am primit

 */

public class MainActivity extends AppCompatActivity
{
    public static RequestData requestData = new RequestData();

    private FusedLocationProviderClient mFusedLocationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>()
                {
                    @Override
                    public void onSuccess(Location location)
                    {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null)
                        {
                            requestData.setLongitude(location.getLongitude());
                            requestData.setLatitude(location.getLatitude());
                            System.out.println("MEEEEEEEEEEEEEEEEEEEEEEEEERGE " + requestData.getLatitude());
                            System.out.println(requestData.getLongitude());

                            Gson gson = new Gson();
                            final String requestDataJson = gson.toJson(requestData);
                            System.out.println(requestDataJson);


                            new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    new ServerRequest().execute(requestDataJson);
                                }
                            };
                            // new thread.start(chestia de mai jos)
                            // Logic to handle location object
                        }
                        else
                        {
                            System.out.println("NU MERGE");
                        }
                    }
                });

        //daca e bifat, sa fie bifat
        checkList.add(new IsCheckedEarthquakes());
        checkList.get(0).setView(findViewById(R.id.checkBox));
        checkList.add(new IsCheckedFires());
        checkList.get(1).setView(findViewById(R.id.checkBox2));
        checkList.add(new IsCheckedFloods());
        checkList.get(2).setView(findViewById(R.id.checkBox3));

        try
        {
            notification("aaaaa", "http://138.68.64.239:55555/api/test");
            getDataDisplayCheckbox();
        }
        catch (Exception e)
        {
            System.out.println(e + "error");
        }
    }




    protected List<IsChecked> checkList = new ArrayList<IsChecked>();
    protected File file = null;


    protected void saveData(View view) throws IOException
    {
        //pregatesc mesajul de pus in fisier
        checkList.get(0).setView(findViewById(R.id.checkBox));
        checkList.get(1).setView(findViewById(R.id.checkBox2));
        checkList.get(2).setView(findViewById(R.id.checkBox3));


        StringBuilder message = new StringBuilder();
        for (IsChecked isChecked : checkList)
        {
            message.append(isChecked.printStatus());
            message.append("\n");
        }

        //pun data in fisier
        if (file == null)
        {
            file = new File(getFilesDir(), "storage");
        }

        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(message.toString());

        bufferedWriter.close();


        try
        {
            getDataDisplayCheckbox();
        }
        catch (Exception e)
        {
            System.out.println(e + "error");
        }
    }

    protected void getDataDisplayCheckbox() throws IOException
    {
        if (file == null)
        {
            file = new File(getFilesDir(), "storage");
        }

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        //curat afisarea
        for (IsChecked isChecked : checkList)
        {
            isChecked.displayChecked(false);
        }

        //citesc linie cu linie si setez checkbox-ul la true
        while (line != null)
        {
            if(line.contains("Earthquakes") && line.contains("1"))
            {
                for (IsChecked isChecked : checkList)
                {
                    if(isChecked instanceof  IsCheckedEarthquakes)
                    {
                        isChecked.displayChecked(true);
                        requestData.addDisaster("Earthquakes");
                    }
                }
            }
            if(line.contains("Fires") && line.contains("1"))
            {
                for (IsChecked isChecked : checkList)
                {
                    if(isChecked instanceof  IsCheckedFires)
                    {
                        isChecked.displayChecked(true);
                        requestData.addDisaster("Fires");
                    }
                }
            }
            if(line.contains("Floods") && line.contains("1"))
            {
                for (IsChecked isChecked : checkList)
                {
                    if(isChecked instanceof  IsCheckedFloods    )
                    {
                        isChecked.displayChecked(true);
                        requestData.addDisaster("Floods");
                    }
                }
            }
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    protected void displaySaveMessage()
    {
        // "Your preference has been saved!"
    }

    /** Called when the user taps the Send button
    public fun sendMessage(view : View)
    {
        // Do something in response to button
    }
     */
    static public int notificationId = 0;
    public void notification(String message, String url)
    {
        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));//(this, AlertDetails.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, new String())
                .setSmallIcon(R.drawable.flaviconcric)
                .setContentTitle("Crisis Containment Service")
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setVisibility(1)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(notificationId++, mBuilder.build());
    }

}

