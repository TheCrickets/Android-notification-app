package com.example.myfirstapp.IsChecked;

import com.example.myfirstapp.IsChecked.IsChecked;

public class IsCheckedFloods extends IsChecked
{
    @Override
    public String printStatus()
    {
        if(isClicked())
        {
            return "Floods : 1";
        }
        else
        {
            return "Floods : 0";
        }
    }
}
