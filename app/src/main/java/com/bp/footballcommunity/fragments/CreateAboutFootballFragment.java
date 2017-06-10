package com.bp.footballcommunity.fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.bp.footballcommunity.R;
import com.bp.footballcommunity.activities.ParentActivity;
import com.bp.footballcommunity.utilities.Constant;
import com.bp.footballcommunity.utilities.UploadUtility;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by BP on 2017/6/4.
 */
public class CreateAboutFootballFragment extends DialogFragment{
    private TextView mGroundText;
    private Spinner mGroundSpinner;
    private TextView mTimeText;
    private EditText mTimeEdit;
    private TextView mPeopleText;
    private EditText mPeopleEdit;
    private Button mButton;
    private static final int REQUEST_DATE = 0;
    private static final String DIALOG_DATE = "date";
    private String groundText = null;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_publish_about_football,null);

        mGroundText = (TextView)v.findViewById(R.id.dialog_create_groundtext);
        mGroundSpinner = (Spinner)v.findViewById(R.id.dialog_create_groundspinner);

        mTimeText = (TextView)v.findViewById(R.id.dialog_create_timetext);
        mTimeEdit = (EditText)v.findViewById(R.id.dialog_create_timeedit);

        mPeopleText = (TextView)v.findViewById(R.id.dialog_create_peopletext);
        mPeopleEdit = (EditText)v.findViewById(R.id.dialog_create_peopleedit);

        mButton = (Button)v.findViewById(R.id.dialog_create_button);

        /**
         * spinner listener
         */
        mGroundSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                     @Override
                                                     public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                         groundText = (String) mGroundSpinner.getSelectedItem();
                                                     }

                                                     @Override
                                                     public void onNothingSelected(AdapterView<?> adapterView) {

                                                     }
                                                 });
        /**
         * time listener
         */
        mTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity()
                        .getSupportFragmentManager();
//                DatePickerFragment dialog = new DatePickerFragment();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance("设置");
                /**
                 * 设置目标fragment方便回传数据
                 */
                dialog.setTargetFragment(CreateAboutFootballFragment.this,REQUEST_DATE);
                dialog.show(fm,DIALOG_DATE);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String playground = String.valueOf(groundText);
                String time = String.valueOf(mTimeEdit.getText());
                String people = mPeopleEdit.getText().toString();

                UploadUtility.uploadAboutMessage(playground, time, people);

//                Intent i = new Intent(getActivity(),ParentActivity.class);
//                startActivity(i);
            }
        });
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.about_football)
                .create();
    }
    /**
     * 响应datepicker对话框
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != Activity.RESULT_OK)
            return;
        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mTimeEdit.setText(setAboutDate(date));

        }
    }

    public String setAboutDate(Date date){
        String s = date.toString();
        SimpleDateFormat sf1 = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
        Date setdate = null;
        try {
            setdate = sf1.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        return sf2.format(setdate);
    }
}
