package ymz.coffeerep.data.selfdripcoffee;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import lombok.Getter;
import lombok.Setter;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffee;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffeeViewHolder;

/**
 * processes about selfdripcoffee list
 */
public class SelfDripCoffeeListAdapter extends ListAdapter<SelfDripCoffee, SelfDripCoffeeViewHolder> {

    @Getter
    @Setter
    private SelfDripCoffee _selectedSelfDripCoffee;

    public MutableLiveData<Boolean> selected = new MutableLiveData<>(false);

    //constructor
    public SelfDripCoffeeListAdapter(@NonNull DiffUtil.ItemCallback<SelfDripCoffee> diffCallback) {
        super(diffCallback);
        _selectedSelfDripCoffee = null;
    }

    //make SelfDripCoffeeViewHolder create 1 line
    @Override
    public SelfDripCoffeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SelfDripCoffeeViewHolder vh = SelfDripCoffeeViewHolder.create(parent);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int pos = vh.getBindingAdapterPosition();
                _selectedSelfDripCoffee = getItem(pos);
                selected.setValue(true);
                Log.d("YMZdebug", "[SelfDripCoffeeListAdapter.SelfDripCoffeeViewHolder]: item selected.");
            }
        });

        return vh;
    }

    //set values to view depending on viewholder and position in list
    @Override
    public void onBindViewHolder(SelfDripCoffeeViewHolder holder, int position) {
        SelfDripCoffee current = getItem(position);
        holder.bind(current);
    }

    //about difference of new/old items
    public static class WordDiff extends DiffUtil.ItemCallback<SelfDripCoffee> {
        //check if the new/old items are the same
        @Override
        public boolean areItemsTheSame(@NonNull SelfDripCoffee oldItem, @NonNull SelfDripCoffee newItem) {
            return oldItem == newItem;
        }

        //check if the new/old items' content displayed are the same
        @Override
        public boolean areContentsTheSame(@NonNull SelfDripCoffee oldItem, @NonNull SelfDripCoffee newItem) {
            return oldItem.getSelfdripcoffee_name().equals(newItem.getSelfdripcoffee_name())
                    && oldItem.getRegistered_time() == newItem.getRegistered_time();
        }
    }
}
