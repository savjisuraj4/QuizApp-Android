package com.example.quizbee;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import java.text.DecimalFormat;
import java.text.NumberFormat;


public class QuestionsActivity extends AppCompatActivity {
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;
    CountDownTimer count;
    long pressedTime;
    String[] Question = {
                            "How many colors are there in a rainbow?",
                            "Virgin Trains, Virgin Atlantic and Virgin Racing, are all companies owned by which famous entrepreneur?",
                            "What is the first book of the Old Testament?",
                            "What does a funambulist walk on?",
                            "In past times, what would a gentleman keep in his fob pocket?",
                            "Which sign of the zodiac is represented by the Crab?",
                            "How would one say goodbye in Spanish?",
                            "On a dartboard, what number is directly opposite No. 1?"

                         };
    String[] answers = {"7","Richard Branson","Genesis","A Tight Rope","Watch","adiós","Cancer","19"};
    String[] opt = {
                    "5","7","9","8",
                    "Alan Sugar","Donald Trump","Bill Gates","Richard Branson",
                    "Exodus","Genesis","Leviticus","Numbers",
                    "Broken Glass","Balls","The Moon","A Tight Rope",
                    "Money","Keys","Notebook","Watch",
                    "adiós"," Hola","Au Revoir","Salir",
                    "Libra","Virgo","Sagittarius","Cancer",
                    "20","19","12","15"
                   };
    ;
    int flag=0;
    public static int marks=0,correct=0,wrong=0;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        final TextView score = findViewById(R.id.textView4);
        TextView textView=findViewById(R.id.editTextTime);
        count=new CountDownTimer(300000,1000){
            public void onTick(long millisUntilFinished) {
                NumberFormat f = new DecimalFormat("00");
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                textView.setText("Time Remained : " +f.format(min) + ":" + f.format(sec));
            }

            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Time Over",Toast.LENGTH_SHORT).show();
                Intent intent1 =new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(intent1);
            }
        };


        Intent intent = getIntent();
        submitbutton=findViewById(R.id.button3);
        quitbutton= findViewById(R.id.buttonquit);
        tv= findViewById(R.id.tvque);

        radio_g= findViewById(R.id.answersgrp);
        rb1=findViewById(R.id.radioButton);
        rb2=findViewById(R.id.radioButton2);
        rb3= findViewById(R.id.radioButton3);
        rb4=findViewById(R.id.radioButton4);
        count.start();

        tv.setText(Question[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        submitbutton.setOnClickListener(v -> {
            if(radio_g.getCheckedRadioButtonId()==-1)
            {
                Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                return;
            }
            RadioButton uans = findViewById(radio_g.getCheckedRadioButtonId());
            String ansText = uans.getText().toString();

            if(ansText.equals(answers[flag])) {
                correct++;
                Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
            }
            else {
                wrong++;
                Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
            }

            flag++;

            if (score != null)
                score.setText(""+correct);

            if(flag<Question.length)
            {
                tv.setText(Question[flag]);
                rb1.setText(opt[flag*4]);
                rb2.setText(opt[flag*4 +1]);
                rb3.setText(opt[flag*4 +2]);
                rb4.setText(opt[flag*4 +3]);
            }

            else
            {
                marks=correct;
                Intent in = new Intent(getApplicationContext(),ResultActivity.class);
                startActivity(in);
            }
            radio_g.clearCheck();
        });

        quitbutton.setOnClickListener(v -> {
            Intent intent1 =new Intent(getApplicationContext(),ResultActivity.class);
            startActivity(intent1);
        });
    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure?")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent1 =new Intent(getApplicationContext(),ResultActivity.class);
                        startActivity(intent1);
                        finish();
                    }
                }).setNegativeButton("no", null).show();
    }
}