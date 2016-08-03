package sda.oscail.edu.actiontabs;
/**
 * Created by ckirwan on 18/06/2016.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class MyPageAdapter extends FragmentStatePagerAdapter
{
    int mNumOfTabs;
    public static final int CHAT = 0;
    public static final int FIND = 1;
    public static final int MEET = 2;
    public static final int PARTY = 3;
    public static final String UI_TAB_CHAT = "CHAT";
    public static final String UI_TAB_FIND = "FIND";
    public static final String UI_TAB_MEET = "MEET";
    public static final String UI_TAB_PARTY = "PARTY";


    public MyPageAdapter(FragmentManager fm, int NumOfTabs)
    {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position)
    {

        switch (position)
        {
            case CHAT:
                ChatFragment chatTab = new ChatFragment();
                return chatTab;
            case FIND:
                FindFragment findTab = new FindFragment();
                return findTab;
            case MEET:
                MeetFragment meetTab = new MeetFragment();
                return meetTab;
            case PARTY:
                PartyFragment partyTab = new PartyFragment();
                return partyTab;
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return mNumOfTabs;
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