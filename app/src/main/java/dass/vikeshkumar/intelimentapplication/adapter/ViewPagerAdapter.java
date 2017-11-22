package dass.vikeshkumar.intelimentapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import dass.vikeshkumar.intelimentapplication.fragments.DetailsFragment;

/**
 * Created by a593038 on 03-10-2016.
 * Adapter for ViewPager using Fragments
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int NumbOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int mNumbOfTabs) {
        super(fm);
        this.NumbOfTabs = mNumbOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return DetailsFragment.newInstance(position, "Page # 1");
            case 1: // Fragment # 0 - This will show SecondFragment different title
                return DetailsFragment.newInstance(position, "Page # 2");
            case 2: // Fragment # 1 - This will show ThirdFragment
                return DetailsFragment.newInstance(position, "Page # 3");
            case 3: // Fragment # 1 - This will show FourthFragment
                return DetailsFragment.newInstance(position, "Page # 3");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}
