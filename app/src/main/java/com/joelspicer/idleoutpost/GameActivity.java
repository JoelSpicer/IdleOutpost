package com.joelspicer.idleoutpost;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


public class GameActivity extends FragmentActivity {

    //values
    public long dollar;
    public String dollarKeyString = "dollars";
    public long dpc;
    public String dpcKeyString = "dpc";
    public long dps;
    public String dpsKeyString = "dps";
    public long shopDpc;
    public String shopDpcKeyString = "ShopDpc";
    public long shopDps;
    public String shopDpsKeyString = "ShopDps";

    //views
    public TextView dollarsView;
    public TextView dpsAndDpcView;
    public ImageButton dollarBtn;
    public Button shopDpcBtn;
    public Button shopDpsBtn;
    ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        SwipeAdapter swipeAdapter = new SwipeAdapter(getSupportFragmentManager());
        viewPager.setAdapter(swipeAdapter);
        loadPref();
        initialize();

        Thread thread = new Thread() {
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);

                        runOnUiThread(new Runnable() {
                            public void run() {
                                dollar += dps;
                                dollarsView.setText(dollar + " dollars");
                                savePref(dollarKeyString, dollar);

                            }
                        });
                    }
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        };
        thread.start();
    }

    public void initialize() {
        //views
        dollarsView = (TextView) findViewById(R.id.dollarsView);
        dollarsView.setText(dollar + " dollars");

        dpsAndDpcView = (TextView) findViewById(R.id.dpsAndDpcView);
        dpsAndDpcView.setText(dps + "dps | " + dpc + " dpc");

        dollarBtn = (ImageButton) findViewById(R.id.dollarBtn);
        shopDpcBtn = (Button) findViewById(R.id.shopDpc);
        shopDpcBtn.setText("Dpc: +1 | Price: " + shopDpc);

        shopDpsBtn = (Button) findViewById(R.id.shopDps);
        shopDpsBtn.setText("Dps: +1 | Price: " + shopDps);


    }


    public void shopDpc(View v) {
        if (dollar >= shopDpc) {
            dollar -= shopDpc;
            dpc += 1;
            shopDpc *= 1.5;
            shopDpcBtn.setText("Dpc: +1 | Price: " + shopDpc);
            dpsAndDpcView.setText(dps + "dps | " + dpc + " dpc");
            dollarsView.setText(dollar + " dollars");
            savePref(shopDpcKeyString, shopDpc);
            savePref(dpcKeyString, dpc);
            savePref(dollarKeyString, dollar);
        } else {
            Toast.makeText(this, "You need more dollars!", Toast.LENGTH_SHORT).show();
        }
    }

    public void shopDps(View v) {
        if (dollar >= shopDps) {
            dollar -= shopDps;
            dps += 1;
            shopDps *= 1.5;
            shopDpsBtn.setText("Dps: +1 | Price: " + shopDps);
            dpsAndDpcView.setText(dps + " dps | " + dpc + " dpc");
            dollarsView.setText(dollar + " dollars");
            savePref(shopDpsKeyString, shopDps);
            savePref(dpsKeyString, dps);
            savePref(dollarKeyString, dollar);


        } else {
            Toast.makeText(this, "You need more money!", Toast.LENGTH_SHORT).show();
        }
    }


    public void loadPref() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        long dollarKey = sharedPref.getLong(dollarKeyString, 0);
        dollar = dollarKey;

        long dpcKey = sharedPref.getLong(dpcKeyString, 1);
        dpc = dpcKey;

        long dpsKey = sharedPref.getLong(dpsKeyString, 0);
        dps = dpsKey;

        long shopDpsKey = sharedPref.getLong(shopDpsKeyString, 10);
        shopDps = shopDpsKey;

        long shopDpcKey = sharedPref.getLong(shopDpsKeyString, 10);
        shopDpc = shopDpcKey;
    }

    public void savePref(String key, long value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.commit();

    }

    public void dollarBtn(View v) {
        dollar += dpc;
        dollarsView.setText(dollar + " dollars");
        savePref(dollarKeyString, dollar);

    }
}
