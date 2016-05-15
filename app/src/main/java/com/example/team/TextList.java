package com.example.team;

import android.content.Context;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by 병훈 on 2015-12-05.
 */
public class TextList {
    private Context context;
    private int id;

    private List<String> textList;

    public TextList(Context context, int id) {
        try {
            this.context = context;
            this.id = id;

            InputStream is = context.getResources().openRawResource(id);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String[] textListTemp = new String(buffer).split("\n"); //n //   /[\r|\n]/g
            textList = Arrays.asList(textListTemp);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getTextList() {
        randomShuffle();
        return textList;
    }
    public void randomShuffle() {
        Collections.shuffle(textList);

        for (String string : textList) {
            System.out.println(string);
        }

    }
    /*
     List<String> list = Arrays.asList(strs);
    Collections.shuffle(list);
    for (String string : list) {
        System.out.println(string);
    }
     */
}
