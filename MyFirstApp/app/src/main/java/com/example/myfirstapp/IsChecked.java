package com.example.myfirstapp;

import android.view.View;
import android.widget.CheckBox;

public abstract class IsChecked
{
    public View getView()
    {
        return view;
    }

    public void setView(View view)
    {
        this.view = view;
    }

    private View view;



    public boolean isClicked()
    {
        CheckBox checkBox = (CheckBox)view;
        if(checkBox.isChecked())
        {
            return true;
        }
        return false;
    }

    public void diplayChecked(boolean status)
    {
        CheckBox checkBox = (CheckBox) view;
        checkBox.setChecked(status);
        /*if(status)
        {
            checkBox.setChecked(true);
        }
        else
        {
            checkBox.setChecked(false);
        }*/
    }

    public abstract String printStatus();

}
