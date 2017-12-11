package com.projeto.clubedowhisky.pager;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.projeto.clubedowhisky.R;
import com.projeto.clubedowhisky.tabs.HistoryFragment;
import com.projeto.clubedowhisky.tabs.MyTicketsFragment;
import com.projeto.clubedowhisky.tabs.QrcodeFragment;

import java.util.List;

/**
 * Created by pedrolima on 30/11/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private final String[] titles;
    private List<Fragment> mFragment;

    public PagerAdapter(FragmentManager fm, List<Fragment> fragments, Context context) {
        super(fm);
        mFragment = fragments;
        titles = new String[]{"", context.getString(R.string.label_my_tickets), context.getString(R.string.label_historic)};
    }
    @Override
    public Fragment getItem(int position) {
    return mFragment.get(position);
        //        switch (position){
//            case 0:
//                return new QrcodeFragment();
//            case 1:
//                return new MyTicketsFragment();
//            case 2:
//                return new HistoryFragment();
//        }
//        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
