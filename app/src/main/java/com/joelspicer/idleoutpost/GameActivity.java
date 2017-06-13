package com.joelspicer.idleoutpost;

        import android.app.Activity;
        import android.content.SharedPreferences;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.preference.PreferenceManager;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.TextView;
        import android.widget.Toast;

        import static android.R.attr.button;
        import static com.joelspicer.idleoutpost.R.id.shopDpb;


public class GameActivity extends Activity {

    //values
    public long dollar;
    public String dollarKeyString = "dollars";
    public long dpc;
    public String dpcKeyString = "dpc";
    public long dps;
    public String dpsKeyString = "dps";
    public long dpb;
    public String dpbKeyString = "dpb";
    public long shopDpc;
    public String shopDpcKeyString = "ShopDpc";
    public long shopDps;
    public String shopDpsKeyString = "ShopDps";
    public long shopDpb;
    public String shopDpbKeyString = "ShopDpb";

    //views
    public TextView dollarsView;
    public TextView dpsAndDpcView;
    public ImageButton dollarBtn;
    public ImageButton bonusBtn;
    public Button shopDpcBtn;
    public Button shopDpsBtn;
    public Button shopDpbBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


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
        dpsAndDpcView.setText(dps + "dps | " + dpc + " dpc | " + dpb + " dpb");

        dollarBtn = (ImageButton) findViewById(R.id.dollarBtn);
        shopDpcBtn = (Button) findViewById(R.id.shopDpc);
        shopDpcBtn.setText("Dpc: +1 | Price: " + shopDpc);


        bonusBtn = (ImageButton)findViewById(R.id.bonusBtn);

        shopDpsBtn = (Button) findViewById(R.id.shopDps);
        shopDpsBtn.setText("Dps: +1 | Price: " + shopDps);

        shopDpbBtn = (Button) findViewById(R.id.shopDpb);
        shopDpbBtn.setText("Dpb: +10 | Price: " + shopDpb);



    }


    public void shopDpc(View v) {
        if (dollar >= shopDpc) {
            dollar -= shopDpc;
            dpc += 1;
            shopDpc *= 1.5;
            shopDpcBtn.setText("Dpc: +1 | Price: " + shopDpc);
            dpsAndDpcView.setText(dps + "dps | " + dpc + " dpc | " + dpb + " dpb");
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
            dpsAndDpcView.setText(dps + "dps | " + dpc + " dpc | " + dpb + " dpb");
            dollarsView.setText(dollar + " dollars");
            savePref(shopDpsKeyString, shopDps);
            savePref(dpsKeyString, dps);
            savePref(dollarKeyString, dollar);


        } else {
            Toast.makeText(this, "You need more dollars!", Toast.LENGTH_SHORT).show();
        }
    }

    public void shopDpb(View v) {
        if (dollar >= shopDpb) {
            dollar -= shopDpb;
            dpb += 10;
            shopDpb *= 1.5;
            shopDpbBtn.setText("Dpb: +10 | Price: " + shopDpb);
            dpsAndDpcView.setText(dps + "dps | " + dpc + " dpc | " + dpb + " dpb");
            dollarsView.setText(dollar + " dollars");
            savePref(shopDpbKeyString, shopDpb);
            savePref(dpbKeyString, dpb);
            savePref(dollarKeyString, dollar);


        } else {
            Toast.makeText(this, "You need more dollars!", Toast.LENGTH_SHORT).show();
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

        long dpbKey = sharedPref.getLong(dpbKeyString, 10);
        dps = dpbKey;

        long shopDpsKey = sharedPref.getLong(shopDpsKeyString, 10);
        shopDps = shopDpsKey;

        long shopDpcKey = sharedPref.getLong(shopDpcKeyString, 10);
        shopDpc = shopDpcKey;

        long shopDpbKey = sharedPref.getLong(shopDpbKeyString, 100);
        shopDpb = shopDpbKey;

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

    public void bonusBtn(View v) {
        dollar += dpb;
        dollarsView.setText(dollar + " dollars");

    }


}
