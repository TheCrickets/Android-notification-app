package com.example.myfirstapp;

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
