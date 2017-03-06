package com.joelspicer.idleoutpost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    //values
    public long dot;

    //views
    public TextView dotsView;
    public Button dotBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initialize();
    }

    public void initialize(){
        //views
        dotsView = (TextView) findViewById(R.id.dotsView);
            dotsView.setText(dot + " Dots");

        dotBtn = (Button) findViewById(R.id.dotBtn);

    }

    public void dotBtn(View v){}
}
