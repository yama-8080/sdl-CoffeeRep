package ymz.coffeerep.data.roastbeans;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ymz.coffeerep.R;
import ymz.coffeerep.data.roastbeans.RoastBeans;

/**
 * processes about each line of roastbeans list
 */
public class RoastBeansViewHolder extends RecyclerView.ViewHolder {

    //maybe binding is usable only in Fragment
    //private RoastbeansItemBinding _binding;

    private final TextView RoastBeansItemNameView;
    private final TextView RoastBeansItemTimeView;
    private final RatingBar RoastBeansReviewRatingBar;

    //constructor
    private RoastBeansViewHolder(View itemView) {
        super(itemView);
        RoastBeansItemNameView = itemView.findViewById(R.id.name_roastbeans_item);
        RoastBeansItemTimeView = itemView.findViewById(R.id.time_roastbeans_item);
        RoastBeansReviewRatingBar = itemView.findViewById(R.id.review_ratingbar_roastbeans_item);
    }

    //set specific value to each line
    public void bind(RoastBeans roastbeans) {
        RoastBeansItemNameView.setText(roastbeans.getRoastbeans_name());
        RoastBeansItemTimeView.setText(
                DateFormat.format("yyyy-MM-dd hh:mm:ss", roastbeans.getRegistered_time())
        );
        RoastBeansReviewRatingBar.setRating(roastbeans.getRoastbeans_review());

        //NullPointerException occurs below
        /*
        _binding.nameRoastbeansItem.setText(roastbeans.getRoastBeansName());
        _binding.timeRoastbeansItem.setText(
                DateFormat.format("yyyy-MM-dd hh:mm:ss", roastbeans.getRegisteredTime())
        );
        */
    }

    //create 1 line and return it
    static RoastBeansViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.roastbeans_item, parent, false);
        return new RoastBeansViewHolder(view);
    }
}
