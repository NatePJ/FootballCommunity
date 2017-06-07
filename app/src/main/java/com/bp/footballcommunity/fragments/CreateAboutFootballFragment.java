package com.bp.footballcommunity.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bp.footballcommunity.R;

/**
 * Created by BP on 2017/6/4.
 */
public class CreateAboutFootballFragment extends DialogFragment{
    private TextView mGroundText;
    private EditText mGroundEdit;
    private TextView mTimeText;
    private EditText mTimeEdit;
    private TextView mPeopleText;
    private EditText mPeopleEdit;
    private Button mButton;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_publish_about_football,null);

        mGroundText = (TextView)v.findViewById(R.id.dialog_create_groundtext);
        mGroundEdit = (EditText)v.findViewById(R.id.dialog_create_groundedit);

        mTimeText = (TextView)v.findViewById(R.id.dialog_create_timetext);
        mTimeEdit = (EditText)v.findViewById(R.id.dialog_create_timeedit);

        mPeopleText = (TextView)v.findViewById(R.id.dialog_create_peopletext);
        mPeopleEdit = (EditText)v.findViewById(R.id.dialog_create_peopleedit);

        mButton = (Button)v.findViewById(R.id.dialog_create_button);
        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.about_football)
                .setPositiveButton(android.R.string.ok,null)
                .create();
    }
}
