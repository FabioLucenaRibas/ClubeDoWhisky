package com.projeto.clubedowhisky;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
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


        //    if (!App.getInstance().isConnected()) {
        //        Toast.makeText(RecoveryActivity.this.getApplicationContext(), R.string.msg_network_error, 0).show();
        //    } else if (new Helper().isValidEmail(RecoveryActivity.this.email)) {
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
        drinks.add(item);

        Drinks item2 = new Drinks();
        item2.setId(2);
        item2.setName("Whisky Jack Daniels Honey");
        drinks.add(item2);

        Drinks item3 = new Drinks();
        item3.setId(3);
        item3.setName("Whisky Chivas Regal 12 anos");
        drinks.add(item3);

        Drinks item4 = new Drinks();
        item4.setId(4);
        item4.setName("Whisky Johnnie Walker Blue");
        drinks.add(item4);

        Drinks item5 = new Drinks();
        item5.setId(5);
        item5.setName("Whisky Johnnie Walker Double");
        drinks.add(item5);

        Drinks item6 = new Drinks();
        item6.setId(6);
        item6.setName("Whisky Johnnie Walker Black");
        drinks.add(item6);

        Drinks item7 = new Drinks();
        item7.setId(7);
        item7.setName("Whisky Johnnie Walker Red");
        drinks.add(item7);
    }
}
