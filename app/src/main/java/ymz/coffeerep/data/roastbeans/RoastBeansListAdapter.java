package ymz.coffeerep.data.roastbeans;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import lombok.Getter;
import lombok.Setter;
import ymz.coffeerep.data.roastbeans.RoastBeans;
import ymz.coffeerep.data.roastbeans.RoastBeansViewHolder;

/**
 * processes about roastbeans list
 */
public class RoastBeansListAdapter extends ListAdapter<RoastBeans, RoastBeansViewHolder> {

    @Getter
    @Setter
    private RoastBeans _selectedRoastBeans;

    public MutableLiveData<Boolean> selected = new MutableLiveData<>(false);

    //constructor
    public RoastBeansListAdapter(@NonNull DiffUtil.ItemCallback<RoastBeans> diffCallback) {
        super(diffCallback);
        _selectedRoastBeans = null;
    }

    //make RoastBeansViewHolder create 1 line
    @Override
    public RoastBeansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RoastBeansViewHolder vh = RoastBeansViewHolder.create(parent);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int pos = vh.getBindingAdapterPosition();
                _selectedRoastBeans = getItem(pos);
                selected.setValue(true);
                Log.d("YMZdebug", "[RoastBeansListAdapter.RoastBeansViewHolder]: item selected.");
            }
        });

        return vh;
    }

    //set values to view depending on viewholder and position in list
    @Override
    public void onBindViewHolder(RoastBeansViewHolder holder, int position) {
        RoastBeans current = getItem(position);
        holder.bind(current);
    }

    //about difference of new/old items
    public static class WordDiff extends DiffUtil.ItemCallback<RoastBeans> {
        //check if the new/old items are the same
        @Override
        public boolean areItemsTheSame(@NonNull RoastBeans oldItem, @NonNull RoastBeans newItem) {
            return oldItem == newItem;
        }

        //check if the new/old items' content displayed are the same
        @Override
        public boolean areContentsTheSame(@NonNull RoastBeans oldItem, @NonNull RoastBeans newItem) {
            return oldItem.getRoastbeans_name().equals(newItem.getRoastbeans_name())
                    && oldItem.getRegistered_time() == newItem.getRegistered_time();
        }
    }
}
