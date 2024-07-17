package ymz.coffeerep.data.selfdripcoffee;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ymz.coffeerep.R;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffee;
import ymz.coffeerep.data.selfdripcoffee.SelfDripCoffeeViewHolder;

/**
 * processes about each line of selfdripcoffee list
 */
public class SelfDripCoffeeViewHolder extends RecyclerView.ViewHolder {

    //maybe binding is usable only in Fragment
    //private SelfDripCoffeeItemBinding _binding;

    private final TextView SelfDripCoffeeItemNameView;
    private final TextView SelfDripCoffeeItemTimeView;
    private final RatingBar SelfDripCoffeeReviewRatingBar;

    //constructor
    private SelfDripCoffeeViewHolder(View itemView) {
        super(itemView);
        SelfDripCoffeeItemNameView = itemView.findViewById(R.id.name_selfdripcoffee_item);
        SelfDripCoffeeItemTimeView = itemView.findViewById(R.id.time_selfdripcoffee_item);
        SelfDripCoffeeReviewRatingBar = itemView.findViewById(R.id.review_ratingbar_selfdripcoffee_item);
    }

    //set specific value to each line
    public void bind(SelfDripCoffee selfdripcoffee) {
        SelfDripCoffeeItemNameView.setText(selfdripcoffee.getSelfdripcoffee_name());
        SelfDripCoffeeItemTimeView.setText(
                DateFormat.format("yyyy-MM-dd hh:mm:ss", selfdripcoffee.getRegistered_time())
        );
        SelfDripCoffeeReviewRatingBar.setRating(selfdripcoffee.getSelfdripcoffee_review());

        //NullPointerException occurs below
        /*
        _binding.nameSelfDripCoffeeItem.setText(selfdripcoffee.getSelfDripCoffeeName());
        _binding.timeSelfDripCoffeeItem.setText(
                DateFormat.format("yyyy-MM-dd hh:mm:ss", selfdripcoffee.getRegisteredTime())
        );
        */
    }

    //create 1 line and return it
    static SelfDripCoffeeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.selfdripcoffee_item, parent, false);
        return new SelfDripCoffeeViewHolder(view);
    }
}
