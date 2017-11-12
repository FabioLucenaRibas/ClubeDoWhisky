package com.projeto.clubedowhisky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.clubedowhisky.classes.Drinks;

import java.util.List;

public class ListDrinksAdapter extends BaseAdapter {
    private Context context;
    private List<Drinks> drinks;

    public ListDrinksAdapter(Context context, List<Drinks> drinks) {
        this.context = context;
        this.drinks = drinks;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            //gridView = new View(context);

            final Drinks drink = drinks.get(position);

            gridView = inflater.inflate(R.layout.item_ticket, null);
            ImageView drink_photo = gridView
                    .findViewById(R.id.drink_photos);

            TextView drink_name = gridView
                    .findViewById(R.id.drink_name);
            drink_name.setText(drink.getName());

            TextView drink_descricao = gridView
                    .findViewById(R.id.drink_descricao);
            drink_descricao.setText(drink.getDescricao());

            TextView drink_preco = gridView
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

        } else {
            gridView = convertView;
        }

        return gridView;
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
