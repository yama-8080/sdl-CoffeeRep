package ymz.coffeerep.data.raw_beans;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ymz.coffeerep.R;

class RawBeansViewHolder extends RecyclerView.ViewHolder {

    private final TextView rawbeansItemView;

    //constructor
    private RawBeansViewHolder(View itemView) {
        super(itemView);
        rawbeansItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        rawbeansItemView.setText(text);
    }
    //public void bind(int id) {
    //    rawbeansItemView.setText(id);
    //}

    static RawBeansViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new RawBeansViewHolder(view);
    }
}
