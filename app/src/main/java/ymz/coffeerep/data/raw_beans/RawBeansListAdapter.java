package ymz.coffeerep.data.raw_beans;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class RawBeansListAdapter extends ListAdapter<RawBeans, RawBeansViewHolder> {

    //constructor
    public RawBeansListAdapter(@NonNull DiffUtil.ItemCallback<RawBeans> diffCallback) {
        super(diffCallback);
    }

    @Override
    public RawBeansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RawBeansViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(RawBeansViewHolder holder, int position) {
        RawBeans current = getItem(position);
        holder.bind(current.getRawBeansName());
        //holder.bind(current.getRawBeansId());
    }

    public static class WordDiff extends DiffUtil.ItemCallback<RawBeans> {

        @Override
        public boolean areItemsTheSame(@NonNull RawBeans oldItem, @NonNull RawBeans newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull RawBeans oldItem, @NonNull RawBeans newItem) {
            return oldItem.getRawBeansName().equals(newItem.getRawBeansName());
            //return oldItem.getRawBeansId() == newItem.getRawBeansId();
        }
    }
}