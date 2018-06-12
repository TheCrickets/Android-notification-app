package com.example.myfirstapp;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

public class MyLocationUsingLocationAPI extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, ActivityCompat.OnRequestPermissionsResultCallback//, PermissionResultCallback
{

    @Override
    public void onConnectionFailed(ConnectionResult result)
    {
        Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "+ result.getErrorCode());
    }

    @Override
    public void onConnected(Bundle arg0)
    {
        // Once connected with google api, get the location
    }

    @Override
    public void onConnectionSuspended(int arg0)
    {
        mGoogleApiClient.connect();
    }
}