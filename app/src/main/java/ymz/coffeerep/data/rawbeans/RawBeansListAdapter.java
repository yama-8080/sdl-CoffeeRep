package ymz.coffeerep.data.rawbeans;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

/**
 * processes about rawbeans list
 */
public class RawBeansListAdapter extends ListAdapter<RawBeans, RawBeansViewHolder> {

    //constructor
    public RawBeansListAdapter(@NonNull DiffUtil.ItemCallback<RawBeans> diffCallback) {
        super(diffCallback);
    }

    //make RawBeansViewHolder create 1 line
    @Override
    public RawBeansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RawBeansViewHolder.create(parent);
    }

    //set values to view depending on viewholder and position in list
    @Override
    public void onBindViewHolder(RawBeansViewHolder holder, int position) {
        RawBeans current = getItem(position);
        holder.bind(current);
    }

    //about difference of new/old items
    public static class WordDiff extends DiffUtil.ItemCallback<RawBeans> {
        //check if the new/old items are the same
        @Override
        public boolean areItemsTheSame(@NonNull RawBeans oldItem, @NonNull RawBeans newItem) {
            return oldItem == newItem;
        }

        //check if the new/old items' content displayed are the same
        @Override
        public boolean areContentsTheSame(@NonNull RawBeans oldItem, @NonNull RawBeans newItem) {
            return oldItem.getRawbeans_name().equals(newItem.getRawbeans_name())
                    && oldItem.getRegistered_time() == newItem.getRegistered_time();
        }
    }
}