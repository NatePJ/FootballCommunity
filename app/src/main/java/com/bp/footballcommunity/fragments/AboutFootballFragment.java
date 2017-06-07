package com.bp.footballcommunity.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bp.footballcommunity.R;

import org.w3c.dom.Text;

/**
 * Created by BP on 2017/6/4.
 */
public class AboutFootballFragment extends Fragment{

    private static final String TAG = "AboutFootballFragment";
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final String DIALOG_DATE = "date";
    private ImageView mImageView;
    private TextView mTextView;
    private TextView mDetailView;
    private TextView mDetailView2;
    private TextView mDetailView3;
    private TextView mDetailView4;
    private Button mButton;

    private ImageView mImageView2;
    private TextView mTextView2;
    private TextView mDetailView5;
    private TextView mDetailView6;
    private TextView mDetailView7;
    private TextView mDetailView8;
    private Button mButton2;

    public static AboutFootballFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        AboutFootballFragment aboutFootballFragment = new AboutFootballFragment();
        aboutFootballFragment.setArguments(args);
        return aboutFootballFragment;
    }
    public AboutFootballFragment(){

    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_about_football, parent, false);

        mImageView = (ImageView)v.findViewById(R.id.about_football_head_image);
        mTextView = (TextView)v.findViewById(R.id.about_football_head_name);
        mDetailView = (TextView)v.findViewById(R.id.about_football_detail);
        mDetailView2 = (TextView)v.findViewById(R.id.about_football_detail2);
        mDetailView3 = (TextView)v.findViewById(R.id.about_football_detail3);
        mDetailView4 = (TextView)v.findViewById(R.id.about_football_detail4);
        mButton = (Button)v.findViewById(R.id.add_about_football);

        mImageView2 = (ImageView)v.findViewById(R.id.about_football_head_image2);
        mTextView2 = (TextView)v.findViewById(R.id.about_football_head_name2);
        mDetailView5 = (TextView)v.findViewById(R.id.about_football_detail5);
        mDetailView6 = (TextView)v.findViewById(R.id.about_football_detail6);
        mDetailView7 = (TextView)v.findViewById(R.id.about_football_detail7);
        mDetailView8 = (TextView)v.findViewById(R.id.about_football_detail8);
        mButton2 = (Button)v.findViewById(R.id.add_about_football2);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                CreateAboutFootballFragment dialog = new CreateAboutFootballFragment();
                dialog.show(fm,DIALOG_DATE);
            }
        });
        return v;
    }
}
