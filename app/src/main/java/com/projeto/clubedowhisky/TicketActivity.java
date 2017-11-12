package com.projeto.clubedowhisky;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.projeto.clubedowhisky.classes.Drinks;

import java.util.ArrayList;
import java.util.List;

public class TicketActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView;
    List<Drinks> drinks = new ArrayList<>();
    ListDrinksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        obterBebidas();

        listView = (ListView) findViewById(R.id.listBebidasTicket);
        adapter = new ListDrinksAdapter(this, drinks);
        listView.setAdapter(adapter);
        listView.setDivider(null);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Drinks drink = (Drinks) adapter.getItem(position);

                Intent i = new Intent(TicketActivity.this, AddCartActivity.class);
                i.putExtra("drink", drink);
                startActivity(i);
            }
        });


        //    if (!App.getInstance().isConnected()) {
        //        Toast.makeText(RecoveryActivity.this.getApplicationContext(), R.string.msg_network_error, 0).show();
        //    } else if (new Helper().isSValidEmail(RecoveryActivity.this.email)) {
        //       RecoveryActivity.this.recovery();
        //    } else {
        //        Toast.makeText(RecoveryActivity.this.getApplicationContext(), RecoveryActivity.this.getText(R.string.error_email), 0).show();
        //    }

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

    public void obterBebidas(){
        Drinks item = new Drinks();
        item.setId(1);
        item.setName("Whisky Old Parr 12 anos");
        item.setDescricao("Descrição Teste");
        item.setPreco(9.50);
        drinks.add(item);

        Drinks item2 = new Drinks();
        item2.setId(2);
        item2.setName("Whisky Jack Daniels Honey");
        item2.setDescricao("Descrição Teste");
        item2.setPreco(7.30);
        drinks.add(item2);

        Drinks item3 = new Drinks();
        item3.setId(3);
        item3.setName("Whisky Chivas Regal 12 anos");
        item3.setDescricao("Descrição Teste");
        item3.setPreco(8.75);
        drinks.add(item3);

        Drinks item4 = new Drinks();
        item4.setId(4);
        item4.setName("Whisky Johnnie Walker Blue");
        item4.setDescricao("Descrição Teste");
        item4.setPreco(76.80);
        drinks.add(item4);

        Drinks item5 = new Drinks();
        item5.setId(5);
        item5.setName("Whisky Johnnie Walker Double");
        item5.setDescricao("Descrição Teste");
        item5.setPreco(11.75);
        drinks.add(item5);

        Drinks item6 = new Drinks();
        item6.setId(6);
        item6.setName("Whisky Johnnie Walker Black");
        item6.setDescricao("Descrição Teste");
        item6.setPreco(9.99);
        drinks.add(item6);

        Drinks item7 = new Drinks();
        item7.setId(7);
        item7.setName("Whisky Johnnie Walker Red");
        item7.setDescricao("O Whisky Johnnie Walker Red Label tem uma seleção inigualável de mais de 35 maltes na sua composição que garantem a sua superioridade. Com aroma doce amadeirado, cravo-da-índia e doce de manteiga e sabor rico com mel. Lançado em 1909");
        item7.setPreco(5.80);
        drinks.add(item7);
    }
}
