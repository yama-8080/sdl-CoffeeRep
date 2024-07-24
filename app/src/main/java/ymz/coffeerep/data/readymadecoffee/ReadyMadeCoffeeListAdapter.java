package ymz.coffeerep.data.readymadecoffee;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import lombok.Getter;
import lombok.Setter;
import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffee;
import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffeeViewHolder;

/**
 * processes about readymadecoffee list
 */
public class ReadyMadeCoffeeListAdapter extends ListAdapter<ReadyMadeCoffee, ReadyMadeCoffeeViewHolder> {

    @Getter
    @Setter
    private ReadyMadeCoffee _selectedReadyMadeCoffee;

    public MutableLiveData<Boolean> selected = new MutableLiveData<>(false);

    //constructor
    public ReadyMadeCoffeeListAdapter(@NonNull DiffUtil.ItemCallback<ReadyMadeCoffee> diffCallback) {
        super(diffCallback);
        _selectedReadyMadeCoffee = null;
    }

    //make ReadyMadeCoffeeViewHolder create 1 line
    @Override
    public ReadyMadeCoffeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ReadyMadeCoffeeViewHolder vh = ReadyMadeCoffeeViewHolder.create(parent);

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int pos = vh.getBindingAdapterPosition();
                _selectedReadyMadeCoffee = getItem(pos);
                selected.setValue(true);
                Log.d("YMZdebug", "[ReadyMadeCoffeeListAdapter.ReadyMadeCoffeeViewHolder]: item selected.");
            }
        });

        return vh;
    }

    //set values to view depending on viewholder and position in list
    @Override
    public void onBindViewHolder(ReadyMadeCoffeeViewHolder holder, int position) {
        ReadyMadeCoffee current = getItem(position);
        holder.bind(current);
    }

    //about difference of new/old items
    public static class WordDiff extends DiffUtil.ItemCallback<ReadyMadeCoffee> {
        //check if the new/old items are the same
        @Override
        public boolean areItemsTheSame(@NonNull ReadyMadeCoffee oldItem, @NonNull ReadyMadeCoffee newItem) {
            return oldItem == newItem;
        }

        //check if the new/old items' content displayed are the same
        @Override
        public boolean areContentsTheSame(@NonNull ReadyMadeCoffee oldItem, @NonNull ReadyMadeCoffee newItem) {
            return oldItem.getReadymadecoffee_name().equals(newItem.getReadymadecoffee_name())
                    && oldItem.getRegistered_time() == newItem.getRegistered_time();
        }
    }
}