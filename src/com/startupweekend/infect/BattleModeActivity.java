package com.startupweekend.infect;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class BattleModeActivity extends Activity {
    
    private RelativeLayout relativeLayoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_battlemode);
        
        relativeLayoutContainer = (RelativeLayout) findViewById(R.id.battleModeContainer);
        
        int userState = SharedPrefUtils.getUserState(BattleModeActivity.this);
        
        int backgroundImage;
        
        if(userState == 0){
            backgroundImage = R.drawable.human_attack;
        } else {
            backgroundImage = R.drawable.zombie_attack;
        }
        
        // Set background photo  
        relativeLayoutContainer.setBackgroundResource(backgroundImage);
        
        
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isWinner = didUserWin();
                
                Intent i = new Intent(v.getContext(), BattleResultActivity.class);
                i.putExtra("isWinner", isWinner);
                startActivity(i); 
            }
        };
        
        relativeLayoutContainer.setOnClickListener(onClickListener);
    }
    
    private boolean didUserWin(){
        Random rn = new Random();        
        int result = rn.nextInt(10) + 1;
        
        Log.d("Battle Result", result +"");
        
        if(result <= 6){
            // Win
            return true;
        } else {
            // Loose
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
