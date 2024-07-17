package ymz.coffeerep.data.readymadecoffee;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ymz.coffeerep.R;
import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffee;
import ymz.coffeerep.data.readymadecoffee.ReadyMadeCoffeeViewHolder;

/**
 * processes about each line of readymadecoffee list
 */
public class ReadyMadeCoffeeViewHolder extends RecyclerView.ViewHolder {

    //maybe binding is usable only in Fragment
    //private ReadyMadeCoffeeItemBinding _binding;

    private final TextView ReadyMadeCoffeeItemNameView;
    private final TextView ReadyMadeCoffeeItemTimeView;
    private final RatingBar ReadyMadeCoffeeReviewRatingBar;

    //constructor
    private ReadyMadeCoffeeViewHolder(View itemView) {
        super(itemView);
        ReadyMadeCoffeeItemNameView = itemView.findViewById(R.id.name_readymadecoffee_item);
        ReadyMadeCoffeeItemTimeView = itemView.findViewById(R.id.time_readymadecoffee_item);
        ReadyMadeCoffeeReviewRatingBar = itemView.findViewById(R.id.review_ratingbar_readymadecoffee_item);
    }

    //set specific value to each line
    public void bind(ReadyMadeCoffee readymadecoffee) {
        ReadyMadeCoffeeItemNameView.setText(readymadecoffee.getReadymadecoffee_name());
        ReadyMadeCoffeeItemTimeView.setText(
                DateFormat.format("yyyy-MM-dd hh:mm:ss", readymadecoffee.getRegistered_time())
        );
        ReadyMadeCoffeeReviewRatingBar.setRating(readymadecoffee.getReadymadecoffee_review());

        //NullPointerException occurs below
        /*
        _binding.nameReadyMadeCoffeeItem.setText(readymadecoffee.getReadyMadeCoffeeName());
        _binding.timeReadyMadeCoffeeItem.setText(
                DateFormat.format("yyyy-MM-dd hh:mm:ss", readymadecoffee.getRegisteredTime())
        );
        */
    }

    //create 1 line and return it
    static ReadyMadeCoffeeViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.readymadecoffee_item, parent, false);
        return new ReadyMadeCoffeeViewHolder(view);
    }
}
