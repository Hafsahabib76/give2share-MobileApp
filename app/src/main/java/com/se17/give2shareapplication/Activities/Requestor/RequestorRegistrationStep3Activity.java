package com.se17.give2shareapplication.Activities.Requestor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.kofigyan.stateprogressbar.StateProgressBar;
import com.se17.give2shareapplication.R;

public class RequestorRegistrationStep3Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //progressbar code
    Context ctx= RequestorRegistrationStep3Activity.this;
    String[] StepData = {"Step 1", "Step 2", "Step 3"};
    LinearLayout next, back;


    //code for spinner select of You are a:
    Spinner UsertypeSpinner, SourceofIncome_Spinner, fatherJob_spinner;
    EditText InstituteET, CompanyET, JobTitleET, MonthlyIncomeET,
            YearlyIncomeET, LastSalaryET, dailyIncomeET, WorktypeET;
    TextView InstituteTV, CompanyTV, JobTitleTV, MonthlyIncomeTV, YearlyIncomeTV, RetireDateTV,
            LastSalaryTV, dailyIncomeTV, LastCompanyTV, WorktypeTV, UnemployedsinceTV, SourceofIncomeTV, fatherjobTV;
    DatePicker RetireDatePicker, UnemployedsincePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_requestor_registration_step3);

        //progressbar code
        final StateProgressBar stateProgressBar = (StateProgressBar) findViewById(R.id.your_state_progress_bar_id);
        stateProgressBar.setStateDescriptionData(StepData);

        next = (LinearLayout) findViewById(R.id.lyt_next);
        back = (LinearLayout) findViewById(R.id.lyt_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stateProgressBar.setCurrentStateNumber(StateProgressBar.StateNumber.TWO);
                Intent in = new Intent(ctx, RequestorRegistrationStep2Activity.class);
                startActivity(in);
            }
        });

        next.setOnClickListener(v -> {
                Intent in = new Intent(ctx, RequestorHomeActivity.class);
                stateProgressBar.setAllStatesCompleted(true);
                startActivity(in);
                finish();
        });

        //code for spinner select of You are a:
        UsertypeSpinner = (Spinner) findViewById(R.id.usertypespinner);



        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this,
                R.array.usertype, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UsertypeSpinner.setAdapter(adapterSpinner);

        UsertypeSpinner.setOnItemSelectedListener(this);

        InstituteET = (EditText) findViewById(R.id.instituteNameET);
        InstituteTV = (TextView) findViewById(R.id.instituteName);
        CompanyET= (EditText) findViewById(R.id.CompanyNameET);
        CompanyTV= (TextView) findViewById(R.id.CompanyName);
        JobTitleET=(EditText) findViewById(R.id.jobtitleET);
        JobTitleTV= (TextView) findViewById(R.id.jobtitleTV);
        MonthlyIncomeET= (EditText) findViewById(R.id.monthlyincomeET);
        MonthlyIncomeTV= (TextView) findViewById(R.id.monthlyincomeTV);
        YearlyIncomeET= (EditText) findViewById(R.id.yearlyincomeET);
        YearlyIncomeTV= (TextView) findViewById(R.id.yearlyincomeTV);

        SourceofIncomeTV= (TextView) findViewById(R.id.SourceofIncomeTV);
        SourceofIncome_Spinner = (Spinner) findViewById(R.id.SourceofIncome_spinner);
        fatherjobTV= (TextView) findViewById(R.id.FatherJobTV);
        fatherJob_spinner= (Spinner) findViewById(R.id.FatherJob_spinner);
        UnemployedsinceTV= (TextView) findViewById(R.id.unemployedsinceTV);
        UnemployedsincePicker= (DatePicker) findViewById(R.id.unemployedsincePicker);
        LastCompanyTV= (TextView) findViewById(R.id.LastCompanyName);
        WorktypeET= (EditText) findViewById(R.id.worktypeET);
        WorktypeTV= (TextView) findViewById(R.id.worktypeTV);
        dailyIncomeTV= (TextView) findViewById(R.id.dailyincomeTV);
        dailyIncomeET= (EditText) findViewById(R.id.dailyincomeET);
        RetireDateTV= (TextView) findViewById(R.id.retirementdateTV);
        RetireDatePicker= (DatePicker) findViewById(R.id.retirementdatePicker);
        LastSalaryET= (EditText) findViewById(R.id.LastdrawnSalaryET);
        LastSalaryTV= (TextView) findViewById(R.id.LastdrawnSalaryTV);


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:

                break;
            case 1:
                //disable case4,5 fields
                CompanyTV.setVisibility(View.GONE);
                JobTitleTV.setVisibility(View.GONE);
                JobTitleET.setVisibility(View.GONE);
                MonthlyIncomeTV.setVisibility(View.GONE);
                MonthlyIncomeET.setVisibility(View.GONE);
                YearlyIncomeTV.setVisibility(View.GONE);
                YearlyIncomeET.setVisibility(View.GONE);

                //disable case6 fields
                UnemployedsinceTV.setVisibility(View.GONE);
                UnemployedsincePicker.setVisibility(View.GONE);
                LastCompanyTV.setVisibility(View.GONE);
                LastSalaryTV.setVisibility(View.GONE);
                LastSalaryET.setVisibility(View.GONE);
                SourceofIncomeTV.setVisibility(View.GONE);
                SourceofIncome_Spinner.setVisibility(View.GONE);

                //disable case7 fields
                WorktypeTV.setVisibility(View.GONE);
                WorktypeET.setVisibility(View.GONE);
                dailyIncomeTV.setVisibility(View.GONE);
                dailyIncomeET.setVisibility(View.GONE);

                //disable case8 fields
                RetireDateTV.setVisibility(View.GONE);
                RetireDatePicker.setVisibility(View.GONE);

                //enable case3 fields
                InstituteTV.setVisibility(View.VISIBLE);
                InstituteET.setVisibility(View.VISIBLE);
                fatherjobTV.setVisibility(View.VISIBLE);
                fatherJob_spinner.setVisibility(View.VISIBLE);
                break;

            case 2:
                //disable case4,5 fields
                CompanyTV.setVisibility(View.GONE);
                JobTitleTV.setVisibility(View.GONE);
                JobTitleET.setVisibility(View.GONE);
                MonthlyIncomeTV.setVisibility(View.GONE);
                MonthlyIncomeET.setVisibility(View.GONE);
                YearlyIncomeTV.setVisibility(View.GONE);
                YearlyIncomeET.setVisibility(View.GONE);

                //disable case6 fields
                UnemployedsinceTV.setVisibility(View.GONE);
                UnemployedsincePicker.setVisibility(View.GONE);
                LastCompanyTV.setVisibility(View.GONE);
                LastSalaryTV.setVisibility(View.GONE);
                LastSalaryET.setVisibility(View.GONE);
                SourceofIncomeTV.setVisibility(View.GONE);
                SourceofIncome_Spinner.setVisibility(View.GONE);

                //disable case7 fields
                WorktypeTV.setVisibility(View.GONE);
                WorktypeET.setVisibility(View.GONE);
                dailyIncomeTV.setVisibility(View.GONE);
                dailyIncomeET.setVisibility(View.GONE);

                //disable case8 fields
                RetireDateTV.setVisibility(View.GONE);
                RetireDatePicker.setVisibility(View.GONE);

                //enable case3 fields
                InstituteTV.setVisibility(View.VISIBLE);
                InstituteET.setVisibility(View.VISIBLE);
                fatherjobTV.setVisibility(View.VISIBLE);
                fatherJob_spinner.setVisibility(View.VISIBLE);
                break;

            case 3:
                //disable case4,5 fields
                CompanyTV.setVisibility(View.GONE);
                JobTitleTV.setVisibility(View.GONE);
                JobTitleET.setVisibility(View.GONE);
                MonthlyIncomeTV.setVisibility(View.GONE);
                MonthlyIncomeET.setVisibility(View.GONE);
                YearlyIncomeTV.setVisibility(View.GONE);
                YearlyIncomeET.setVisibility(View.GONE);

                //disable case6 fields
                UnemployedsinceTV.setVisibility(View.GONE);
                UnemployedsincePicker.setVisibility(View.GONE);
                LastCompanyTV.setVisibility(View.GONE);
                LastSalaryTV.setVisibility(View.GONE);
                LastSalaryET.setVisibility(View.GONE);
                SourceofIncomeTV.setVisibility(View.GONE);
                SourceofIncome_Spinner.setVisibility(View.GONE);

                //disable case7 fields
                WorktypeTV.setVisibility(View.GONE);
                WorktypeET.setVisibility(View.GONE);
                dailyIncomeTV.setVisibility(View.GONE);
                dailyIncomeET.setVisibility(View.GONE);

                //disable case8 fields
                RetireDateTV.setVisibility(View.GONE);
                RetireDatePicker.setVisibility(View.GONE);

                //enable case3 fields
                InstituteTV.setVisibility(View.VISIBLE);
                InstituteET.setVisibility(View.VISIBLE);
                fatherjobTV.setVisibility(View.VISIBLE);
                fatherJob_spinner.setVisibility(View.VISIBLE);
                break;

            case 4:
                //disable case1,2,3 fields
                InstituteTV.setVisibility(View.GONE);
                InstituteET.setVisibility(View.GONE);
                fatherjobTV.setVisibility(View.GONE);
                fatherJob_spinner.setVisibility(View.GONE);

                //disable case6 fields
                UnemployedsinceTV.setVisibility(View.GONE);
                UnemployedsincePicker.setVisibility(View.GONE);
                LastCompanyTV.setVisibility(View.GONE);
                LastSalaryTV.setVisibility(View.GONE);
                LastSalaryET.setVisibility(View.GONE);
                SourceofIncomeTV.setVisibility(View.GONE);
                SourceofIncome_Spinner.setVisibility(View.GONE);

                //disable case7 fields
                WorktypeTV.setVisibility(View.GONE);
                WorktypeET.setVisibility(View.GONE);
                dailyIncomeTV.setVisibility(View.GONE);
                dailyIncomeET.setVisibility(View.GONE);

                //disable case8 fields
                RetireDateTV.setVisibility(View.GONE);
                RetireDatePicker.setVisibility(View.GONE);

                //enable
                CompanyTV.setVisibility(View.VISIBLE);
                CompanyET.setVisibility(View.VISIBLE);
                JobTitleTV.setVisibility(View.VISIBLE);
                JobTitleET.setVisibility(View.VISIBLE);
                MonthlyIncomeTV.setVisibility(View.VISIBLE);
                MonthlyIncomeET.setVisibility(View.VISIBLE);
                YearlyIncomeTV.setVisibility(View.VISIBLE);
                YearlyIncomeET.setVisibility(View.VISIBLE);
                break;
            case 5:
                //disable case1,2,3 fields
                InstituteTV.setVisibility(View.GONE);
                InstituteET.setVisibility(View.GONE);
                fatherjobTV.setVisibility(View.GONE);
                fatherJob_spinner.setVisibility(View.GONE);

                //disable case6 fields
                UnemployedsinceTV.setVisibility(View.GONE);
                UnemployedsincePicker.setVisibility(View.GONE);
                LastCompanyTV.setVisibility(View.GONE);
                LastSalaryTV.setVisibility(View.GONE);
                LastSalaryET.setVisibility(View.GONE);
                SourceofIncomeTV.setVisibility(View.GONE);
                SourceofIncome_Spinner.setVisibility(View.GONE);

                //disable case7 fields
                WorktypeTV.setVisibility(View.GONE);
                WorktypeET.setVisibility(View.GONE);
                dailyIncomeTV.setVisibility(View.GONE);
                dailyIncomeET.setVisibility(View.GONE);

                //disable case8 fields
                RetireDateTV.setVisibility(View.GONE);
                RetireDatePicker.setVisibility(View.GONE);

                //enable case5 fields
                CompanyTV.setVisibility(View.VISIBLE);
                CompanyET.setVisibility(View.VISIBLE);
                JobTitleTV.setVisibility(View.VISIBLE);
                JobTitleET.setVisibility(View.VISIBLE);
                MonthlyIncomeTV.setVisibility(View.VISIBLE);
                MonthlyIncomeET.setVisibility(View.VISIBLE);
                YearlyIncomeTV.setVisibility(View.VISIBLE);
                YearlyIncomeET.setVisibility(View.VISIBLE);
                break;

            case 6:
                //disable case1,2,3 fields
                InstituteTV.setVisibility(View.GONE);
                InstituteET.setVisibility(View.GONE);
                fatherjobTV.setVisibility(View.GONE);
                fatherJob_spinner.setVisibility(View.GONE);

                //disable case4,5 fields
                CompanyTV.setVisibility(View.GONE);
                JobTitleTV.setVisibility(View.GONE);
                JobTitleET.setVisibility(View.GONE);
                MonthlyIncomeTV.setVisibility(View.GONE);
                MonthlyIncomeET.setVisibility(View.GONE);
                YearlyIncomeTV.setVisibility(View.GONE);
                YearlyIncomeET.setVisibility(View.GONE);

                //disable case7 fields
                WorktypeTV.setVisibility(View.GONE);
                WorktypeET.setVisibility(View.GONE);
                dailyIncomeTV.setVisibility(View.GONE);
                dailyIncomeET.setVisibility(View.GONE);

                //disable case8 fields
                RetireDateTV.setVisibility(View.GONE);
                RetireDatePicker.setVisibility(View.GONE);
                CompanyTV.setVisibility(View.GONE);

                //enable case6 fields
                UnemployedsinceTV.setVisibility(View.VISIBLE);
                UnemployedsincePicker.setVisibility(View.VISIBLE);
                LastCompanyTV.setVisibility(View.VISIBLE);
                CompanyET.setVisibility(View.VISIBLE);
                LastSalaryTV.setVisibility(View.VISIBLE);
                LastSalaryET.setVisibility(View.VISIBLE);
                SourceofIncomeTV.setVisibility(View.VISIBLE);
                SourceofIncome_Spinner.setVisibility(View.VISIBLE);
                break;

            case 7:
                //disable case1,2,3 fields
                InstituteTV.setVisibility(View.GONE);
                InstituteET.setVisibility(View.GONE);
                fatherjobTV.setVisibility(View.GONE);
                fatherJob_spinner.setVisibility(View.GONE);

                //disable case4,5 fields
                CompanyTV.setVisibility(View.GONE);
                CompanyET.setVisibility(View.GONE);
                JobTitleTV.setVisibility(View.GONE);
                JobTitleET.setVisibility(View.GONE);
                MonthlyIncomeTV.setVisibility(View.GONE);
                MonthlyIncomeET.setVisibility(View.GONE);
                YearlyIncomeTV.setVisibility(View.GONE);
                YearlyIncomeET.setVisibility(View.GONE);

                //disable case6 fields
                UnemployedsinceTV.setVisibility(View.GONE);
                UnemployedsincePicker.setVisibility(View.GONE);
                LastCompanyTV.setVisibility(View.GONE);
                CompanyET.setVisibility(View.GONE);
                LastSalaryTV.setVisibility(View.GONE);
                LastSalaryET.setVisibility(View.GONE);
                SourceofIncomeTV.setVisibility(View.GONE);
                SourceofIncome_Spinner.setVisibility(View.GONE);

                //disable case8 fields
                RetireDateTV.setVisibility(View.GONE);
                RetireDatePicker.setVisibility(View.GONE);
                CompanyTV.setVisibility(View.GONE);
                CompanyET.setVisibility(View.GONE);
                LastSalaryTV.setVisibility(View.GONE);
                LastSalaryET.setVisibility(View.GONE);

                //enable case7 fields
                WorktypeTV.setVisibility(View.VISIBLE);
                WorktypeET.setVisibility(View.VISIBLE);
                dailyIncomeTV.setVisibility(View.VISIBLE);
                dailyIncomeET.setVisibility(View.VISIBLE);
                break;

            case 8:
                //disable case1,2,3 fields
                InstituteTV.setVisibility(View.GONE);
                InstituteET.setVisibility(View.GONE);
                fatherjobTV.setVisibility(View.GONE);
                fatherJob_spinner.setVisibility(View.GONE);

                //disable case4,5 fields
                JobTitleTV.setVisibility(View.GONE);
                JobTitleET.setVisibility(View.GONE);
                MonthlyIncomeTV.setVisibility(View.GONE);
                MonthlyIncomeET.setVisibility(View.GONE);
                YearlyIncomeTV.setVisibility(View.GONE);
                YearlyIncomeET.setVisibility(View.GONE);

                //disable case6 fields
                UnemployedsinceTV.setVisibility(View.GONE);
                UnemployedsincePicker.setVisibility(View.GONE);
                LastCompanyTV.setVisibility(View.GONE);
                LastSalaryTV.setVisibility(View.GONE);
                LastSalaryET.setVisibility(View.GONE);
                SourceofIncomeTV.setVisibility(View.GONE);
                SourceofIncome_Spinner.setVisibility(View.GONE);

                //disable case7 fields
                WorktypeTV.setVisibility(View.GONE);
                WorktypeET.setVisibility(View.GONE);
                dailyIncomeTV.setVisibility(View.GONE);
                dailyIncomeET.setVisibility(View.GONE);

                //enable case8 fields
                RetireDateTV.setVisibility(View.VISIBLE);
                RetireDatePicker.setVisibility(View.VISIBLE);
                CompanyTV.setVisibility(View.VISIBLE);
                CompanyET.setVisibility(View.VISIBLE);
                LastSalaryTV.setVisibility(View.VISIBLE);
                LastSalaryET.setVisibility(View.VISIBLE);
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
