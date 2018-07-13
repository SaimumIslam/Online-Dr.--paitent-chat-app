package com.example.foysal.apoinment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import fr.ganfra.materialspinner.MaterialSpinner;

public class RequirementFragment extends Fragment {


    private MaterialSpinner investigate;
    private List<String> investigatelist=new ArrayList<>();
    private String invest="";

    private EditText Bp11,Bp12;
    private EditText Bp21,Bp22;
    private EditText Bp31,Bp32;
    private EditText Pulse1,Pulse2,Pulse3;
    private EditText Chollestrol,Urine,Cxa,Hba;

    private Button Save;

    private int sys,dis;
    private int chol=0,urin=0,cxa=0,hba=0;
    private int pulse1,pulse2,pulse3;
    private int bp11=0,bp12=0,bp21=0,bp31=0,bp22=0,bp32=0;
    private int check=0;

    public RequirementFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_requirement, container, false);
        set();

        Bp11=(EditText)v.findViewById(R.id.bp1);
        Bp12=(EditText)v.findViewById(R.id.bp12);
        Bp21=(EditText)v.findViewById(R.id.bp2);
        Bp22=(EditText)v.findViewById(R.id.bp22);
        Bp31=(EditText)v.findViewById(R.id.bp3);
        Bp32=(EditText)v.findViewById(R.id.bp32);

        Pulse1=(EditText)v.findViewById(R.id.pulse1);
        Pulse2=(EditText)v.findViewById(R.id.pulse2);
        Pulse3=(EditText)v.findViewById(R.id.pulse3);

        Chollestrol=(EditText)v.findViewById(R.id.col);
        Urine=(EditText)v.findViewById(R.id.urine);
        Cxa=(EditText)v.findViewById(R.id.cxa);
        Hba=(EditText)v.findViewById(R.id.hba);

        Save=(Button)v.findViewById(R.id.save);

        investigate= (MaterialSpinner)v.findViewById(R.id.investigate);
        final ArrayAdapter adapter1 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item,investigatelist);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        investigate.setAdapter(adapter1);;

        investigate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=-1)//hint
                {
                    if(i==0)
                    {
                        Chollestrol.setVisibility(View.VISIBLE);
                        Urine.setVisibility(View.VISIBLE);
                        Cxa.setVisibility(View.VISIBLE);
                        Hba.setVisibility(View.VISIBLE);
                    }
                    else if(i==1)
                    {
                        Chollestrol.setVisibility(View.GONE);
                        Urine.setVisibility(View.GONE);
                        Cxa.setVisibility(View.GONE);
                        Hba.setVisibility(View.GONE);

                    }
                    invest=investigate.getItemAtPosition(i).toString().toLowerCase();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                investigate.setError("your investigation");
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!Bp11.getText().toString().equals(""))
                {
                    bp11= Integer.parseInt(Bp11.getText().toString());
                }
                else
                {
                    check=1;
                }
                if(!Bp12.getText().toString().equals(""))
                {
                    bp12= Integer.parseInt(Bp12.getText().toString());
                }
                else
                {
                    check=1;
                }

                if(!Bp21.getText().toString().equals(""))
                {
                    bp21= Integer.parseInt(Bp21.getText().toString());
                }
                else
                {
                    check=1;
                }

                if(!Bp22.getText().toString().equals(""))
                {
                    bp22= Integer.parseInt(Bp22.getText().toString());
                }
                else
                {
                    check=1;
                }
                if(!Bp31.getText().toString().equals(""))
                {
                    bp31= Integer.parseInt(Bp31.getText().toString());
                }

                if(!Bp31.getText().toString().equals(""))
                {
                    bp32= Integer.parseInt(Bp32.getText().toString());
                }

                if(check==1)
                {
                    Toast.makeText(getContext(),"Please give valid input",Toast.LENGTH_LONG).show();
                }
                else
                {
                    if(bp32==0||bp31==0)
                    {
                        sys=(bp11+bp21)/2;
                        dis=(bp12+bp22)/2;
                    }
                    else
                    {
                        sys=(bp11+bp21+bp31)/3;
                        dis=(bp12+bp22+bp32)/3;
                    }
                    //pulse1= Integer.parseInt(Pulse3.getText().toString());
                    //pulse2= Integer.parseInt(Pulse2.getText().toString());
                    //pulse3= Integer.parseInt(Pulse3.getText().toString());

                    if(!Chollestrol.getText().toString().equals(""))
                    {
                        chol= Integer.parseInt(Chollestrol.getText().toString());
                    }
                    if(!Urine.getText().toString().equals(""))
                    {
                        urin= Integer.parseInt(Urine.getText().toString());
                    }
                    if(!Cxa.getText().toString().equals(""))
                    {
                        cxa= Integer.parseInt(Cxa.getText().toString());
                    }
                    if(!Hba.getText().toString().equals(""))
                    {
                        hba= Integer.parseInt(Hba.getText().toString());
                    }
                    BpCheckup bp = new BpCheckup();
                    bp.systolic=sys;
                    bp.diastolic=dis;
                    bp.timestamp = System.currentTimeMillis();
                    FirebaseDatabase.getInstance().getReference().child("bpcheckup/"+StaticConfig.UID).push().setValue(bp);

                }

            }
        });
        return v;
    }
    public void set()
    {
        investigatelist.add("Yes");
        investigatelist.add("NO");
    }
}
