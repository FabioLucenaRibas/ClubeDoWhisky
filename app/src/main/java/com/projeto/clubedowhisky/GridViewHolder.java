package com.projeto.clubedowhisky;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.projeto.clubedowhisky.GridAdapter.itemClickListener;

public class GridViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView drink_photo;
    TextView drink_name;
    TextView drink_quantities;

    public GridViewHolder(View view) {
        super(view);
        view.setOnClickListener(this);

        drink_photo = view.findViewById(R.id.drink_photos);

        drink_name = view.findViewById(R.id.drink_name);

        drink_quantities = view.findViewById(R.id.drink_quantities);
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }
}
