package com.startupweekend.infect;

import com.littlefluffytoys.littlefluffylocationlibrary.LocationInfo;
import com.littlefluffytoys.littlefluffylocationlibrary.LocationLibraryConstants;
import com.parse.GetCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class InfectBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("LocationBroadcastReceiver", "onReceive: received location update");

        final LocationInfo locationInfo = (LocationInfo) intent.getSerializableExtra(
                LocationLibraryConstants.LOCATION_BROADCAST_EXTRA_LOCATIONINFO);
        
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        
         // Retrieve the object by id
        String currentUserId = SharedPrefUtils.getUserId(context);
        
        if(currentUserId != null && !currentUserId.isEmpty()){
        
             query.getInBackground(currentUserId, new GetCallback<ParseObject>() {
               public void done(ParseObject gameScore, ParseException e) {
                 if (e == null) {
    
                   gameScore.put("lastLatitude", locationInfo.lastLat);
                   gameScore.put("lastLognitude", locationInfo.lastLong);
                   gameScore.saveInBackground();
                   Log.d("LocationBroadcastReceiver", "UPDATED LOCATION ON SERVER");
                 }
               }
             });
        }
        
    }
}