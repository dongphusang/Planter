package com.github.planter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // declaring components
    ImageButton firstPlant;
    ImageButton secondPlant;
    ImageButton thirdPlant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // assigning to views
        firstPlant = findViewById(R.id.first_plant);
        secondPlant = findViewById(R.id.second_plant);
        thirdPlant = findViewById(R.id.third_plant);

        // assigning listener to views
        firstPlant.setOnClickListener(this);
        secondPlant.setOnClickListener(this);
        thirdPlant.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.first_plant)
        {
            startActivity(new Intent(getApplicationContext(), StatBoard.class));
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left);
        }
    }
}