package com.projeto.clubedowhisky;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.clubedowhisky.classes.Drinks;

public class AddCartFragment extends Fragment {

    private Drinks drink = new Drinks();
    private Double amountValue;

    private TextView name;
    private TextView description;
    private TextView price;
    private TextView amount;
    private TextView quantities;
    private ImageButton addDose;
    private ImageButton removeDose;
    private ImageView photo;
    private Button addCart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content_add_cart, container, false);

        Bundle bundle = getArguments();
        if (bundle != null) {
            drink = bundle.getParcelable("drink");
            drink.setQuantities(1);
        }

        photo = view.findViewById(R.id.drink_photo);
        name = view.findViewById(R.id.drink_name);
        description = view.findViewById(R.id.drink_description);
        price = view.findViewById(R.id.drink_price);
        addDose = view.findViewById(R.id.add_dose);
        removeDose = view.findViewById(R.id.remove_dose);
        quantities = view.findViewById(R.id.drink_quantities);
        amount = view.findViewById(R.id.drink_price_total);

        if (savedInstanceState != null) {
            Integer value = savedInstanceState.getInt("quantities");
            drink.setQuantities(value);
            quantities.setText(value.toString());
            amountValue = savedInstanceState.getDouble("amountValue");
            amount.setText(Helper.formatarPreco(amountValue));

        } else {
            quantities.setText(drink.getQuantities().toString());
            amount.setText(Helper.formatarPreco(drink.getPrice()));
            amountValue = drink.getPrice();
        }

        addCart = view.findViewById(R.id.button);
        addCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                actionAddToCart();
            }
        });

        name.setText(drink.getName());
        description.setText(drink.getDescription());
        price.setText(Helper.formatarPreco(drink.getPrice()));


        if (savedInstanceState != null) {
            Integer value = savedInstanceState.getInt("quantities");
            drink.setQuantities(value);
            quantities.setText(value.toString());
            amountValue = savedInstanceState.getDouble("amountValue");
            amount.setText(Helper.formatarPreco(amountValue));

        } else {
            quantities.setText(drink.getQuantities().toString());
            amount.setText(Helper.formatarPreco(drink.getPrice()));
            amountValue = drink.getPrice();
        }

        addDose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = drink.getQuantities();
                value++;
                drink.setQuantities(value);
                quantities.setText(drink.getQuantities().toString());
                amountValue = drink.getPrice();
                amountValue *= value;

                amount.setText(Helper.formatarPreco(amountValue));

            }
        });

        removeDose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int value = drink.getQuantities();
                if (value > 1) {
                    value--;
                    drink.setQuantities(value);
                    quantities.setText(drink.getQuantities().toString());
                    amountValue = drink.getPrice();
                    amountValue *= value;

                    amount.setText(Helper.formatarPreco(amountValue));

                }

            }
        });

        switch (drink.getId()) {
            case 1:
                photo.setImageResource(R.drawable.whisky_old_parr_12_anos);
                break;
            case 2:
                photo.setImageResource(R.drawable.whisky_jack_daniels_honey);
                break;
            case 3:
                photo.setImageResource(R.drawable.whisky_chivas_regal_12_anos);
                break;
            case 4:
                photo.setImageResource(R.drawable.whisky_johnn_e_walker_blue);
                break;
            case 5:
                photo.setImageResource(R.drawable.whisky_johnn_e_walker_double);
                break;
            case 6:
                photo.setImageResource(R.drawable.whisky_johnn_e_walker_black);
                break;
            case 7:
                photo.setImageResource(R.drawable.whisky_johnn_e_walker_red);
                break;
        }


        return view;
    }

    public void actionAddToCart() {
       /* AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage(R.string.action_keep_buying);
        alertDialogBuilder.setPositiveButton(R.string.action_yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        // TODO CHAMAR METODO PARA ADICIONAR NO CARRINHO (SQLLITE)
                    }
                });

        alertDialogBuilder.setNegativeButton(R.string.action_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                getActivity().startActivity(new Intent(getActivity(), CartActivity.class));
                // TODO CHAMAR METODO PARA ADICIONAR NO CARRINHO (SQLLITE)  E VAI PARA TELA DE CARRINHO

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        */

        // TODO CHAMAR METODO PARA ADICIONAR NO CARRINHO (SQLLITE)


        Toast.makeText(getActivity().getApplicationContext(),
                (R.string.dialog_add_cart), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("quantities", drink.getQuantities());
        outState.putDouble("amountValue", amountValue);
        super.onSaveInstanceState(outState);
    }

}