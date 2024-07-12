package ymz.coffeerep.data.rawbeans;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ymz.coffeerep.R;
import ymz.coffeerep.databinding.RawbeansItemBinding;

/**
 * processes about each line of rawbeans list
 */
class RawBeansViewHolder extends RecyclerView.ViewHolder {

    private RawbeansItemBinding _binding;

    //constructor
    private RawBeansViewHolder(View itemView) {
        super(itemView);
    }

    //set specific value to each line
    public void bind(RawBeans rawbeans) {
        _binding.nameRawbeansItem.setText(rawbeans.getRawBeansName());
        _binding.timeRawbeansItem.setText(
                DateFormat.format("yyyy-MM-dd hh:mm:ss", rawbeans.getRegisteredTime())
        );
    }

    //create 1 line and return it
    static RawBeansViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rawbeans_item, parent, false);
        return new RawBeansViewHolder(view);
    }
}
