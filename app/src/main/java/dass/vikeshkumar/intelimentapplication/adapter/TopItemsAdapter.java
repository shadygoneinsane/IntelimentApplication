package dass.vikeshkumar.intelimentapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import dass.vikeshkumar.intelimentapplication.R;
import dass.vikeshkumar.intelimentapplication.fragments.FragmentTopRecyclerItems;

public class TopItemsAdapter extends RecyclerView.Adapter<TopItemsAdapter.ViewHolder> {

    private ArrayList<String> itemsDataSet;
    private FragmentTopRecyclerItems.ItemClickListener mListener;

    public TopItemsAdapter(ArrayList<String> dataSet, FragmentTopRecyclerItems.ItemClickListener Listener) {
        this.itemsDataSet = dataSet;
        this.mListener = Listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView item_name;

        private ViewHolder(View v) {
            super(v);
            item_name = v.findViewById(R.id.itemName);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item_top_list, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.item_name.setText(itemsDataSet.get(position));
        viewHolder.item_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onClick("Clicked at Item " + (position + 1));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemsDataSet.size();
    }
}