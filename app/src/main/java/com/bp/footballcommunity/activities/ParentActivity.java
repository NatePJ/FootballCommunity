package com.bp.footballcommunity.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.bp.footballcommunity.PageFragment;
import com.bp.footballcommunity.R;
import com.bp.footballcommunity.adapters.ParentFragmentPagerAdapter;
import com.bp.footballcommunity.fragments.FocusFragment;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by BP on 2017/6/2.
 */
public class ParentActivity extends AppCompatActivity{
    private static final String TAG = "ParentActivity";
    private Toolbar mToolbar = null;
    private TabLayout mTabLayout = null;
    private ViewPager mViewPager = null;
    private ParentFragmentPagerAdapter mParentFragmentPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);

        /**
         * 实例化组件
         */
        mToolbar = (Toolbar)findViewById(R.id.parent_toolbar);
        mTabLayout = (TabLayout)findViewById(R.id.parent_tablayout);
        mViewPager = (ViewPager)findViewById(R.id.parent_viewpager);

        /**
         * 设置pageadapter
         */
        Log.d(TAG,"pager1");
        mParentFragmentPagerAdapter = new ParentFragmentPagerAdapter(getSupportFragmentManager(),this);
        Log.d(TAG,"pager2");
        mViewPager.setAdapter(mParentFragmentPagerAdapter);
        Log.d(TAG,"pager3");

        /**
         * 设置tabLayout
         */
        mTabLayout.setupWithViewPager(mViewPager);
//        Log.d(TAG,"tab1");
        /**
         * TabLayout.MODE_FIXED 使tab123分开
         * TabLayout.MODE_SCROLLABLE 使tab123连在一起
         */
        mTabLayout.setTabMode(TabLayout.MODE_FIXED  );
    }
}
