package com.example.myapplication.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.modelo.Carrito;
import com.example.myapplication.datos.DDBBCarrito;
import com.example.myapplication.ListElement;
import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
public class DescriptionActivity extends AppCompatActivity{
    TextView titleDescriptionTextView;
    TextView cityDescriptionTextView;
    TextView precioDescriptionTextView;
    TextView codigoDescriptionTextView;
    Carrito carrito;
    private static final String API_URL = "https://pmgh24ms-3000.brs.devtunnels.ms/orders/create";

    private ArrayList<Carrito> elementosCarrito = new ArrayList<>();
    private List<Map<String, Object>> data = new ArrayList<>();


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

    public void elementosDescripcion(View v) throws JSONException {
        String nombre = titleDescriptionTextView.getText().toString();
        String selectedValueStr = (String) spinner.getSelectedItem();
        int cantidad = Integer.parseInt(selectedValueStr);
        double precio = Double.parseDouble(precioDescriptionTextView.getText().toString());
        int codigo=Integer.parseInt(codigoDescriptionTextView.getText().toString());
        DDBBCarrito.registrar(this,codigo,nombre,precio,cantidad,"","");
        String codstr=String.valueOf(codigo);
        carrito=new Carrito(codstr,precio,cantidad);
        elementosCarrito.add(carrito);

        Toast.makeText(this, "Agregado al carrito de compras", Toast.LENGTH_SHORT).show();

    }
    public void crearJson(double total,Context context){
        // Suponiendo que ya tienes elementosCarrito lleno
        JSONArray jsonArray = new JSONArray();
        for (Carrito carritoItem : elementosCarrito) {
            JSONObject jsonItem = new JSONObject();
            try {
                jsonItem.put("id_articulo", Integer.parseInt(carritoItem.getCodigo()));
                jsonItem.put("amount", carritoItem.getPrecio());
                jsonItem.put("quantity", carritoItem.getCantidad());
                jsonArray.put(jsonItem);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

// JSON final
        JSONObject jsonFinal = new JSONObject();
        try {
            jsonFinal.put("id_sucursal", 1);
            jsonFinal.put("id_cliente", 4);
            jsonFinal.put("total_amount", total);
            jsonFinal.put("data", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        // Crear la solicitud POST
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, API_URL, jsonFinal,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Manejar la respuesta exitosa

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar errores de la solicitud
                        Log.e("ApiRequest", "Error en la solicitud", error);

                    }
                });

        // Agregar la solicitud a la cola
        requestQueue.add(jsonObjectRequest);
    }
    }





