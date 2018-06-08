package com.example.myfirstapp;

public class IsCheckedEarthquakes extends IsChecked
{
    @Override
    public String printStatus()
    {
        if(isClicked())
        {
            return "Earthquakes : 1";
        }
        else
        {
            return "Earthquakes : 0";
        }
    }
}
