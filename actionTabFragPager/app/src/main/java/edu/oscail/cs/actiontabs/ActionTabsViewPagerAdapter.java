package edu.oscail.cs.actiontabs;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by Fanglin Chen on 12/18/14.
 *
 */

public class ActionTabsViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments;

    public static final int CHAT = 0;
    public static final int FIND = 1;
    public static final int MEET = 2;
    public static final int PARTY = 3;
    public static final String UI_TAB_CHAT = "CHAT";
    public static final String UI_TAB_FIND = "FIND";
    public static final String UI_TAB_MEET = "MEET";
    public static final String UI_TAB_PARTY = "PART";

    public ActionTabsViewPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments){
        super(fm);
        this.fragments = fragments;
    }

    public Fragment getItem(int pos){
        return fragments.get(pos);
    }

    public int getCount(){
        return fragments.size();
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case CHAT:
                return UI_TAB_CHAT;
            case FIND:
                return UI_TAB_FIND;
            case MEET:
                return UI_TAB_MEET;
            case PARTY:
                return UI_TAB_PARTY;
            default:
                break;
        }
        return null;
    }
}
