package com.startupweekend.infect;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

public class BattleModeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_battlemode);
        
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseWinner();
                
                Intent i = new Intent(v.getContext(), BattleResultActivity.class);
                startActivity(i); 
            }
        };
        
        findViewById(R.id.battleModeContainer).setOnClickListener(onClickListener);
    }
    
    private void chooseWinner(){
        Random rn = new Random();        
        int result = rn.nextInt(10);
        
        if(result <= 6){
            // Win
            
        } else {
            // Loose
            
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
