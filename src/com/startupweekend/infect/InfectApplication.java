package com.startupweekend.infect;

import com.littlefluffytoys.littlefluffylocationlibrary.LocationLibrary;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import android.app.Application;
import android.util.Log;

public class InfectApplication extends Application {
    
    public void onCreate() {
        super.onCreate();
        Parse.initialize(this, "6lXYnG9nzQrHfp9NZTdf2T9WS35cxZkcyCBInMxK", "77MW00INq22gJFZxSIBj4lrGA9ddzjZJlZEowMO8");
        initializeLocationLibrary();
        registerUser();
    }
    
    private void initializeLocationLibrary(){
        // output debug to LogCat, with tag LittleFluffyLocationLibrary
        LocationLibrary.showDebugOutput(true);

        try {
            // we will request unrealistically frequent location broadcasts
            // every 1 minute, and force a location update if there hasn't been one for 2 minutes.
            LocationLibrary.initialiseLibrary(getBaseContext(), 60 * 1000, 2 * 60 * 1000, "com.startupweekend.infect");
            Log.d("LocationLibrary", "Initiated");
        }
        catch (UnsupportedOperationException ex) {
            Log.d("LocationLibrary", "UnsupportedOperationException thrown - the device doesn't have any location providers");
        }
    }
    
    public void registerUser() {
        String currentUserId = SharedPrefUtils.getUserId(getApplicationContext());
        
        if(currentUserId == null || currentUserId.isEmpty()){
            final ParseObject newUser = new ParseObject("User");
            newUser.put("name", "New User");
            newUser.put("state", 0); // Human
            newUser.put("encounterNum", 0);
            newUser.saveInBackground(new SaveCallback()
            {
                @Override
                public void done(ParseException e) {
                   if(e==null){
                       // Retrieve new userId and store it locally
                       SharedPrefUtils.setUserId(getApplicationContext(), newUser.getObjectId());
                   }

                }
            });
        }
        
    }    
}
