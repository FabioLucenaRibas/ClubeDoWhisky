package com.projeto.clubedowhisky;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.projeto.clubedowhisky.classes.Drinks;

import java.util.ArrayList;

public class TicketActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    ArrayList<Drinks> drinks = new ArrayList<>();
    ListDrinksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        this.toolbar = findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        carregarDrinks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                finish();
                return true;
            case R.id.action_cart:
                Intent i = new Intent(TicketActivity.this, CartActivity.class);
                startActivityForResult(i, 1);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void carregarDrinks(){

        obterBebidas();

        listView = findViewById(R.id.listBebidasTicket);
        adapter = new ListDrinksAdapter(this, this.drinks);
        listView.setAdapter(adapter);
        listView.setDivider(null);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Drinks drink = TicketActivity.this.drinks.get(position);

                int orientation = TicketActivity.this.getResources().getConfiguration().orientation;
                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Intent i = new Intent(TicketActivity.this, AddCartActivity.class);
                    i.putExtra("drink", drink);
                    startActivity(i);
                    finish();
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

                    AddCartFragment addCartFragment = new AddCartFragment();

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("drink", drink);
                    addCartFragment.setArguments(bundle);

                    FragmentManager fm = getSupportFragmentManager();

                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_content, addCartFragment);
                    ft.commit();

                }

            }
        });
    }

    private void obterBebidas() {

        //TODO CRIAR CHAMADA PARA OBTER LISTA DE BEBIDAS

        Drinks item = new Drinks();
        item.setId(1);
        item.setName("Whisky Old Parr 12 anos");
        item.setDescription("Descrição Teste");
        item.setPrice(9.50);
        drinks.add(item);

        Drinks item2 = new Drinks();
        item2.setId(2);
        item2.setName("Whisky Jack Daniels Honey");
        item2.setDescription("Descrição Teste");
        item2.setPrice(7.30);
        drinks.add(item2);

        Drinks item3 = new Drinks();
        item3.setId(3);
        item3.setName("Whisky Chivas Regal 12 anos");
        item3.setDescription("Descrição Teste");
        item3.setPrice(8.75);
        drinks.add(item3);

        Drinks item4 = new Drinks();
        item4.setId(4);
        item4.setName("Whisky Johnnie Walker Blue");
        item4.setDescription("Descrição Teste");
        item4.setPrice(76.80);
        drinks.add(item4);

        Drinks item5 = new Drinks();
        item5.setId(5);
        item5.setName("Whisky Johnnie Walker Double");
        item5.setDescription("Descrição Teste");
        item5.setPrice(11.75);
        drinks.add(item5);

        Drinks item6 = new Drinks();
        item6.setId(6);
        item6.setName("Whisky Johnnie Walker Black");
        item6.setDescription("Descrição Teste");
        item6.setPrice(9.99);
        drinks.add(item6);

        Drinks item7 = new Drinks();
        item7.setId(7);
        item7.setName("Whisky Johnnie Walker Red");
        item7.setDescription("O Whisky Johnnie Walker Red Label tem uma seleção inigualável de mais de 35 maltes na sua composição que garantem a sua superioridade. Com aroma doce amadeirado, cravo-da-índia e doce de manteiga e sabor rico com mel. Lançado em 1909");
        item7.setPrice(5.80);
        drinks.add(item7);
    }
}
