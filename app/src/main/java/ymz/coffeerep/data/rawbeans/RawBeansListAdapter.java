package ymz.coffeerep.data.rawbeans;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import lombok.Getter;
import lombok.Setter;
import ymz.coffeerep.R;
import ymz.coffeerep.databinding.RawbeansItemBinding;
import ymz.coffeerep.scenes.list.BeansListFragment;

/**
 * processes about rawbeans list
 */
public class RawBeansListAdapter extends ListAdapter<RawBeans, RawBeansViewHolder> {

    @Getter
    @Setter
    private RawBeans _selectedRawBeans;

    public MutableLiveData<Boolean> selected = new MutableLiveData<>(false);

    //constructor
    public RawBeansListAdapter(@NonNull DiffUtil.ItemCallback<RawBeans> diffCallback) {
        super(diffCallback);
        _selectedRawBeans = null;
    }

    //make RawBeansViewHolder create 1 line
    @Override
    public RawBeansViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RawBeansViewHolder vh = RawBeansViewHolder.create(parent);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int pos = vh.getBindingAdapterPosition();
                _selectedRawBeans = getItem(pos);
                selected.setValue(true);
                Log.d("YMZdebug", "[RawBeansListAdapter.RawBeansViewHolder]: item selected.");
            }
        });

        return vh;
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