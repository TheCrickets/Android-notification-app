package com.example.myfirstapp;

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
