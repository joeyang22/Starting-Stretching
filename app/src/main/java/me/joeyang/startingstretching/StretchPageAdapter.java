package me.joeyang.startingstretching;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Joe Yang on 6/14/2015.
 */
public class StretchPageAdapter extends FragmentStatePagerAdapter {
    private String[] titles = {"Tab 1", "Tab 2"};
    public StretchPageAdapter (FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int i){
        Fragment fragment = new StretchFragment();
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
