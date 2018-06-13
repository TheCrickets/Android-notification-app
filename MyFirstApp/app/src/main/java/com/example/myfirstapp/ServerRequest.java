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
    /**
     * this function is responsible of the client-server communication
     * it sends the json array to the server and gets the response
     * @param strings
     * @return string that has the notification
     */
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

            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(strings[0]);
            out.flush();
            out.close();

            int status = con.getResponseCode();
            if(status != 200)
                return "";
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
            
            Gson gson = new Gson();
            String message = gson.fromJson(response.toString(), String.class);

            return message;

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
