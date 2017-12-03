package com.projeto.clubedowhisky;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.projeto.clubedowhisky.ListDrinksAdapter.itemClickListener;

public class ListDrinksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView drink_photo;
    TextView drink_name;
    TextView drink_descricao;
    TextView drink_preco;

    public ListDrinksViewHolder(View view) {
        super(view);
        view.setOnClickListener(this);

        drink_photo = view.findViewById(R.id.drink_photos);

        drink_name = view.findViewById(R.id.drink_name);

        drink_descricao = view.findViewById(R.id.drink_descricao);

        drink_preco = view.findViewById(R.id.drink_preco);

    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick(getAdapterPosition());
        }
    }
}
