package com.simpletom.cypher;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity {

    private List<String> words;
    private List<TextView> boxes;
    private String word;

    private StringBuilder encrypted;

    private int touch = 0;
    private int level = 1;

    private TextView main;
    private TextView boxOne;
    private TextView boxTwo;
    private TextView boxThree;
    private TextView boxFour;
    private TextView boxFive;
    private TextView boxSix;
    private TextView boxSeven;
    private TextView boxEight;

    private Random rando = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        words = new ArrayList<>();
        words.add("HELLO");
        words.add("WELCOME");
        words.add("DECODE");
        words.add("TITS");
        words.add("BUMS");

        boxOne = findViewById(R.id.box_one);
        boxTwo = findViewById(R.id.box_two);
        boxThree = findViewById(R.id.box_three);
        boxFour = findViewById(R.id.box_four);
        boxFive = findViewById(R.id.box_five);
        boxSix = findViewById(R.id.box_six);
        boxSeven = findViewById(R.id.box_seven);
        boxEight = findViewById(R.id.box_eight);

        boxes = new ArrayList<>();
        boxes.add(boxOne);
        boxes.add(boxTwo);
        boxes.add(boxThree);
        boxes.add(boxFour);
        boxes.add(boxFive);
        boxes.add(boxSix);
        boxes.add(boxSeven);
        boxes.add(boxEight);

        main = findViewById(R.id.main);
        main.setText("Welcome to Cypher\n Press screen to continue");
        reset();
    }
    public void changeColor(View view) {
        TextView textView = (TextView) findViewById(R.id.box_four);
        textView.setBackgroundColor(Color.RED);
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (level == 1 && touch == 0) {
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:
                    touch = 1;
                    main.setText("Break the code to move \n on to the next level");
                    encryptL1();
                    play();
                    break;
                default:
                    break;
            }
        }
        if (level == 2 && touch == 0) {
            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_UP:
                    touch = 1;
                    main.setText("Have a go at this one then");
                    encryptL1();
                    play();
                    break;
                default:
                    break;
            }
        }
//        else {
//            main.setText("You Win");
//            level =1;
//        }
        return true;
    }
    public void checkAnswer(View view)
    {
        EditText ansField = findViewById(R.id.answer_field);
        String answer = ansField.getText().toString();
        System.out.println(word);
        if(answer.equals(word)) {
            main.setText("CORRECT\n Tap Screen to continue ");
            level++;
            reset();
            ansField.setText("");
            touch = 0;
        }
        else{
            main.setText("WRONG");

        }
    }
    public void play(){
        int i = 0;
        for(TextView box : boxes){
            if(word.length() > i) {
                box.setText("" + encrypted.charAt(i));
                //box.setGravity(Gravity.CENTER_HORIZONTAL);
                box.setVisibility(View.VISIBLE);
            }
            i++;
        }
    }
    // code for basic level one encryption
    public void encryptL1(){
        word = words.get(rando.nextInt(words.size()));
        encrypted = new StringBuilder(word);

        for(int i = 0; i < encrypted.length(); i++) {
            char aChar = encrypted.charAt(i);
            int aCharInt = aChar;
            aCharInt--;
            char newChar = (char)aCharInt;
            encrypted.setCharAt(i, newChar);

        }
    }
    private void reset(){
        for(TextView box : boxes) {

            box.setVisibility(View.INVISIBLE);
        }


    }
}
