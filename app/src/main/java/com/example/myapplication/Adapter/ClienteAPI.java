package com.example.myapplication.Adapter;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.myapplication.controller.TokenManager;
import com.example.myapplication.modelo.Cliente;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
public class ClienteAPI {
    public interface ClienteCallback {
        void onSuccess(ArrayList<Cliente> listElements);
        void onError(String errorMessage);
    }

    //  private static final String stringurl1 = "https://my-json-server.typicode.com/typicode/demo/comments";
    //private static final String stringurl1 = "https://pmgh24ms-3000.brs.devtunnels.ms/productos/getAll/?id=3&cant=10&page=0";
    private static final String stringurl1 = "https://pmgh24ms-3000.brs.devtunnels.ms/users/allUsers";

    public void makeRequest(RequestQueue requestQueue, Context context, ClienteAPI.ClienteCallback callback) {
        ArrayList<Cliente> listElements = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                stringurl1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int ok = jsonResponse.getInt("ok");
                            if (ok == 1) {
                                JSONArray dataObject = jsonResponse.getJSONArray("data");
                                //JSONArray productsArray = dataObject.getJSONArray("products");

                                for (int i = 0; i < dataObject.length(); i++) {
                                    JSONObject productObject = dataObject.getJSONObject(i);
                                    int id = productObject.getInt("id");
                                    String codigo = productObject.getString("usuario");
                                    String marca = productObject.getString("razon social");

                                    String idS = String.valueOf(id);
                                    String razonSocial = String.valueOf(codigo);
                                    String tipoUsuario = marca;

                                    Cliente listElement = new Cliente(idS,razonSocial,marca);
                                    listElements.add(listElement);
                                }

                                // Éxito: llama al método onSuccess en el callback
                                callback.onSuccess(listElements);
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
