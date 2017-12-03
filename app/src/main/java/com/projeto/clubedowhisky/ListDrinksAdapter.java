package com.projeto.clubedowhisky;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.projeto.clubedowhisky.classes.Drinks;
import com.projeto.clubedowhisky.classes.ItemClickListener;

import java.util.List;

public class ListDrinksAdapter extends RecyclerView.Adapter {
    private final Activity act;
    private List<Drinks> drinks;
    public static ItemClickListener itemClickListener;

    public ListDrinksAdapter(Activity act, List<Drinks> drinks) {
        this.act = act;
        this.drinks = drinks;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = act.getLayoutInflater().inflate(R.layout.item_ticket, parent, false);
        return new ListDrinksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        final Drinks drink = drinks.get(position);

        ListDrinksViewHolder holder = (ListDrinksViewHolder) viewHolder;

        holder.drink_name.setText(drink.getName());

        holder.drink_descricao.setText(drink.getDescricao());

        holder.drink_preco.setText(Helper.formatarPreco(drink.getPreco()));

        switch (drink.getId()) {
            case 1:
                holder.drink_photo.setImageResource(R.drawable.whisky_old_parr_12_anos);
                break;
            case 2:
                holder.drink_photo.setImageResource(R.drawable.whisky_jack_daniels_honey);
                break;
            case 3:
                holder.drink_photo.setImageResource(R.drawable.whisky_chivas_regal_12_anos);
                break;
            case 4:
                holder.drink_photo.setImageResource(R.drawable.whisky_johnn_e_walker_blue);
                break;
            case 5:
                holder.drink_photo.setImageResource(R.drawable.whisky_johnn_e_walker_double);
                break;
            case 6:
                holder.drink_photo.setImageResource(R.drawable.whisky_johnn_e_walker_black);
                break;
            case 7:
                holder.drink_photo.setImageResource(R.drawable.whisky_johnn_e_walker_red);
                break;
        }

    }

    public void setOnItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }
}
