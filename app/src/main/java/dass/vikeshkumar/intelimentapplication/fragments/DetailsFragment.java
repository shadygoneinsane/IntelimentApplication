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

    //For storing variables - title and page number
    private String title;
    private int page;
    private FrameLayout parentFrag;

    public DetailsFragment() {
        // Required empty public constructor
    }

    //For creating fragment with arguments here using newInstance() constructor
    public static DetailsFragment newInstance(int index, String title) {
        DetailsFragment f = new DetailsFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("titleName", title);
        args.putInt("pageNo", index);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        page = getArguments().getInt("pageNo");
        title = getArguments().getString("titleName");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        TextView fragName = view.findViewById(R.id.fragment_name);
        fragName.setText(getString(R.string.fragment_name) + page);
        parentFrag = view.findViewById(R.id.parentFrag);
        parentFrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Fragment Number : " + page, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

}
