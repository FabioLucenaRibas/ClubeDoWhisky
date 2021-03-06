package com.projeto.clubedowhisky;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.projeto.clubedowhisky.classes.Clients;
import com.projeto.clubedowhisky.classes.Drinks;
import com.projeto.clubedowhisky.classes.Users;
import com.projeto.clubedowhisky.pager.PagerAdapter;
import com.projeto.clubedowhisky.tabs.HistoryFragment;
import com.projeto.clubedowhisky.tabs.MyTicketsFragment;
import com.projeto.clubedowhisky.tabs.QrcodeFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    GridView gridView;
    List<Drinks> drinks = new ArrayList<>();
    GridAdapter adapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    private FragmentActivity myContext;

    static final int REQUEST_CODE = 1;
    private static final int REQUEST_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, TicketActivity.class);
//                startActivityForResult(i, REQUEST_CODE);
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);

//        TextView nome =  headerView.findViewById(R.id.nome);
        TextView email = headerView.findViewById(R.id.email);

        Clients c = (Clients) getIntent().getSerializableExtra("client");
        if (c != null){
            email.setText(c.getUser().getEmail());
        }else {
            Users u = (Users) getIntent().getSerializableExtra("user");
            email.setText(u.getEmail());
        }

        // TODO CRIAR CHAMADA PARA BUSCAR LISTA DE TICKETS QUE O CLIENTE LOGADO POSSUI
//        obterListaTicketsCliente();

//        gridView = (GridView) findViewById(R.id.gridView1);
//        adapter = new GridAdapter(this, drinks);
//        gridView.setAdapter(adapter);
//
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View v,
//                                    int position, long id) {
//
//                Drinks drink = (Drinks) adapter.getItem(position);
//
//                Toast.makeText(
//                        getApplicationContext(),
//                        ((TextView) v.findViewById(R.id.drink_name))
//                                .getText(), Toast.LENGTH_SHORT).show();
//            }
//        });
        buildViewPager();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            openDialogExit();
            //super.onBackPressed();
        }
    }

    public void openDialogExit() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.dialog_exit);
        alertDialogBuilder.setPositiveButton(R.string.action_ok,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finishAndRemoveTask();
                    }
                });

        alertDialogBuilder.setNegativeButton(R.string.action_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_map) {
            int checkPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
            if (checkPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_LOCATION);
            } else {
                MainActivity.this.startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }

            int checkPermission1 = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
            if (checkPermission1 == PackageManager.PERMISSION_GRANTED) {
                MainActivity.this.startActivity(new Intent(MainActivity.this, MapsActivity.class));
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }

    private void obterListaTicketsCliente() {
        // Implementar consulta do serviço e gravar no sqlLite

        Drinks item = new Drinks();
        item.setId(1);
        item.setQuantities(15);
        item.setName("Whisky Old Parr 12 anos");
        drinks.add(item);

        Drinks item2 = new Drinks();
        item2.setId(2);
        item2.setQuantities(6);
        item2.setName("Whisky Jack Daniels Honey");
        drinks.add(item2);
    }

    private void buildViewPager(){
        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.view_pager);

        Bundle b = new Bundle();
        b.putSerializable("client", getIntent().getSerializableExtra("client"));

        QrcodeFragment qr = new QrcodeFragment();

        MyTicketsFragment myTicketsFragment = new MyTicketsFragment();
        myTicketsFragment.setArguments(b);

        HistoryFragment historyFragment = new HistoryFragment();

        PagerAdapter pg = new PagerAdapter(getSupportFragmentManager(), Arrays.asList(qr, myTicketsFragment, historyFragment), this);

        viewPager.setAdapter(pg);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);

        //Initializing the tablayout
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
