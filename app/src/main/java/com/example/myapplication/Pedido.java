package com.example.myapplication;

import com.example.myapplication.modelo.ArticuloPedido;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    public static String construirJSON(int idSucursal, int idCliente, double totalAmount, List<ArticuloPedido> items) {
        try {
            JSONObject jsonPedido = new JSONObject();
            jsonPedido.put("id_sucursal", idSucursal);
            jsonPedido.put("id_cliente", idCliente);
            jsonPedido.put("total_amount", totalAmount);

            JSONArray jsonArrayData = new JSONArray();

            for (ArticuloPedido item : items) {
                JSONObject jsonItem = new JSONObject();
                jsonItem.put("id_articulo", item.getIdArticulo());
                jsonItem.put("amount", item.getAmount());
                jsonItem.put("quantity", item.getQuantity());
                jsonArrayData.put(jsonItem);
            }

            jsonPedido.put("data", jsonArrayData);

            return jsonPedido.toString();

        } catch (JSONException e) {
            e.printStackTrace();
            return null; // Manejar el error apropiadamente en tu aplicación
        }
    }

    public static class ItemCarrito {
        private int idArticulo;
        private double amount;
        private int quantity;

        public ItemCarrito(int idArticulo, double amount, int quantity) {
            this.idArticulo = idArticulo;
            this.amount = amount;
            this.quantity = quantity;
        }

        public int getIdArticulo() {
            return idArticulo;
        }

        public double getAmount() {
            return amount;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}

