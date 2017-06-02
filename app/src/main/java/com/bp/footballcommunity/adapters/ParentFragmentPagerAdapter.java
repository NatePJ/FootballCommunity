package com.bp.footballcommunity.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.widget.ListView;

import com.bp.footballcommunity.PageFragment;
import com.bp.footballcommunity.fragments.FocusFragment;

import java.util.ArrayList;

/**
 * Created by BP on 2017/6/2.
 */
public class ParentFragmentPagerAdapter extends FragmentPagerAdapter{
//
    final int PAGE_COUNT = 4;
    private String tabTitles[] = new String[]{"关注","约球","推荐","事务"};
    private Context context;
    private ListView mListView;
    public ParentFragmentPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return FocusFragment.newInstance(position + 1);
            case 1:
                return PageFragment.newInstance(position + 1);
            case 2:
                return PageFragment.newInstance(position + 1);
            case 3:
                return PageFragment.newInstance(position + 1);
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
