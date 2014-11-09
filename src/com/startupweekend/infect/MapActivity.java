package com.startupweekend.infect;

import java.util.List;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationInfo;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationLibrary;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

public class MapActivity extends Activity {
    
    private GoogleMap mMap;
    
    private List<ParseObject> mapUserList;

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
            
            querySorroundings(latestLocation.lastLat, latestLocation.lastLong, MapActivity.this);
            
            LatLng latLng = new LatLng(latestLocation.lastLat, latestLocation.lastLong);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
            mMap.moveCamera(cameraUpdate);
            
            mMap.setOnMarkerClickListener(new OnMarkerClickListener()
            {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    if(marker.getTitle().equals("Zombie")){ // if marker source is clicked
                        ParseObject rivalUser = mapUserList.get(Integer.parseInt(marker
                                .getSnippet()));
                         EncounterHelper.setupFight(rivalUser, MapActivity.this);
                    }
                    return true;
                }

            });  
        }        
    }
    
    protected void updateMapUsers(List<ParseObject> userList){
        
        for(int i=0; i<userList.size(); i++){
            ParseObject user = userList.get(i);
            
            int state = user.getInt("state");
            
            String userState = "Zombie";
            int userIcon = R.drawable.map_zombie_icon;
            if(state == 0){
                userState = "Human";
                userIcon = R.drawable.map_human_icon;
            }

            mMap.addMarker(new MarkerOptions()
                .position(new LatLng(user.getDouble("lastLatitude"), user.getDouble("lastLongitude")))
                .icon(BitmapDescriptorFactory.fromResource(userIcon))
                .title(userState))
                .setSnippet("" + i);
        }
        
        mapUserList = userList;
    }
    
    public void querySorroundings(float latitude, float longitude, Context context){
        float latDif = (float) 0.008;
        float longDif = (float) 0.008;
        
        String currentUserId = SharedPrefUtils.getUserId(context);
        int currentUserState = SharedPrefUtils.getUserState(context);
        final Context theContext = context;
        
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereGreaterThanOrEqualTo("lastLatitude", latitude - latDif);
        query.whereLessThanOrEqualTo("lastLatitude", latitude + latDif);
        
        query.whereGreaterThanOrEqualTo("lastLongitude", longitude - longDif);
        query.whereLessThanOrEqualTo("lastLongitude", longitude + longDif);
        query.whereNotEqualTo("objectId", currentUserId);
        //query.whereNotEqualTo("inBattle", true);   
        
        query.findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> userList, ParseException e) {
                if (e == null) {
                    Log.d("score", "Retrieved " + userList.size() + " users");
                    updateMapUsers(userList);
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }
}
