package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.modelo.Carrito;
import com.example.myapplication.Adapter.CarritoAdapter;
import com.example.myapplication.datos.DDBBCarrito;
import com.example.myapplication.R;
import com.example.myapplication.activity.DescriptionActivity;

import java.util.List;


public class CartFragment extends Fragment {

    List<Carrito> elementosCarrito;
    RecyclerView recyclerView;
    View rootView;
    CarritoAdapter adapter;
    TextView totalString;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_cart, container, false);
        init();
        return rootView;
    }

    public void init() {
        recyclerView = rootView.findViewById(R.id.recyclerViewCarrito);
        totalString=rootView.findViewById(R.id.numeroTotalTV);
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
            }
        });
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

}

