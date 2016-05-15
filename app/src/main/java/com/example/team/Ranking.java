package com.example.team;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by 고완욱 on 2015-11-29.
 */
public class Ranking extends Activity{
    TextView title;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ranking);

        title=(TextView)findViewById(R.id.ranktitle);

    }
}
