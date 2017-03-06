package com.joelspicer.idleoutpost;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends Activity {

    //values
    public long dot;
    public String dotKeyString = "dots";
    public long dpc;
    public String dpcKeyString = "dpc";
    public long dps;
    public String dpsKeyString = "dps";

    //views
    public TextView dotsView;
    public Button dotBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        loadPref();
        initialize();
    }

    public void initialize() {
        //views
        dotsView = (TextView) findViewById(R.id.dotsView);
        dotsView.setText(dot + " Dots");

        dotBtn = (Button) findViewById(R.id.dotBtn);

    }

    public void loadPref() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        long dotKey = sharedPref.getLong(dotKeyString, 0);
        dot = dotKey;

        long dpcKey = sharedPref.getLong(dpcKeyString, 1);
        dpc = dpcKey;

        long dpsKey = sharedPref.getLong(dpsKeyString, 0);
        dps = dpsKey;
    }

    public void savePref(String key, long value) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(key, value);
        editor.commit();

    }

    public void dotBtn(View v) {
        dot += dpc;
        dotsView.setText(dot + " Dots");
        savePref(dotKeyString, dot);

    }
}
