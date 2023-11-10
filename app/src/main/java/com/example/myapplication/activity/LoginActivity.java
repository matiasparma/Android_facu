package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.myapplication.MyApiManager;
import com.example.myapplication.R;
import com.example.myapplication.TokenManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText usernameEditText;
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.editTextUsuario);
        passwordEditText = findViewById(R.id.editTextTextPassword);
    }

    public void login(View view) {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa ambos campos", Toast.LENGTH_SHORT).show();
            return;
        }

        MyApiManager apiManager = MyApiManager.getInstance(this);
        String url = "https://pmgh24ms-3000.brs.devtunnels.ms/auth/login";

        Map<String, String> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);

        apiManager.makePostRequest(url, params,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            int ok = jsonResponse.getInt("ok");

                            if (ok == 1) {
                                JSONObject userData = jsonResponse.getJSONObject("data");
                                String usuario = userData.getString("usuario");
                                String token = userData.getString("token");


                                //realizar acciones adicionales, como guardar el token en SharedPreferences
                                TokenManager tokenManager = TokenManager.getInstance(LoginActivity.this);
                                tokenManager.saveToken(token);
                                // próxima pantalla de la aplicación
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("token", token);
                                startActivity(intent);
                                finish();
                            } else {
                                // La solicitud fue exitosa, pero el "ok" no es igual a 1, lo que podría indicar un error en el servidor
                                // Maneja el caso de error aquí
                                Toast.makeText(LoginActivity.this, "Inicio de sesión fallido", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Maneja errores de análisis JSON aquí
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Manejar errores en la solicitud aquí
                        Toast.makeText(LoginActivity.this, "Error de red", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
}
