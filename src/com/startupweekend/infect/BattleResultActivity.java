package com.startupweekend.infect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class BattleResultActivity extends Activity {
    
    private RelativeLayout relativeLayoutContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_battleresult);
        
        relativeLayoutContainer = (RelativeLayout) findViewById(R.id.battleModeContainer);
        
        int userState = SharedPrefUtils.getUserState(BattleResultActivity.this);
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            boolean isWinner = extras.getBoolean("isWinner");
            int backgroundImage;
            
            if(isWinner){
                if(userState == 0){
                    backgroundImage = R.drawable.human_attack_win;
                } else {
                    backgroundImage = R.drawable.zombie_attack_win;
                }
            } else {
                if(userState == 0){
                    backgroundImage = R.drawable.human_attack_lose;
                } else {
                    backgroundImage = R.drawable.zombie_attack_lose;
                }
            }
            
            // Set background photo  
            relativeLayoutContainer.setBackgroundResource(backgroundImage);
            
            if(!isWinner){
                if(userState == 0){
                    SharedPrefUtils.setUserState(BattleResultActivity.this, 1);
                    
                    // TODO: Update in database
                }
            }
        }
        
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), HomeActivity.class);
                startActivity(i); 
            }
        };
        
        relativeLayoutContainer.setOnClickListener(onClickListener);
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
