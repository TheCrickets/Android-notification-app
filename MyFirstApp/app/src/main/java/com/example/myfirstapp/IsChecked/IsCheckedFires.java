package com.example.myfirstapp.IsChecked;

import com.example.myfirstapp.IsChecked.IsChecked;

public class IsCheckedFires extends IsChecked
{
    @Override
    public String printStatus()
    {
        if(isClicked())
        {
            return "Fires : 1";
        }
        else
        {
            return "Fires : 0";
        }
    }
}
