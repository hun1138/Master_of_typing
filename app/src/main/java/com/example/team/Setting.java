package com.example.team;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by 고완욱 on 2015-11-29.
 */
public class Setting extends PreferenceActivity{
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);

    }

}
