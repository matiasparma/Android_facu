package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.R;
import com.example.myapplication.controller.ActivityController;


public class SettingsFragment extends Fragment {
    Button btnClient, btnPedidos, btnVolver;
    View rootView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_settings, container, false);
        btnClient = rootView.findViewById(R.id.btnCliente);
        btnPedidos=rootView.findViewById(R.id.btnpedidos);
        btnVolver=rootView.findViewById(R.id.btnvolver);
        abrirClientes();
        voverMenu();
        abrirPedidos();
        return rootView;
    }
    public void abrirClientes(){
        btnClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityController.abrirCliente(getContext());
            }
        });
    }
    public void abrirPedidos(){
        btnPedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityController.abrirContenidoPedido(getContext());
            }
        });
    }
    public void voverMenu(){
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityController.abrirMain(getContext());
            }
        });
    }
}