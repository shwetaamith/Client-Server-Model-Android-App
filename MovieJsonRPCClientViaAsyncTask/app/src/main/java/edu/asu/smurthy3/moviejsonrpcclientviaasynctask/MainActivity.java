package edu.asu.smurthy3.moviejsonrpcclientviaasynctask;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
public class MainActivity extends ListActivity {
    public String defaulturl = "http://10.0.2.2:8080";
    public ArrayAdapter adapter;
    public Intent intent;
    public String[] n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<String> movies = new ArrayList<String>();
        movies.add("unknown");

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,movies);
        setListAdapter(adapter);
        try{
            MethodInformation mi = new MethodInformation(this,defaulturl,"resetFromJsonFile",
                    new String[]{});
            AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception creating adapter: "+
                    ex.getMessage());
        }


        try{
            MethodInformation mi = new MethodInformation(this,defaulturl,"getNames",
                    new String[]{});
            AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception creating adapter: "+
                    ex.getMessage());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        android.util.Log.d(this.getClass().getSimpleName(), "called onCreateOptionsMenu()");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        android.util.Log.d(this.getClass().getSimpleName(), "called onOptionsItemSelected()");
        switch (item.getItemId()) {
            case R.id.action_delete:
                Intent i = new Intent(MainActivity.this,Delete.class);
                i.putExtra("movies",n);
                startActivityForResult(i,1);
                return true;
            case R.id.action_addmovie:
                //m.setL(titles);
                Intent intent = new Intent(MainActivity.this, AddMe.class);
                startActivityForResult(intent, 2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Uri names = intent.getData();
                String movieinfo = names.toString();
                Log.d(this.getClass().getSimpleName(), "IN activityResult");
                Log.d(this.getClass().getSimpleName(), movieinfo);
                try {
                    MethodInformation mi = new MethodInformation(this, defaulturl, "remove",
                            new String[]{movieinfo});
                    AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
                } catch (Exception ex) {
                    android.util.Log.w(this.getClass().getSimpleName(), "Exception creating adapter: " +
                            ex.getMessage());
                }

                try{
                    MethodInformation mi = new MethodInformation(this,defaulturl,"getNames",
                            new String[]{});
                    AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
                } catch (Exception ex){
                    android.util.Log.w(this.getClass().getSimpleName(),"Exception creating adapter: "+
                            ex.getMessage());
                }

            }
        }
        else if(requestCode == 2){
            Uri names = intent.getData();
            String movieinfo = names.toString();
            Log.d(this.getClass().getSimpleName(), "IN activityResult");
            Log.d(this.getClass().getSimpleName(), movieinfo);
            Movie movie = new Movie(movieinfo);
            try {
                MethodInformation mi = new MethodInformation(this, defaulturl, "add",
                        new Object[]{movie.toJson()});
                AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
            } catch (Exception ex) {
                android.util.Log.w(this.getClass().getSimpleName(), "Exception creating adapter: " +
                        ex.getMessage());
            }

            try{
                MethodInformation mi = new MethodInformation(this,defaulturl,"getNames",
                        new String[]{});
                AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
            } catch (Exception ex){
                android.util.Log.w(this.getClass().getSimpleName(),"Exception creating adapter: "+
                        ex.getMessage());
            }
        }
    }

    @Override
    protected void onListItemClick (ListView listView, View v,int position, long id){
        super.onListItemClick(listView, v, position, id);

        String movie = (String) listView.getItemAtPosition(position);
        Log.d(this.getClass().getSimpleName(), movie);
        try{
            MethodInformation mi = new MethodInformation(this,defaulturl,"get",
                    new String[]{movie});
            AsyncCollectionConnect ac = (AsyncCollectionConnect) new AsyncCollectionConnect().execute(mi);
        } catch (Exception ex){
            android.util.Log.w(this.getClass().getSimpleName(),"Exception creating adapter: "+
                    ex.getMessage());
        }


    }
}
