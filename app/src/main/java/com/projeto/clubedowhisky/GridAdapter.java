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

public class GridAdapter extends BaseAdapter {
    private Context context;
    List<Drinks> drinks;

    public GridAdapter(Context context, List<Drinks> drinks) {
        this.context = context;
        this.drinks = drinks;
        drinks.add(new Drinks());
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            Drinks drink = drinks.get(position);

            if (drink.getId() == 0) {
                gridView = inflater.inflate(R.layout.item_add, null);

                ImageView drink_photo_add = (ImageView) gridView
                        .findViewById(R.id.drink_add);
                drink_photo_add.setImageResource(R.drawable.ic_add);


            } else {
                gridView = inflater.inflate(R.layout.item, null);
                ImageView drink_photo = (ImageView) gridView
                        .findViewById(R.id.drink_photos);

                TextView drink_name = (TextView) gridView
                        .findViewById(R.id.drink_name);
                drink_name.setText(drink.getName());

                TextView drink_quantities = (TextView) gridView
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


            }

        } else {
            gridView = (View) convertView;
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
