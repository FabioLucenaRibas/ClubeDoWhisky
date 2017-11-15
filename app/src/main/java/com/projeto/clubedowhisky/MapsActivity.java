package com.projeto.clubedowhisky;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    public Location location;
    protected double latitude;
    protected double longitude;

    private static final int REQUEST_LOCATION = 1;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this) //Be aware of state of the connection
                .addOnConnectionFailedListener(this) //Be aware of failures
                .addApi(LocationServices.API)
                .build();
        //Tentando conexão com o Google API. Se a tentativa for bem sucessidade, o método onConnected() será chamado, senão, o método onConnectionFailed() será chamado.
        googleApiClient.connect();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        //LatLng currentLocation = new LatLng(latitude, longitude);

        LatLng currentLocation = new LatLng(-8.1518139, -34.9193298);
        mMap.addMarker(new MarkerOptions().position(currentLocation).title("Tempero da Galega"));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 17.0f));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        int checkPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        } else {
            mMap.setMyLocationEnabled(true);
        }

    }

    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pararConexaoComGoogleApi();
    }

    public void pararConexaoComGoogleApi() {
        //Verificando se está conectado para então cancelar a conexão!
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    /**
     * Depois que o método connect() for chamado, esse método será chamado de forma assíncrona caso a conexão seja bem sucedida.
     *
     * @param bundle
     */
    @Override
    public void onConnected(Bundle bundle) {
        //Google API connection has been done successfully
        int checkPermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (checkPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION);
        } else {
            Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
            if (location != null) {
                this.latitude = location.getLatitude();
                this.longitude = location.getLongitude();
            }

            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

    }

    /**
     * Esse método é chamado quando o client está temporariamente desconectado. Isso pode acontecer quando houve uma falha ou problema com o serviço que faça com que o client seja desligado.
     * Nesse estado, todas as requisições e listeners são cancelados.
     * Não se preocupe em tentar reestabelecer a conexão, pois isso acontecerá automaticamente.
     * As aplicações devem desabilitar recursos visuais que estejam relacionados com o uso dos serviços e habilitá-los novamente quando o método onConnected() for chamado, indicando reestabelecimento da conexão.
     */
    @Override
    public void onConnectionSuspended(int i) {
        // Aguardando o GoogleApiClient reestabelecer a conexão.
    }

    /**
     * Método chamado quando um erro de conexão acontece e não é possível acessar os serviços da Google Service.
     *
     * @param connectionResult
     */
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        //A conexão com o Google API falhou!
        pararConexaoComGoogleApi();
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

}