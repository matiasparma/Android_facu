package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ScrollView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.PedidosAPI;
import com.example.myapplication.R;
import com.example.myapplication.modelo.Pedido;

import java.util.ArrayList;

public class ContenidoPedidoActivity extends AppCompatActivity {

    private LinearLayout layoutListaPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contenido_pedido);

        layoutListaPedidos = findViewById(R.id.layoutListaPedidos);

        // Inicializar la cola de solicitudes Volley
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        // Crear una instancia de PedidosAPI
        PedidosAPI pedidosAPI = new PedidosAPI();

        // Hacer la solicitud a la API y manejar los resultados en un callback
        pedidosAPI.makeRequest(requestQueue, this, new PedidosAPI.PedidoCallback() {
            @Override
            public void onSuccess(ArrayList<Pedido> listaPedidos) {
                // Procesar y mostrar los pedidos en el LinearLayout
                mostrarPedidos(listaPedidos);
            }

            @Override
            public void onError(String errorMessage) {
                // Manejar errores, por ejemplo, mostrar un mensaje de error
                // (puedes personalizar cómo manejas los errores en el diseño)
                TextView errorTextView = new TextView(ContenidoPedidoActivity.this);
                errorTextView.setText("Error: " + errorMessage);
                layoutListaPedidos.addView(errorTextView);
            }
        });
    }

    private void mostrarPedidos(ArrayList<Pedido> listaPedidos) {
        for (Pedido pedido : listaPedidos) {
            // Crear dinámicamente TextView para cada pedido y agregarlo al LinearLayout
            TextView pedidoTextView = new TextView(this);
            pedidoTextView.setText("Número Comprobante: " + pedido.getNumeroComprobante() + "\n" +
                    "Código Usuario: " + pedido.getCodigoUsuario() + "\n" +
                    "Código Artículo: " + pedido.getCodigoArticulo() + "\n" +
                    "Precio: " + pedido.getPrecio() + "\n" +
                    "Cantidad: " + pedido.getCantidad() + "\n" +
                    "Estado: " + pedido.getEstado() + "\n\n");
            // Establecer el estilo de la letra
            pedidoTextView.setTextSize(20);  // Cambiar el tamaño del texto
            pedidoTextView.setTextColor(getResources().getColor(R.color.black));  // Cambiar el color del texto
            layoutListaPedidos.addView(pedidoTextView);
        }
    }
}
