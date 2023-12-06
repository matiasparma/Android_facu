package com.example.myapplication.Adapter.controller;
import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.Map;

public class MyApiManager {
    private static MyApiManager instance;
    private RequestQueue requestQueue;

    private MyApiManager(Context context) {
        // Inicializa la cola de solicitudes
        requestQueue = Volley.newRequestQueue(context);
    }

    public static synchronized MyApiManager getInstance(Context context) {
        if (instance == null) {
            instance = new MyApiManager(context);
        }
        return instance;
    }

    public void makePostRequest(String url, Map<String, String> params, Response.Listener<String> successListener, Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, successListener, errorListener) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
