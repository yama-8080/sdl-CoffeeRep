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

    //maybe binding is usable only in Fragment
    //private RawbeansItemBinding _binding;

    private final TextView RawBeansItemNameView;
    private final TextView RawBeansItemTimeView;

    //constructor
    private RawBeansViewHolder(View itemView) {
        super(itemView);
        RawBeansItemNameView = itemView.findViewById(R.id.name_rawbeans_item);
        RawBeansItemTimeView = itemView.findViewById(R.id.time_rawbeans_item);
    }

    //set specific value to each line
    public void bind(RawBeans rawbeans) {
        RawBeansItemNameView.setText(rawbeans.getRawbeans_name());
        RawBeansItemTimeView.setText(
                DateFormat.format("yyyy-MM-dd hh:mm:ss", rawbeans.getRegistered_time())
        );

        //NullPointerException occurs below
        /*
        _binding.nameRawbeansItem.setText(rawbeans.getRawBeansName());
        _binding.timeRawbeansItem.setText(
                DateFormat.format("yyyy-MM-dd hh:mm:ss", rawbeans.getRegisteredTime())
        );
        */
    }

    //create 1 line and return it
    static RawBeansViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rawbeans_item, parent, false);
        return new RawBeansViewHolder(view);
    }
}
