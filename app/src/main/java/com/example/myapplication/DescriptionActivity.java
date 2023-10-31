package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DescriptionActivity extends AppCompatActivity{
    TextView titleDescriptionTextView;
    TextView cityDescriptionTextView;
    TextView precioDescriptionTextView;
    TextView codigoDescriptionTextView;

    private ArrayList<Carrito> elementosCarrito = new ArrayList<>();


    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        ListElement element = (ListElement) getIntent().getSerializableExtra("ListElement");
        titleDescriptionTextView = findViewById(R.id.titleDescriptionTextView);
        cityDescriptionTextView = findViewById(R.id.cityDescriptionTextView);
        precioDescriptionTextView = findViewById(R.id.statusDescriptionTextview);
        codigoDescriptionTextView=findViewById(R.id.codigoDescriptionTextView);
        spinner = findViewById(R.id.mySpinner);

        titleDescriptionTextView.setText(element.getNombre());
        titleDescriptionTextView.setTextColor(Color.BLACK);

        cityDescriptionTextView.setText(element.getCity());
        precioDescriptionTextView.setText(element.getPrecio());
        precioDescriptionTextView.setTextColor(Color.GRAY);
        codigoDescriptionTextView.setText(element.getCodigo());

        String[] cantidades = {"1", "2", "3", "4", "5", "6"};
        ArrayAdapter<String> adapterCant = new ArrayAdapter<>(this, R.layout.list_cantidad, cantidades);
        adapterCant.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue = cantidades[position];
                String displayText = "Cantidad: " + selectedValue;
                ((TextView) parentView.getChildAt(0)).setText(displayText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });

        spinner.setAdapter(adapterCant);
    }

    public void elementosDescripcion(View v) {
        String nombre = titleDescriptionTextView.getText().toString();
        String selectedValueStr = (String) spinner.getSelectedItem();
        int cantidad = Integer.parseInt(selectedValueStr);
        double precio = Double.parseDouble(precioDescriptionTextView.getText().toString());
        int codigo=Integer.parseInt(codigoDescriptionTextView.getText().toString());
        DDBBCarrito.registrar(this,codigo,nombre,precio,cantidad,"","");

    }


    public void borrarElementos(View v) {
        DDBBCarrito.eliminarBD(this);
    }

}

