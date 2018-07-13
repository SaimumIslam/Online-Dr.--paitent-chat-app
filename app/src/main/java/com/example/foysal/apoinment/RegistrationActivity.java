package com.example.foysal.apoinment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.ganfra.materialspinner.MaterialSpinner;


public class RegistrationActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private Button btnDatePicker;
    AlertDialog.Builder builder;

    private EditText txtDate;
    private EditText FName, LName,Address, Citygenship, Weight,Height,Mobile;
    private EditText editTextEmail, editTextPassword, editTextRepeatPassword;
    private Button Doctor,Paitent;


    private MaterialSpinner gender;
    private List<String> genderlist=new ArrayList<>();

    private MaterialSpinner profession;
    private List<String> professionlist=new ArrayList<>();

    private CardView cvAdd,check;

    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static String STR_EXTRA_ACTION_REGISTER = "register";
    private String lname="",fName,sex="",address,citygen,date,age="",weight="",height="",Type,number;


    private int mYear, mMonth, mDay;
    private int agee = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar=(Toolbar)findViewById(R.id.Register_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Doctor=(Button)findViewById(R.id.Doctor);
        Paitent=(Button)findViewById(R.id.Paitent);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        cvAdd = (CardView) findViewById(R.id.cv_add);
        check = (CardView) findViewById(R.id.check);
        editTextEmail = (EditText) findViewById(R.id.RegEmail);
        editTextPassword = (EditText) findViewById(R.id.RegPassword);
        editTextRepeatPassword = (EditText) findViewById(R.id.ConfirmPassword);

        FName=(EditText)findViewById(R.id.et_username);
        LName=(EditText)findViewById(R.id.LastName);
        Address=(EditText)findViewById(R.id.Address);
        Citygenship=(EditText)findViewById(R.id.Cityzenship);
        Weight=(EditText)findViewById(R.id.Weight);
        Height=(EditText)findViewById(R.id.Height);
        Mobile=(EditText)findViewById(R.id.Mobile);

        txtDate=(EditText)findViewById(R.id.in_date);
        btnDatePicker=(Button)findViewById(R.id.btn_date);
        builder = new AlertDialog.Builder(RegistrationActivity.this);

        Doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.setVisibility(View.GONE);
                cvAdd.setVisibility(View.VISIBLE);
                Height.setVisibility(View.GONE);
                Weight.setVisibility(View.GONE);
                Citygenship.setVisibility(View.GONE);
                btnDatePicker.setVisibility(View.GONE);
                txtDate.setVisibility(View.GONE);

                Type="Doctor";
            }
        });
        Paitent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check.setVisibility(View.GONE);
                cvAdd.setVisibility(View.VISIBLE);
                Height.setVisibility(View.VISIBLE);
                Weight.setVisibility(View.VISIBLE);
                Citygenship.setVisibility(View.VISIBLE);
                txtDate.setVisibility(View.VISIBLE);
                btnDatePicker.setVisibility(View.VISIBLE);
                Type="Paitent";
            }
        });

        setSex();

        gender= (MaterialSpinner) findViewById(R.id.gender);
        final ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,genderlist);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter1);;

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=-1)//hint
                {
                    if(i==0)
                    {
                    }
                    else
                    {
                        sex=gender.getItemAtPosition(i).toString().toLowerCase();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                gender.setError("Please select your sex");
            }
        });


        setProfession();
        profession= (MaterialSpinner) findViewById(R.id.Profession);
        final ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,professionlist);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profession.setAdapter(adapter2);;

        profession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i!=-1)//hint
                {
                    if(i==0)
                    {
                    }
                    else
                    {
                        sex=profession.getItemAtPosition(i).toString().toLowerCase();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                profession.setError("Please select your sex");
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ShowEnterAnimation();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    animateRevealClose();
                }
                else
                {
                    RegistrationActivity.super.onBackPressed();
                }
            }
        });
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay=c.get(Calendar.DAY_OF_YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(RegistrationActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                if(year/1000==2)
                                {
                                    agee=mYear-year;
                                    txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year+" / "+agee);
                                }
                                else
                                {
                                    agee=(mYear-2000)+(2000-year);
                                    txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year+" / "+agee);
                                }
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

    }
    private void ShowEnterAnimation() {
        Transition transition = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(transition);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            transition.addListener(new Transition.TransitionListener() {
                @Override
                public void onTransitionStart(Transition transition) {
                    cvAdd.setVisibility(View.GONE);
                }

                @Override
                public void onTransitionEnd(Transition transition) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                        transition.removeListener(this);
                    }
                    animateRevealShow();
                }

                @Override
                public void onTransitionCancel(Transition transition) {

                }

                @Override
                public void onTransitionPause(Transition transition) {

                }

                @Override
                public void onTransitionResume(Transition transition) {

                }


            });
        }
    }

    public void animateRevealShow() {
        Animator mAnimator = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        }
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                cvAdd.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        }
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                cvAdd.setVisibility(View.INVISIBLE);
                super.onAnimationEnd(animation);
                fab.setImageResource(R.drawable.ic_signup);
                RegistrationActivity.super.onBackPressed();
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
            }
        });
        mAnimator.start();
    }
    @Override
    public void onBackPressed() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            animateRevealClose();
        }
        else
        {
            RegistrationActivity.super.onBackPressed();

        }
    }

    public void clickRegister(View view) {
        lname=LName.getText().toString();
        fName=FName.getText().toString();
        address=Address.getText().toString();
        citygen=Citygenship.getText().toString();
        age= String.valueOf(agee);
        weight=Weight.getText().toString();
        height=Height.getText().toString();
        date=txtDate.getText().toString();
        number=Mobile.getText().toString();
        if(fName.isEmpty() || lname.isEmpty()||address.isEmpty()||number.isEmpty())
        {
            builder.setTitle("Please  Fill All Things");
            displayAlert("Fill  Necessary Somes");
        }
        else {

            String Email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            String repeatPassword = editTextRepeatPassword.getText().toString();
            if (validate(Email, password, repeatPassword)) {
                Intent data = new Intent();
                data.putExtra(StaticConfig.STR_EXTRA_USERNAME, Email);
                data.putExtra(StaticConfig.STR_EXTRA_PASSWORD, password);
                data.putExtra("Type", Type);
                data.putExtra("Name", fName);
                data.putExtra("Age", age);
                data.putExtra(StaticConfig.STR_EXTRA_ACTION, STR_EXTRA_ACTION_REGISTER);
                setResult(RESULT_OK, data);
                finish();
            } else {
                Toast.makeText(this, "Invalid email or not match password", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean validate(String emailStr, String password, String repeatPassword) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return password.length() > 0 && repeatPassword.equals(password) && matcher.find();
    }
    public void displayAlert(String message) {
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    public void setSex()
    {
        genderlist.add("Male");
        genderlist.add("Female");
    }
    public void setProfession()
    {
        professionlist.add("Unemployed");
        professionlist.add("Teacher");
        professionlist.add("Service");
        professionlist.add("Business");
        professionlist.add("Housewife");
        professionlist.add("Farmer");
        professionlist.add("Private");
        professionlist.add("Police");
        professionlist.add("Driver");
        professionlist.add("Electrician");
        professionlist.add("Contractor");
        professionlist.add("Nurse");
        professionlist.add("Fisher");
        professionlist.add("Worker");
        professionlist.add("Engineer");
        professionlist.add("Politics");
        professionlist.add("Painting");
    }

}