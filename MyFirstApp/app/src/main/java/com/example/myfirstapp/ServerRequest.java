package com.example.myfirstapp;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONObject;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
            url = new URL("http://192.168.0.103:55555/api/notification");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            con.setRequestProperty("Content-Type", "application/json");

            //set time here - 10/20 minute - vezi api
            //con.setConnectTimeout(900000);
            //con.setReadTimeout(900000);

            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(strings[0]);
            out.flush();
            out.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            System.out.println(response.toString());

            //InputSource serverResponse = new InputSource(url.openStream());
            Gson gson = new Gson();
            String message = gson.fromJson(response.toString(), String.class);
            System.out.println(message);

            int status = con.getResponseCode();
            System.out.println("IUPIIIIIIIIIIIIIIIIIIIIIIIIIIIII" + status + "  " + message);

            return strings[0];

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
