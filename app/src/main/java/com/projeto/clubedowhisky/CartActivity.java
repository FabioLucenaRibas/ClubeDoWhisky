package com.projeto.clubedowhisky;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.projeto.clubedowhisky.classes.Drinks;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ArrayList<Drinks> drinks = new ArrayList<>();
    private ListCartAdapter adapter;
    private ListView listCart;
    private TextView amount;
    private Drinks drink;

    private int index = 0;
    private int top = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);


        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        obterListaTicketsCliente();

        listCart = (ListView) findViewById(R.id.listCart);
        amount = (TextView) findViewById(R.id.drink_preco_total);

        for (int i = 0; i < drinks.size(); i++) {
            drinks.get(i).setAmountValue(drinks.get(i).getPreco() * drinks.get(i).getQuantities());
        }

        updateList();


        if (savedInstanceState != null) {

        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onBackPressed() {
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


    private void obterListaTicketsCliente() {
        // TODO OBTER LISTA DE BEBIDAS DO CARRINHO (SQLLITE)

        Drinks item = new Drinks();
        item.setId(1);
        item.setName("Whisky Old Parr 12 anos");
        item.setQuantities(1);
        item.setDescricao("Descrição Teste");
        item.setPreco(9.50);
        drinks.add(item);

        Drinks item2 = new Drinks();
        item2.setId(2);
        item2.setName("Whisky Jack Daniels Honey");
        item2.setQuantities(1);
        item2.setDescricao("Descrição Teste");
        item2.setPreco(7.30);
        drinks.add(item2);

        Drinks item3 = new Drinks();
        item3.setId(3);
        item3.setName("Whisky Chivas Regal 12 anos");
        item3.setQuantities(1);
        item3.setDescricao("Descrição Teste");
        item3.setPreco(8.75);
        drinks.add(item3);

        Drinks item4 = new Drinks();
        item4.setId(4);
        item4.setName("Whisky Johnnie Walker Blue");
        item4.setQuantities(1);
        item4.setDescricao("Descrição Teste");
        item4.setPreco(76.80);
        drinks.add(item4);

        Drinks item5 = new Drinks();
        item5.setId(5);
        item5.setName("Whisky Johnnie Walker Double");
        item5.setQuantities(1);
        item5.setDescricao("Descrição Teste");
        item5.setPreco(11.75);
        drinks.add(item5);

        Drinks item6 = new Drinks();
        item6.setId(6);
        item6.setName("Whisky Johnnie Walker Black");
        item6.setQuantities(1);
        item6.setDescricao("Descrição Teste");
        item6.setPreco(9.99);
        drinks.add(item6);

        Drinks item7 = new Drinks();
        item7.setId(7);
        item7.setName("Whisky Johnnie Walker Red");
        item7.setQuantities(1);
        item7.setDescricao("O Whisky Johnnie Walker Red Label tem uma seleção inigualável de mais de 35 maltes na sua composição que garantem a sua superioridade. Com aroma doce amadeirado, cravo-da-índia e doce de manteiga e sabor rico com mel. Lançado em 1909");
        item7.setPreco(5.80);
        drinks.add(item7);
    }

    public void buttonClickedListCart(View v) {
        int pos = listCart.getPositionForView(v);

        drink = drinks.get(pos);

        switch (v.getId()) {
            case R.id.add_dose:
                int value = drink.getQuantities();
                value += 1;
                drink.setQuantities(value);
                drink.setAmountValue(drink.getPreco() * value);
                updateList();
                break;
            case R.id.remove_dose:
                Integer value1 = drink.getQuantities();
                value1 -= 1;
                if (value1.equals(0)) {
                    drinks.remove(drink);
                    //removeItemFromList();
                } else {
                    drink.setQuantities(value1);
                    drink.setAmountValue(drink.getPreco() * value1);
                }
                updateList();
                break;
            default:
                // caso aconteca algum erro
                break;
        }


    }

    public void updateList() {
        index = listCart.getFirstVisiblePosition();
        View v = listCart.getChildAt(0);
        top = (v == null) ? 0 : v.getTop();
        adapter = new ListCartAdapter(this, drinks);
        listCart.setAdapter(adapter);
        listCart.setDivider(null);
        listCart.setSelectionFromTop(index, top);
        adapter.notifyDataSetChanged();
        updateAmount();
    }

    public void updateAmount() {

        Double value = 0.0;
        for (int i = 0; i < drinks.size(); i++) {
            value += drinks.get(i).getAmountValue();
        }
        amount.setText(Helper.formatarPreco(value));
    }

    public void removeItemFromList() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialogBuilder.setTitle(R.string.dialog_title_remover_item);
        alertDialogBuilder.setMessage(R.string.dialog_remover_item);
        alertDialogBuilder.setPositiveButton(R.string.action_confirm,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        drinks.remove(drink);
                    }
                });

        alertDialogBuilder.setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


}