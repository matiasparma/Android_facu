package com.example.myapplication;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.controller.TokenManager;
import com.example.myapplication.modelo.Pedido;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidosAPI {
    public interface PedidoCallback {
        void onSuccess(ArrayList<Pedido> listaPedidos);
        void onError(String errorMessage);
    }

    private static final String stringurl1 = "https://pmgh24ms-3000.brs.devtunnels.ms/orders/?page=1&cant=1000";

    public void makeRequest(RequestQueue requestQueue, Context context, PedidoCallback callback) {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                stringurl1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int ok = jsonResponse.getInt("ok");
                            if (ok == 1) {
                                JSONArray data = jsonResponse.getJSONArray("data");

                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject pedidoObject = data.getJSONObject(i);
                                    int numeroComprobante = pedidoObject.getInt("numero_comprobante");
                                    int codigoUsuario = pedidoObject.getInt("codigo_usuario");
                                    String codigoArticulo = pedidoObject.getString("codigo_articulo");
                                    double precio = pedidoObject.getDouble("precio");
                                    int cantidad = pedidoObject.getInt("cantidad");
                                    String estado = pedidoObject.getString("estado");

                                    Pedido pedido = new Pedido(numeroComprobante, codigoUsuario, codigoArticulo, precio, cantidad, estado);
                                    listaPedidos.add(pedido);
                                }

                                // Éxito: llama al método onSuccess en el callback
                                callback.onSuccess(listaPedidos);
                            } else {
                                // Manejar el caso en el que 'ok' no es 1
                                callback.onError("Error en la respuesta del servidor");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Error: llama al método onError en el callback
                            callback.onError("Error parsing JSON");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejo de errores aquí
                        // Error: llama al método onError en el callback
                        callback.onError("Error en la solicitud Volley");
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                // Obtener el token y agregarlo al encabezado de autorización
                TokenManager tokenManager = TokenManager.getInstance(context);
                String token = tokenManager.getToken();
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        requestQueue.add(stringRequest);
    }
}
