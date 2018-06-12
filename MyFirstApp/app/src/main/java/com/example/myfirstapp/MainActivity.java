package com.example.myfirstapp;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

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

    de vazut cum se ia locatia din telefon

    pe server sa raspund ce am primit

 */



public class MainActivity extends AppCompatActivity
{
    private static final int REQUEST_CODE_PERMISSION = 1;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*
        ArrayList<String> permissions=new ArrayList<>();
        PermissionUtils permissionUtils;

        permissionUtils=new PermissionUtils(MyLocationUsingLocationAPI.this);

        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        permissionUtils.check_permission(permissions,"Need GPS permission for getting your location",1);
        */

        //

        GoogleApiClient mGoogleApiClient;
        mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this).addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this).addApi(LocationServices.API).build();
        mGoogleApiClient.connect();

        //daca e bifat, sa fie bifat
        checkList.add(new IsCheckedEarthquakes());
        checkList.get(0).setView(findViewById(R.id.checkBox));
        checkList.add(new IsCheckedFires());
        checkList.get(1).setView(findViewById(R.id.checkBox2));
        checkList.add(new IsCheckedFloods());
        checkList.get(2).setView(findViewById(R.id.checkBox3));

        try
        {
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
            isChecked.diplayChecked(false);
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
                        isChecked.diplayChecked(true);
                    }
                }
            }
            if(line.contains("Fires") && line.contains("1"))
            {
                for (IsChecked isChecked : checkList)
                {
                    if(isChecked instanceof  IsCheckedFires)
                    {
                        isChecked.diplayChecked(true);
                    }
                }
            }
            if(line.contains("Floods") && line.contains("1"))
            {
                for (IsChecked isChecked : checkList)
                {
                    if(isChecked instanceof  IsCheckedFloods    )
                    {
                        isChecked.diplayChecked(true);
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
}


//Un buton on/off daca vrea notificări
//Checkboxes cu ce dezastre vrea sa primeasca,
// deocamdată doar 1, dar fa sa mai pui adăuga ușor și altele