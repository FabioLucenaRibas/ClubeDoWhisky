package com.projeto.clubedowhisky;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.clubedowhisky.classes.Drinks;

import java.util.List;

public class ListDrinksAdapter extends BaseAdapter {
    private final Activity act;
    private List<Drinks> drinks;

    public ListDrinksAdapter(Activity act, List<Drinks> drinks) {
        this.act = act;
        this.drinks = drinks;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = act.getLayoutInflater().inflate(R.layout.item_ticket, parent, false);

        final Drinks drink = drinks.get(position);

        ImageView drink_photo = view
                .findViewById(R.id.drink_photos);

        TextView drink_name = view
                .findViewById(R.id.drink_name);
        drink_name.setText(drink.getName());

        TextView drink_descricao = view
                .findViewById(R.id.drink_descricao);
        drink_descricao.setText(drink.getDescricao());

        TextView drink_preco = view
                .findViewById(R.id.drink_preco);
        drink_preco.setText(Helper.formatarPreco(drink.getPreco()));

        switch (drink.getId()) {
            case 1:
                drink_photo.setImageResource(R.drawable.whisky_old_parr_12_anos);
                break;
            case 2:
                drink_photo.setImageResource(R.drawable.whisky_jack_daniels_honey);
                break;
            case 3:
                drink_photo.setImageResource(R.drawable.whisky_chivas_regal_12_anos);
                break;
            case 4:
                drink_photo.setImageResource(R.drawable.whisky_johnn_e_walker_blue);
                break;
            case 5:
                drink_photo.setImageResource(R.drawable.whisky_johnn_e_walker_double);
                break;
            case 6:
                drink_photo.setImageResource(R.drawable.whisky_johnn_e_walker_black);
                break;
            case 7:
                drink_photo.setImageResource(R.drawable.whisky_johnn_e_walker_red);
                break;
        }

        return view;
    }

    @Override
    public int getCount() {
        return drinks.size();
    }

    @Override
    public Object getItem(int position) {
        return drinks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
