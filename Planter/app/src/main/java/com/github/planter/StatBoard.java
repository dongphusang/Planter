package com.github.planter;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class StatBoard extends AppCompatActivity implements View.OnTouchListener {

    // declaring components
    private static TextView humidityStat;
    private static TextView tempStat;
    private static TextView ventStat;
    private static TextView soilMoistStat;
    private static TextView lighting;
    private static TextView wateringStat;
    private static TextView warning;
    private static TextView plantName;
    private static TextView dayExisted;
    private static ImageView image; // possibly...?
    private static CloudRetriever influxObject;
    private static LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_board);

        // assigning to views
        humidityStat = findViewById(R.id.humidity_stat);
        tempStat = findViewById(R.id.temp_stat);
        ventStat = findViewById(R.id.venti_stat);
        soilMoistStat = findViewById(R.id.soilmoist_stat);
        wateringStat = findViewById(R.id.watering_stat);
        lighting = findViewById(R.id.lighting_stat);
        warning = findViewById(R.id.warning);
        plantName = findViewById(R.id.plant_name);
        dayExisted = findViewById(R.id.days_existed);
        linearLayout = findViewById(R.id.statBoard);

        // initialize influxDB object
        influxObject = new CloudRetriever();

        // assign listener to linear layout
        linearLayout.setOnTouchListener(this);

        // run data retrieval
        influxObject.execute();

    }

    // updating sensors info on screen
    public static void updateTextViews() {
        lighting.setText(influxObject.getDataCollection()[2]);
        warning.setText(influxObject.getDataCollection()[0]);
        soilMoistStat.setText(influxObject.getDataCollection()[1]);
        humidityStat.setText(influxObject.getDataCollection()[3]+"%");
        tempStat.setText(influxObject.getDataCollection()[4]+"C");
        ventStat.setText(influxObject.getDataCollection()[5]);
        wateringStat.setText(influxObject.getDataCollection()[6]+"m");
    }


    // refresh data displayed on screen upon touch
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        influxObject = new CloudRetriever();
        influxObject.execute();
        return true;
    }
}