package com.example.myfirstapp.IsChecked;

import android.view.View;
import android.widget.CheckBox;

/**
 * IsChecked class is the base class for the disasters that the app gives notifications for
 */
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

    /**
     * the function displays the status of the user's preferences for every disaster
     * if the user would like to receive notifications then the status will be true, thus the checkbox will be checked
     * @param status
     */
    public void displayChecked(boolean status)
    {
        CheckBox checkBox = (CheckBox) view;
        checkBox.setChecked(status);
    }

    /**
     * this function will return a String that contains "DisasterName" : "UserPreference",
     * where
     *      DisasterName is the name of the disaster
     *      UserPreference is 0 or 1; 0 - the user won't receive notifications about DisasterName type of disaster; 1 - the user will receive notifications
     * @return
     */
    public abstract String printStatus();
}
