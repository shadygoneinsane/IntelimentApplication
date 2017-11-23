package dass.vikeshkumar.intelimentapplication.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import dass.vikeshkumar.intelimentapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    private int page;

    public DetailsFragment() {
    }

    public static DetailsFragment newInstance(int index) {
        DetailsFragment frag = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt("pageNo", index);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        page = getArguments().getInt("pageNo");
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        TextView fragName = view.findViewById(R.id.fragment_name);
        fragName.setText(getString(R.string.fragment_name) + page);
        FrameLayout parentFrag = view.findViewById(R.id.parentFrag);
        parentFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Fragment Number : " + page, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
