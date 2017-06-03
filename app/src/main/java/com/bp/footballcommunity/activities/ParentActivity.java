package com.bp.footballcommunity.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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
         * 设置ToolBar
         */
        setSupportActionBar(mToolbar);
        //添加toolbar返回按钮
//        if(getSupportActionBar() != null)
//            // Enable the Up button
//            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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

    /**
     * 在menu设置了显示在Toolbar上的item之后，
     * 需要把Toolbar和menu关联才能显示定义的item，
     * 重写Activity提供的onCreateOptionsMenu()方法可以关联Toolbar和menu。
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 重写onOptionsItemSelected()方法就可以对Toolbar的按钮设置监听。
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_favorite:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
//                Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ParentActivity.this,PublishFootballActivity.class);
                startActivity(i);
                return true;
            case R.id.action_settings:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                Toast.makeText(this,"hello",Toast.LENGTH_SHORT).show();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

}
