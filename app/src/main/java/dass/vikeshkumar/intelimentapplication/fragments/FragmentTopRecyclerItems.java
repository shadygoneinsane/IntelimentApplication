package dass.vikeshkumar.intelimentapplication.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dass.vikeshkumar.intelimentapplication.R;
import dass.vikeshkumar.intelimentapplication.adapter.TopItemAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTopRecyclerItems extends Fragment {
    //Listener used to communicate Item back to host activity
    private OnFragmentInteractionListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*since on click action on any item of list is to be communicated back
        so making sure the host activity has implemented the interface*/
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_item_1, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView mRecyclerView = view.findViewById(R.id.top_items_recycler_view);
        ArrayList<String> items = new ArrayList<>(5);
        for (int i = 0; i < 5; i++) {
            items.add("item " + (i + 1));
        }

        TopItemAdapter mAdapter = new TopItemAdapter(items, mListener);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    //Used to communicate with host activity
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String name);
    }
}