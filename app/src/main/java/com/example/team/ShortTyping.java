package com.example.team;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by 고완욱 on 2015-11-29.
 */
public class ShortTyping extends Activity {

    private TextList shortList;
    private List<String> shortListText;

    private TextView sentence;
    private EditText input;

    private TextView statusTxt;
    private TextView wrongTxt;
    private TextView correctTxt;

    private int totalGameNumber;

    private int statusCnt;
    private int wrongCnt;
    private int correctCnt;


    private CustomDialog mCustomDialog;

    private double averageWPM; // Words Per Minute
    private boolean checkStart;
    private long startTime;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shorttyping);

        totalGameNumber = 20;
        statusCnt = 0;
        wrongCnt = 0;
        correctCnt = 0;

        sentence=(TextView)findViewById(R.id.shortsentence);
        input=(EditText)findViewById(R.id.shortinput);

        statusTxt = (TextView)findViewById(R.id.statusTxt);
        wrongTxt = (TextView)findViewById(R.id.wrongTxt);
        correctTxt = (TextView)findViewById(R.id.correctTxt);

        shortList = new TextList(this, R.raw.shortlist);
        shortListText = shortList.getTextList();
        sentence.setText(shortListText.get(statusCnt));

        input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_DONE:
                        if (statusCnt + 1 < shortListText.size() && statusCnt < totalGameNumber) {
                            String sCmp1 = sentence.getText().toString();
                            String sCmp2 = input.getText().toString();

                            long endTime = System.currentTimeMillis();
                            averageWPM += ((sCmp1.length() - 1) / (double)(endTime - startTime)) * 1000.0 * 60.0;
                            checkStart = false;

                            statusCnt++;
                            statusTxt.setText(statusCnt + " / 20");

                            if (!compareString(sCmp1, sCmp2)) {
                                wrongCnt++;
                                wrongTxt.setText("" + wrongCnt);
                            } else {
                                correctCnt++;
                            }

                            if (correctCnt > 0) {
                                int numTemp = (int) (correctCnt * 100 / statusCnt);
                                correctTxt.setText(numTemp + "%");
                            }

                            sentence.setText(shortListText.get(statusCnt));
                            input.setText("");
                        }
                        else {
                            View.OnClickListener leftClickListener = new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getApplicationContext(), "왼쪽버튼 Click!!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            };

                            View.OnClickListener rightClickListener = new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getApplicationContext(), "오른쪽버튼 Click!!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            };

                            mCustomDialog = new CustomDialog(this,
                                    "8월의 크리스마스!!",
                                    "영화보러가자~!!!",
                                    leftClickListener,
                                    rightClickListener);
                            mCustomDialog.show();






                           /*AlertDialog.Builder alert = new AlertDialog.Builder(ShortTyping.this);

                            alert.setCancelable(false);
                            alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //dialog.dismiss();
                                    finish();
                                }
                            });
                            alert.setMessage("Average WPM : " + (int) (averageWPM / statusCnt));

                            alert.show();*/


                        }
                        break;
                    default:
                        return false;
                }
                return true;
            }
        });






        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(!checkStart && s.length() > 0) {
                    startTime = System.currentTimeMillis();
                    checkStart = true;
                }
                String sentenceTemp = sentence.getText().toString();
                SpannableStringBuilder sb = new SpannableStringBuilder(sentenceTemp);
                String sTemp = s.toString();

                for (int i = 0; i < sb.length() && i < sTemp.length(); i++) {
                    ForegroundColorSpan correct = new ForegroundColorSpan(Color.BLUE);
                    ForegroundColorSpan wrong = new ForegroundColorSpan(Color.RED);

                    if (sb.charAt(i) != sTemp.charAt(i)) {
                        sb.setSpan(wrong, i, i + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                    } else {
                        sb.setSpan(correct, i, i + 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
                    }
                }
                sentence.setText(sb);
            }
        });
    }
  /*  public Dialog onCreateDialog(int id){
        dialog=new Dialog(ShortTyping.this);
        View view = getLayoutInflater().inflate(R.layout.dialogback,null);
        dialog.setContentView(view);
        bnt1=(Button)view.findViewById(R.id.dialog_ok);
        bnt1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0){
                finish();
            }
        });
        return dialog;
    }*/



    public boolean compareString(String a, String b) {
        a = a.trim();
        b = b.trim();
        if(a.length() != b.length())
            return false;
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i))
                return false;
        }
        return true;
    }
}
