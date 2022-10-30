package org.idnp.idnp2022lab04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mensaje_text;
    private IntentFilter InterFilter;
    private BroadCastReceiver broadCastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        mensaje_text = (TextView) findViewById(R.id.mensaje);
        InterFilter = new IntentFilter();
        InterFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        InterFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        broadCastReceiver= new BroadCastReceiver();
    }
    private class BroadCastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            String action = intent.getAction();
            boolean isCargar = (action.equals(Intent.ACTION_POWER_CONNECTED));
            showCargar(isCargar);
        }
    }
    private void showCargar(boolean isCargar){
        if(isCargar){
            mensaje_text.setText("conectado");
        }else{
            mensaje_text.setText("desconectado");
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        registerReceiver(broadCastReceiver,chargingInterFilter);
    }
    @Override
    protected  void onPause(){
        super.onPause();
        unregisterReceiver(broadCastReceiver);
    }
}
