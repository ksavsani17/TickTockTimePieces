package com.example.ticktocktimepieces;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActiviti extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_);
        /*RelativeLayout rel= findViewById(R.id.animatedlayout);
        AnimationDrawable animation=(AnimationDrawable) rel.getBackground();
        animation.setEnterFadeDuration(2000);
        animation.setExitFadeDuration(4000);
        animation.start();*/


        Toast.makeText(AboutActiviti.this,"Please Give 5 Star In PlayStore",Toast.LENGTH_LONG).show();
    }
}