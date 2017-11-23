package dass.vikeshkumar.intelimentapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dass.vikeshkumar.intelimentapplication.R;
import dass.vikeshkumar.intelimentapplication.adapter.ViewPagerAdapter;
import me.relex.circleindicator.CircleIndicator;

/**
 * Fragment hosting view pager
 */
public class ViewPagerFragment extends Fragment {

    public ViewPagerFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_default, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ViewPager viewpager = view.findViewById(R.id.viewpager);
        CircleIndicator indicator = view.findViewById(R.id.indicator);
        viewpager.setAdapter(new ViewPagerAdapter(getActivity().getSupportFragmentManager()));
        indicator.setViewPager(viewpager);
        viewpager.setCurrentItem(0);
    }
}
