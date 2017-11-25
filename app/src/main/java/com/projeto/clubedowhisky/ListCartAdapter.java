package com.projeto.clubedowhisky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.projeto.clubedowhisky.classes.Drinks;

import java.util.List;

public class ListCartAdapter extends BaseAdapter {
    private Context context;
    ImageButton add_dose;
    ImageButton remove_dose;
    private List<Drinks> drinks;

    public ListCartAdapter(Context context, List<Drinks> drinks) {
        this.context = context;
        this.drinks = drinks;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View listView;

        if (convertView == null) {

            final Drinks drink = drinks.get(position);

            listView = inflater.inflate(R.layout.item_cart, null);

            TextView drink_name = listView
                    .findViewById(R.id.drink_name);
            drink_name.setText(drink.getName());

            TextView drink_quantities = listView
                    .findViewById(R.id.drink_quantities);
            drink_quantities.setText(drink.getQuantities().toString());

            TextView drink_preco_total = listView
                    .findViewById(R.id.drink_preco_total);
            drink_preco_total.setText(Helper.formatarPreco(drink.getAmountValue()));

        } else {
            listView = convertView;
        }

        return listView;
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
