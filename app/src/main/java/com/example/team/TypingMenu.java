package com.example.team;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by 고완욱 on 2015-11-29.
 */
public class TypingMenu extends Activity {

    TextView title;
    ImageButton spacepra;
    ImageButton wordpra;
    ImageButton shortpra;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.typingmenu);

        title=(TextView)findViewById(R.id.typingtitle);
        spacepra=(ImageButton)findViewById(R.id.spacepra);
        wordpra=(ImageButton)findViewById(R.id.wordpra);
        shortpra=(ImageButton)findViewById(R.id.shortpra);

        spacepra.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intObj1 = new Intent(getApplication(),SpaceTyping.class);
                startActivity(intObj1);
            }

        });
        wordpra.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intObj2 = new Intent(getApplication(),WordTyping.class);
                startActivity(intObj2);
            }

        });

        shortpra.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intObj3 = new Intent(getApplication(),ShortTyping.class);
                startActivity(intObj3);
            }

        });


    }
}
