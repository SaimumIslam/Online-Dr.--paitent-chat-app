package com.example.foysal.apoinment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class preQuestion extends AppCompatActivity {

    private MaterialSpinner family;
    private List<String> familylist=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_question);


        setfamily();
        family= (MaterialSpinner) findViewById(R.id.family);
        final ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,familylist);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        family.setAdapter(adapter2);;

        family.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=-1)//hint
                {
                    if(i==0)
                    {
                    }
                    else
                    {

                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                family.setError("Please select your sex");
            }
        });

    }

    private void setfamily() {
        familylist.add("Father");
        familylist.add("Mother");
        familylist.add("Brother");
        familylist.add("Sister");
        familylist.add("GrandParents");
        familylist.add("No one");
    }
}
