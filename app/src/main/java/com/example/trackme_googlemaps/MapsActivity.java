package com.example.trackme_googlemaps;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private static final LatLng BOSTON = new LatLng(42.3144556,-71.0403236);
    private static final LatLng NYC = new LatLng(40.6976637,-74.119764);
    private static final LatLng DALLAS = new LatLng(32.8208751,-96.8716261);

    private Marker mBoston;
    private Marker mNYC;
    private Marker mDallas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        List<Marker> markerList = new ArrayList<>();

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//        This displays the different map type styles
//        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
//        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
//        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        mBoston = mMap.addMarker(new MarkerOptions()
                .position(BOSTON).title("Boston")
                 .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mBoston.setTag(0);
        markerList.add(mBoston);

        mDallas = mMap.addMarker(new MarkerOptions()
                .position(DALLAS).title("Dallas")
                      .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
        mBoston.setTag(0);
        markerList.add(mDallas);

        mNYC = mMap.addMarker(new MarkerOptions()
                .position(NYC).title("NYC")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mBoston.setTag(0);
        markerList.add(mNYC);

        mMap.setOnMarkerClickListener(this);

        for(Marker m : markerList){
            LatLng latLng = new LatLng(m.getPosition().latitude, m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        }



        // Add a marker in Sydney and move the camera
        LatLng boston = new LatLng(42.3140089, -71.2504676);
        mMap.addMarker(new MarkerOptions().position(boston).title("Marker in Boston"));
//        To change the marker color
//        mMap.addMarker(new MarkerOptions().position(boston).title("Marker in Boston")
//                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(boston));
//        To enter the Zoom on the map
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(boston, 20));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Integer clickCt = (Integer) marker.getTag();
        if(clickCt != null){
            clickCt = clickCt + 1;
            marker.setTag(clickCt);
            Toast.makeText(this, "Clicked: " + marker.getTitle(), Toast.LENGTH_LONG).show();

        }

        return false;
    }
}
