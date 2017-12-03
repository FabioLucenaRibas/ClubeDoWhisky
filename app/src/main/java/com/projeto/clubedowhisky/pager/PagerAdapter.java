package com.projeto.clubedowhisky.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.projeto.clubedowhisky.tabs.HistoryFragment;
import com.projeto.clubedowhisky.tabs.MyTicketsFragment;
import com.projeto.clubedowhisky.tabs.QrcodeFragment;

/**
 * Created by pedrolima on 30/11/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {
    private final String[] titles = new String[]{"", "Meus Tickets", "Histórico"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new QrcodeFragment();
            case 1:
                return new MyTicketsFragment();
            case 2:
                return new HistoryFragment();
        }
        return null;
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
