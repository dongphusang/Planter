package com.github.planter;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class StatBoard extends AppCompatActivity {

    // declaring components
    TextView humidityStat;
    TextView tempStat;
    TextView ventStat;
    TextView soilMoistStat;
    TextView waterTankStat;
    TextView wateringStat;
    TextView warning;
    TextView plantName;
    TextView dayExisted;
    ImageView image; // possibly...?
    PlanterData unit;


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
        waterTankStat = findViewById(R.id.watertank_stat);
        warning = findViewById(R.id.warning);
        plantName = findViewById(R.id.plant_name);
        dayExisted = findViewById(R.id.days_existed);

        unit = new PlanterData();



    }


}