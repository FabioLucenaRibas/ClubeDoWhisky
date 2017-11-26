package com.projeto.clubedowhisky;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.clubedowhisky.classes.Drinks;

import java.util.List;

public class GridAdapter extends BaseAdapter {
    private final Activity act;
    List<Drinks> drinks;

    public GridAdapter(Activity act, List<Drinks> drinks) {
        this.act = act;
        this.drinks = drinks;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = act.getLayoutInflater().inflate(R.layout.item, parent, false);

        final Drinks drink = drinks.get(position);

        ImageView drink_photo = view
                .findViewById(R.id.drink_photos);

        TextView drink_name = view
                .findViewById(R.id.drink_name);
        drink_name.setText(drink.getName());

        TextView drink_quantities = view
                .findViewById(R.id.drink_quantities);
        drink_quantities.setText(drink.getQuantities().toString());

        switch (drink.getId()) {
            case 1:
                drink_photo.setImageResource(R.drawable.whisky_old_parr_12_anos);
                break;
            case 2:
                drink_photo.setImageResource(R.drawable.whisky_jack_daniels_honey);
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
