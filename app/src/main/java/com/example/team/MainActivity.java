package com.example.team;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int SETTING_RESULT=1;
    //private static final int READ_BLOCK_SIZE=100;
    String language;
    String name;
    String sound;
    String id;
    TextView title;
    TextView info;
    ImageButton typing;
    ImageButton game;
    ImageButton ranking;
    ImageButton settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        title=(TextView)findViewById(R.id.title);
        typing=(ImageButton)findViewById(R.id.typingpra);
        game=(ImageButton)findViewById(R.id.game);
        ranking=(ImageButton)findViewById(R.id.ranking);
        settings=(ImageButton)findViewById(R.id.setting);
        info=(TextView)findViewById(R.id.selectlanguage);

        typing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intObj = new Intent(MainActivity.this, TypingMenu.class);
                startActivity(intObj);
            }
        });
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intObj2 = new Intent(MainActivity.this, TypingGame.class);
                startActivity(intObj2);

            }
        });
        ranking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intObj3 = new Intent(MainActivity.this,Ranking.class);
                startActivity(intObj3);
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Setting.class);
                startActivityForResult(i, SETTING_RESULT);
            }
        });

    }


    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SETTING_RESULT){
            displayUserSetting();
        }
    }

    private void displayUserSetting(){
        //String settings1="This is Preference Settings";
        // String settings2="\n";
        // settings1=settings1;
        // settings2=settings2+sharedPrefs.getString("getString", "")+sharedPrefs.getBoolean("checkbox", false);
        //TextView textview1=(TextView)findViewById(R.id.textView1);
        //TextView textview2=(TextView)findViewById(R.id.textView2);
        //CheckBox checkbox1 =(CheckBox)findViewById(R.id.checkbox1);
        //checkbox1.setChecked(true);

        //textview1.setText(settings1);
        //textview2.setText(settings2);
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String settings1="";
        String settings2="";
        String settings3="";
        String settings4="";

        settings1=sharedPrefs.getString("getName", "NOMODE");

        info.setText(settings1);
        language= info.getText().toString();

        settings2=sharedPrefs.getString("getID","NONAME");
        settings3=sharedPrefs.getString("emp","NOSound");


        settings4=sharedPrefs.getString("language", "NOMODE");

        info.setText("name"+settings1+"language"+settings2);
        language= info.getText().toString();


    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
}
