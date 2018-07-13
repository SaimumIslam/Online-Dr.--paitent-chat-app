package com.example.foysal.apoinment;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class PaitentActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    LineGraphSeries<DataPoint> series1;
    private String friendId;
    private int sys, dia;
    private double tim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paitent);

        Intent intentData = getIntent();
        friendId = intentData.getStringExtra("FrinendId");

        final GraphView graphView = (GraphView) findViewById(R.id.graph);
        series = new LineGraphSeries<DataPoint>();
        series1 = new LineGraphSeries<DataPoint>();
        getGraph();

        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                graphView.addSeries(series);
                graphView.addSeries(series1);
            }
        },1000);
    }

    private void getGraph() {
        FirebaseDatabase.getInstance().getReference().child("bpcheckup/" + friendId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() != null) {
                    Map<String, Integer> map;
                    HashMap<String, Map> mapRecord = (HashMap<String, Map>) dataSnapshot.getValue();
                    //Iterator listKey = mapRecord.keySet().iterator();
                    ArrayList keylist=new ArrayList(mapRecord.keySet());
                    int k=1;
                    for (int i=keylist.size()-1;i>=0;i--) {
                        String key = String.valueOf(keylist.get(i));
                        map = mapRecord.get(key);
                        for (Map.Entry m : map.entrySet()) {
                            if (m.getKey().equals("systolic")) {
                                sys = Integer.parseInt(String.valueOf(m.getValue()));
                            } else if (m.getKey().equals("diastolic")) {
                                dia = Integer.parseInt(String.valueOf(m.getValue()));
                            } else if (m.getKey().equals("timestamp")) {
                                tim = Double.parseDouble((String.valueOf(m.getValue())));
                                DecimalFormat df=new DecimalFormat("0.00");
                                tim= Double.parseDouble(df.format(tim));
                            }
                        }
                        series.appendData(new DataPoint(k, sys), true, 600);
                        series1.appendData(new DataPoint(k, -1*dia), true, 600);
                        k++;
                    }
                } else {
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public void getE()
    {
        double x=0.0,y,z;
        for (int i=0;i<500;i++)
        {
            x=x+.04;
            y=2*x;
            z=-3*x;
            series.appendData(new DataPoint(x, y), true, 600);
            series1.appendData(new DataPoint(x, z), true, 600);
        }
    }

}