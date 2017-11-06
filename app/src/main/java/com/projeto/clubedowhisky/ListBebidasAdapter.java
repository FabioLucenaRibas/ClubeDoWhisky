package com.projeto.clubedowhisky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.clubedowhisky.classes.Drinks;

import java.util.List;

public class ListBebidasAdapter extends BaseAdapter {
    private Context context;
    List<Drinks> drinks;

    public ListBebidasAdapter(Context context, List<Drinks> drinks) {
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

            final Drinks drink = drinks.get(position);


                gridView = inflater.inflate(R.layout.item_ticket, null);
                ImageView drink_photo = (ImageView) gridView
                        .findViewById(R.id.drink_photos);

                TextView drink_name = (TextView) gridView
                        .findViewById(R.id.drink_name);
                drink_name.setText(drink.getName());

                EditText drink_quantities = (EditText) gridView
                        .findViewById(R.id.drink_quantities);
                drink_quantities.setText(drink.getQuantities().toString());
                disableEditText(drink_quantities);

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

            ImageButton add_dose = (ImageButton) gridView.findViewById(R.id.add_dose);
            ImageButton remove_dose = (ImageButton) gridView.findViewById(R.id.remove_dose);


            add_dose.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int value = drink.getQuantities();
                    value += 1;
                    drink.setQuantities(value);

                    notifyDataSetChanged();
                }
            });

            remove_dose.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int value = drink.getQuantities();
                    value -= 1;
                    drink.setQuantities(value);

                    notifyDataSetChanged();
                }
            });

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

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
    }
}
