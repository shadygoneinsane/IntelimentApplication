package dass.vikeshkumar.intelimentapplication.fragments;

import android.content.Context;
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
 * Created by vikesh on 22-11-2017.
 * Fragment hosting view pager
 */
public class ViewPagerFragment extends Fragment {

    private int pageNo;

    public ViewPagerFragment() {

    }

    //For creating fragment with arguments here using newInstance() constructor
    public static ViewPagerFragment newInstance(int index, String title) {
        ViewPagerFragment f = new ViewPagerFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("pageNo", index);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        pageNo = getArguments().getInt("pageNo");
        return inflater.inflate(R.layout.fragment_default, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ViewPager viewpager = view.findViewById(R.id.viewpager);
        CircleIndicator indicator = view.findViewById(R.id.indicator);
        viewpager.setAdapter(new ViewPagerAdapter(getActivity().getSupportFragmentManager(), 4));
        indicator.setViewPager(viewpager);
        viewpager.setCurrentItem(pageNo);
    }
}
