package dass.vikeshkumar.intelimentapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dass.vikeshkumar.intelimentapplication.R;
import dass.vikeshkumar.intelimentapplication.fragments.FragmentTopRecyclerItems;

/**
 * Created by vikesh on 22-11-2017.
 */
public class TopItemAdapter extends RecyclerView.Adapter<TopItemAdapter.ViewHolder> {

    private ArrayList<String> mDataSet;
    private FragmentTopRecyclerItems.OnFragmentInteractionListener mListener;

    public TopItemAdapter(ArrayList<String> dataSet, FragmentTopRecyclerItems.OnFragmentInteractionListener Listener) {
        this.mDataSet = dataSet;
        this.mListener = Listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tV_name;

        private ViewHolder(View v) {
            super(v);
            tV_name = v.findViewById(R.id.textView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new TopItemAdapter.ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_top_list, viewGroup, false));
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final TopItemAdapter.ViewHolder viewHolder, final int position) {
        String object = mDataSet.get(position);
        viewHolder.tV_name.setText(object);
        viewHolder.tV_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, "Clicked at item " + position, Toast.LENGTH_SHORT).show();
                if (mListener != null) {
                    mListener.onFragmentInteraction("Clicked at item " + (position + 1));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}