package com.startupweekend.infect;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class EncounterHelper {
    
    public static void queryForEncounter(float latitude, float longitude, Context context){
        float latDif = (float) 0.08;
        float longDif = (float) 0.08;
        
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
                    
                    if(userList.size() > 0){
                        ParseObject user = userList.get(0);
                        setupFight(user, theContext);
                    }
                } else {
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });
    }
    
    public static void setupFight(ParseObject rivalUser, Context context){
        // Place rival into battle mode
        rivalUser.put("inBattle",true);
        rivalUser.saveInBackground();
        
        // Set myself into battle mode
        String currentUserId = SharedPrefUtils.getUserId(context);
        
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.getInBackground(currentUserId, new GetCallback<ParseObject>() {
            public void done(ParseObject userObject, ParseException e) {
                if (e == null) {
                      // Update in server
                      userObject.put("inBattle", true);
                      userObject.saveInBackground();
                }
            }
        });

        // Send push notification to rival user
        String rivalUserId = rivalUser.getString("objectId");
        
        // Go into fight mode in this device (go to new activity)
        Intent intent = new Intent();
        intent.setClass(context, BattleModeActivity.class);
        context.startActivity(intent);
    }

}
