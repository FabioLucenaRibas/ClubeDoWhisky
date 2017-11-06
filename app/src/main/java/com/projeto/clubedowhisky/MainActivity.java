package com.projeto.clubedowhisky;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.clubedowhisky.classes.Drinks;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    GridView gridView;
    List<Drinks> drinks = new ArrayList<Drinks>();
    GridAdapter adapter;

    static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }

        Drinks item = new Drinks();
        item.setId(1);
        item.setQuantities(15);
        item.setName("Whisky Old Parr 12 anos");
        drinks.add(item);

        Drinks item2 = new Drinks();
        item2.setId(2);
        item2.setQuantities(06);
        item2.setName("Whisky Jack Daniels Honey");
        drinks.add(item2);

        gridView = (GridView) findViewById(R.id.gridView1);
        adapter = new GridAdapter(this, drinks);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Drinks drink = (Drinks) adapter.getItem(position);

                if (drink.getId() == 0){
                    Intent i = new Intent(MainActivity.this, TicketActivity.class);
                    startActivityForResult(i, REQUEST_CODE);
                }else{
                    Toast.makeText(
                            getApplicationContext(),
                            ((TextView) v.findViewById(R.id.drink_name))
                                    .getText(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                Drinks drinks = data.getParcelableExtra("drinks");
                this.drinks.add(drinks);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
