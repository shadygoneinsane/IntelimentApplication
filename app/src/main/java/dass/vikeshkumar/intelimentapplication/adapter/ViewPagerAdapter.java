package dass.vikeshkumar.intelimentapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import dass.vikeshkumar.intelimentapplication.fragments.DetailsFragment;

/**
 * Adapter for ViewPager using Fragments
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return DetailsFragment.newInstance(position + 1);
            case 1:
                return DetailsFragment.newInstance(position + 1);
            case 2:
                return DetailsFragment.newInstance(position + 1);
            case 3:
                return DetailsFragment.newInstance(position + 1);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
