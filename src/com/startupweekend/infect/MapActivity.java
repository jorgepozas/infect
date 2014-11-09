package com.startupweekend.infect;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationInfo;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationLibrary;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class MapActivity extends Activity {
    
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_map);
        
        // Update latest location
        LocationLibrary.forceLocationUpdate(MapActivity.this);
        
        mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        
        if (mMap != null) {
            mMap.setMyLocationEnabled(true);
            
            LocationInfo latestLocation = new LocationInfo(getBaseContext());
            
            LatLng latLng = new LatLng(latestLocation.lastLat, latestLocation.lastLong);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            mMap.moveCamera(cameraUpdate);
        }        
    }
}
