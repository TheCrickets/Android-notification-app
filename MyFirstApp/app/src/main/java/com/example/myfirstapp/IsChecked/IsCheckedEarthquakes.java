package com.example.myfirstapp.IsChecked;

import com.example.myfirstapp.IsChecked.IsChecked;

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
