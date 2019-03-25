package com.projeto.clubedowhisky;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.projeto.clubedowhisky.classes.Drinks;

public class AddCartActivity extends AppCompatActivity {

    Toolbar toolbar;
    private Drinks drink;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart);


        this.toolbar = findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Intent i = this.getIntent();
        drink = i.getParcelableExtra("drink");
        drink.setQuantities(1);

        photo = findViewById(R.id.drink_photo);
        name = findViewById(R.id.drink_name);
        description = findViewById(R.id.drink_description);
        price = findViewById(R.id.drink_price);
        addDose = findViewById(R.id.add_dose);
        removeDose = findViewById(R.id.remove_dose);
        quantities = findViewById(R.id.drink_quantities);
        amount = findViewById(R.id.drink_price_total);

        addCart = findViewById(R.id.button);
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
                value += 1;
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
                    value -= 1;
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

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("quantities", drink.getQuantities());
        outState.putDouble("amountValue", amountValue);
        super.onSaveInstanceState(outState);
    }

    public void onBackPressed() {
        startActivity(new Intent(AddCartActivity.this, TicketActivity.class));
        finish();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void actionAddToCart() {
       /* AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.action_keep_buying);
        alertDialogBuilder.setPositiveButton(R.string.action_yes,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        startActivity(new Intent(AddCartActivity.this, TicketActivity.class));
                        finish();
                        // TODO CHAMAR METODO PARA ADICIONAR NO CARRINHO (SQLLITE) E VOLTAR PARA LISTA DE TICKETS
                    }
                });

        alertDialogBuilder.setNegativeButton(R.string.action_no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                startActivity(new Intent(AddCartActivity.this, CartActivity.class));
                finish();
                // TODO CHAMAR METODO PARA ADICIONAR NO CARRINHO (SQLLITE)  E IR PARA TELA DE CARRINHO

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();*/
        startActivity(new Intent(AddCartActivity.this, TicketActivity.class));
        finish();
        // TODO CHAMAR METODO PARA ADICIONAR NO CARRINHO (SQLLITE) E VOLTAR PARA LISTA DE TICKETS
    }
}