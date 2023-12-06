package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Adapter.controller.nombreManager;
import com.example.myapplication.ApiClient;
import com.example.myapplication.Pedido;
import com.example.myapplication.activity.ClienteActivity;
import com.example.myapplication.modelo.ArticuloPedido;
import com.example.myapplication.modelo.Carrito;
import com.example.myapplication.Adapter.CarritoAdapter;
import com.example.myapplication.datos.DDBBCarrito;
import com.example.myapplication.R;
import com.example.myapplication.activity.DescriptionActivity;

import java.util.ArrayList;
import java.util.List;


public class CartFragment extends Fragment {

    List<Carrito> elementosCarrito;
    RecyclerView recyclerView;
    View rootView;
    CarritoAdapter adapter;
    TextView totalString, clienteId,nunPoducto;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        init();
        return rootView;
    }

    public void init() {
        recyclerView = rootView.findViewById(R.id.recyclerViewCarrito);
        totalString=rootView.findViewById(R.id.numeroTotalTV);
        clienteId=rootView.findViewById(R.id.textViewClienteID);
        nunPoducto=rootView.findViewById(R.id.textViewProductos);
        clientCantid();
        // Obtén la lista de elementos desde la base de datos usando DDBBCarrito
        elementosCarrito = DDBBCarrito.obtenerArticulos(getActivity());
        //Carritototal();
        // Configura el RecyclerView con un LinearLayoutManager y tu CarritoAdapter
        double totalDouble=DDBBCarrito.obtenertotal(getActivity());
        totalString.setText(String.valueOf(totalDouble));
        adapter = new CarritoAdapter(elementosCarrito,this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CarritototalJson(view);
                double total=Double.parseDouble(totalString.getText().toString());
                int sucursal=1;
                int cliente=Integer.parseInt(clienteId.getText().toString());
                List<ArticuloPedido> listaPedido=new ArrayList<>();
                listaPedido=hacerList();
                String jsonPedido =Pedido.construirJSON(sucursal,cliente,total,listaPedido);
                ApiClient.enviarPedido(getContext(), jsonPedido);
            }
        });
    }
    private List<ArticuloPedido> hacerList(){
        CarritoAdapter carritoAdapter = (CarritoAdapter) recyclerView.getAdapter();
        List<Carrito> listaElementosCarrito = carritoAdapter.getItems();
        List<ArticuloPedido> listaPedido=new ArrayList<>();
        if (listaElementosCarrito != null && !listaElementosCarrito.isEmpty()) {

            for (Carrito carrito : listaElementosCarrito) {

                int id=Integer.parseInt(carrito.getCodigo());
                double precio=carrito.getPrecio();
                int cantidad=carrito.getCantidad();
                listaPedido.add(new ArticuloPedido(id,precio,cantidad));
            }

        } else {
            Toast.makeText(getContext(), "La lista de elementos del carrito está vacía.", Toast.LENGTH_SHORT).show();
        }
        return listaPedido;
    }
    public void CarritototalJson(View v){
        double totalDouble=DDBBCarrito.obtenertotal(getActivity());
        //Toast.makeText(getActivity(), ""+totalDouble, Toast.LENGTH_SHORT).show();
        DescriptionActivity descriptionActivity=new DescriptionActivity();
        descriptionActivity.crearJson(totalDouble,getActivity());
    }
    public void Carritototal(View v){
        double totalDouble=DDBBCarrito.obtenertotal(getActivity());
        totalString.setText(String.valueOf(totalDouble));
    }
    private void clientCantid(){
        nombreManager manager=new nombreManager(getActivity());
        String cliente=manager.obtenerTexto();
        clienteId.setText(cliente);
        int num=DDBBCarrito.obtenerCantidadElementos(getActivity());
        nunPoducto.setText("("+String.valueOf(num)+")");
    }

}

