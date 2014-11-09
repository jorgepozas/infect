package com.startupweekend.infect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class HomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
        
        OnClickListener onClickListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;
                
                switch (v.getId()) {
                    case R.id.btn_map:
                        i = new Intent(v.getContext(), MapActivity.class);
                        startActivity(i); 
                        break;
                    case R.id.btn_stats:
                        i = new Intent(v.getContext(), StatsActivity.class);
                        startActivity(i); 
                        break;
                    case R.id.btn_about:
                        i = new Intent(v.getContext(), AboutActivity.class);
                        startActivity(i); 
                        break;
                    case R.id.btn_inventory:
                        i = new Intent(v.getContext(), InventoryActivity.class);
                        startActivity(i); 
                        break;
                    case R.id.btn_invite:
                        i = new Intent(v.getContext(), InviteActivity.class);
                        startActivity(i); 
                    default:
                        break;
                }
            }
        };

        findViewById(R.id.btn_map).setOnClickListener(onClickListener);
        findViewById(R.id.btn_stats).setOnClickListener(onClickListener);
        findViewById(R.id.btn_about).setOnClickListener(onClickListener);
        findViewById(R.id.btn_inventory).setOnClickListener(onClickListener);
        findViewById(R.id.btn_invite).setOnClickListener(onClickListener);
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
