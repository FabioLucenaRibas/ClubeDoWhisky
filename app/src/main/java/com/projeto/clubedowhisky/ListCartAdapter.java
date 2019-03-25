package com.projeto.clubedowhisky;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.projeto.clubedowhisky.classes.Drinks;

import java.util.List;

public class ListCartAdapter extends BaseAdapter {
    private final Activity act;
    private List<Drinks> drinks;

    public ListCartAdapter(Activity act, List<Drinks> drinks) {
        this.act = act;
        this.drinks = drinks;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = act.getLayoutInflater().inflate(R.layout.item_cart, parent, false);

        final Drinks drink = drinks.get(position);

        TextView drink_name = view
                .findViewById(R.id.drink_name);
        drink_name.setText(drink.getName());

        TextView drink_quantities = view
                .findViewById(R.id.drink_quantities);
        drink_quantities.setText(drink.getQuantities().toString());

        TextView drink_preco_total = view
                .findViewById(R.id.drink_price_total);
        drink_preco_total.setText(Helper.formatarPreco(drink.getAmountValue()));

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
