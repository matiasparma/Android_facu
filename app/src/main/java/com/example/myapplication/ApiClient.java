package com.example.myapplication;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class ApiClient {
    private static final String BASE_URL = "https://pmgh24ms-3000.brs.devtunnels.ms/";
    private static RequestQueue requestQueue;

    public static void enviarPedido(Context context, String jsonPedido) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context);
        }

        String url = BASE_URL + "orders/create";

        try {
            JSONObject jsonBody = new JSONObject(jsonPedido);

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            // Manejar la respuesta exitosa
                            Toast.makeText(context, "Pedido enviado con éxito", Toast.LENGTH_SHORT).show();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // Manejar el error en la respuesta
                            Toast.makeText(context, "Error al enviar el pedido", Toast.LENGTH_SHORT).show();
                        }
                    });

            requestQueue.add(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
