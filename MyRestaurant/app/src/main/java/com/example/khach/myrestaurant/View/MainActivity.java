package com.example.khach.myrestaurant.View;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.khach.myrestaurant.Entity.Food;
import com.example.khach.myrestaurant.Entity.FoodType;
import com.example.khach.myrestaurant.Entity.Restaurant;
import com.example.khach.myrestaurant.Presenter.MainPresenter;
import com.example.khach.myrestaurant.Presenter.MainPresenterImpl;
import com.example.khach.myrestaurant.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static com.example.khach.myrestaurant.myAdapter.ShareReference.KEY_ID;
import static com.example.khach.myrestaurant.myAdapter.ShareReference.KEY_REMEMBER;
import static com.example.khach.myrestaurant.myAdapter.ShareReference.PREF_NAME;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback, MainView,GoogleMap.OnInfoWindowClickListener {
    GoogleMap googleMap;
    MapView mapView;
    AutoCompleteTextView autoTxtSearch;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    MainPresenter mainPresenter;
    private ArrayList<Restaurant> listRestaurant = new ArrayList<>();
    private ArrayList<Food> mListFoods = new ArrayList<Food>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(KEY_ID,"ResTest1");
        editor.putBoolean(KEY_REMEMBER, true);
        editor.apply();
        mainPresenter.getAllRestaurant();
        mainPresenter.getAllFoodType();

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mapView = (MapView) findViewById(R.id.mapMain);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mainPresenter = new MainPresenterImpl(this);
        autoTxtSearch = (AutoCompleteTextView) findViewById(R.id.atctvSearch);
        autoTxtSearch.setDropDownWidth(getResources().getDisplayMetrics().widthPixels);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(this.getApplicationContext(), MainDetailRestaurant.class);
            startActivity(intent);
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(this.getApplicationContext(), Update_FreeSpace.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getApplicationContext());
        this.googleMap = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        } else {
            googleMap.setMyLocationEnabled(true);
            Location location = getLastBestLocation();
            if (location != null) {
                double lat = location.getLatitude();
                double lon=location.getLongitude();
                LatLng latLng = new LatLng(lat,lon);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
            }
        }
        googleMap.setOnInfoWindowClickListener(this);
    }
    private Location getLastBestLocation() {
        LocationManager mLocationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNet = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            long GPSLocationTime = 0;
            if (null != locationGPS) {
                GPSLocationTime = locationGPS.getTime();
            }

            long NetLocationTime = 0;

            if (null != locationNet) {
                NetLocationTime = locationNet.getTime();
            }

            if (0 < GPSLocationTime - NetLocationTime) {
                return locationGPS;
            } else {
                return locationNet;
            }
        }
        return null;
    }

    @Override
    public void getAllRestaurant(ArrayList<Restaurant> listRestaurant) {
        this.listRestaurant = listRestaurant;
        int size = listRestaurant.size();
        for (int i = 0; i < size; i++) {
            LatLng myLocation = new LatLng(this.listRestaurant.get(i).getLatitude(),this.listRestaurant.get(i).getLongitude());
            if (myLocation != null) {
                MarkerOptions markerOptions = new MarkerOptions().position(myLocation).title("Mã Quán: "+(i+1));
                this.googleMap.addMarker(markerOptions);
            }
        }
    }

    @Override
    public void getAllFoodTypes(ArrayList<FoodType> listRestaurant) {
        ArrayList<String> listTypeFoodNames = new ArrayList<>();
        for(int i=0;i<listRestaurant.size();i++){
            listTypeFoodNames.add(listRestaurant.get(i).getFoodTypeName());
        }
        if(listTypeFoodNames.size()>0) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.my_custom_dropdow, listTypeFoodNames);
            autoTxtSearch.setAdapter(adapter);
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Intent intent = new Intent(getApplicationContext(),MainDetailRestaurant.class);
        String text = marker.getTitle();
        String[] abc = text.split(" ");
        String id = abc[2];
        Bundle bundle = new Bundle();
        bundle.putString("ID", listRestaurant.get(Integer.parseInt(id)-1).getResID());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
