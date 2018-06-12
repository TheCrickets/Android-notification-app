package com.example.myfirstapp;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class ServerRequest extends AsyncTask<String, Void, String>
{

    @Override
    protected String doInBackground(String... strings)
    {
        URL url = null;
        try
        {
            url = new URL("http://httpbin.org/post");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            con.setRequestProperty("Content-Type", "application/json");

            //set time here - 10/20 minute - vezi api

            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(strings[0]);

            int status = con.getResponseCode();
            System.out.println("IUPIIIIIIIIIIIIIIIIIIIIIIIIIIIII" + status);

            //primesc raspunsul si dau raspunsul cu return
            


        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (ProtocolException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
