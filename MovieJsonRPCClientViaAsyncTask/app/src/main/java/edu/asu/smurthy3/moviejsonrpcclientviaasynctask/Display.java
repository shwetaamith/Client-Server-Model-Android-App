package edu.asu.smurthy3.moviejsonrpcclientviaasynctask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Copyright (c) 2016 Shweta Murthy,
 * You may not use this file except for self-evaluation and practice
 * This file is allowed to be used for grading puroposes
 * through the spring semester 2016, ASU, by  the grader, TA and the instructor
 * Unless agreed to in writing, this material can is to be
 * distributed on an "AS IS" BASIS
 *
 * @author Shweta Murthy mailTo: smurthy3@asu.edu
 * @version 2/29/16
 */

public class Display extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie);
        final Intent intent = getIntent();
        String movie = intent.getStringExtra("movie");
        Log.d(this.getClass().getSimpleName(), "In MovieDescription, got intent");
        Movie m = new Movie(movie);
        System.out.println(m.name);
        final TextView t1 = (TextView) findViewById(R.id.textView1);
        final TextView t2 = (TextView) findViewById(R.id.textView2);
        final TextView t3 = (TextView) findViewById(R.id.textView3);
        final TextView t4 = (TextView) findViewById(R.id.textView4);
        final TextView t5 = (TextView) findViewById(R.id.textView5);
        final TextView t6 = (TextView) findViewById(R.id.textView6);
        final TextView t7 = (TextView) findViewById(R.id.textView7);
        Button b = (Button) findViewById(R.id.button);
        t1.setText(m.name);
        t2.setText(m.actors);
        t3.setText(m.plot);
        t4.setText(m.genre);
        t5.setText(m.released);
        t6.setText(m.rated);
        t7.setText(m.year);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

}
