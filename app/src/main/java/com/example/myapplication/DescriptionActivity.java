package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {
    TextView titleDescriptionTextView;
    TextView cityDescriptionTextView;
    TextView statusDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        ListElement element=(ListElement) getIntent().getSerializableExtra("ListElement");
        titleDescriptionTextView=findViewById(R.id.titleDescriptionTextView);
        cityDescriptionTextView=findViewById(R.id.cityDescriptionTextView);
        statusDescriptionTextView=findViewById(R.id.statusDescriptionTextview);

        titleDescriptionTextView.setText(element.getNombre());
        titleDescriptionTextView.setTextColor(Color.parseColor(element.getColor()));

        cityDescriptionTextView.setText(element.getCity());
        statusDescriptionTextView.setText(element.getPrecio());
        statusDescriptionTextView.setTextColor(Color.GRAY);

    }
}