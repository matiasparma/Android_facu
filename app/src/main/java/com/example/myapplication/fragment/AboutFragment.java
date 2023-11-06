package com.example.myapplication.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.PersonasAdministradoras;
import com.example.myapplication.PersonasAdministradorasAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {
    private RecyclerView recyclerView;
    private PersonasAdministradorasAdapter personAdapter;
    private List<PersonasAdministradoras> personList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);

        // Configura la lista de personas (debes agregar tus propios datos)
        personList = new ArrayList<>();
        personList.add(new PersonasAdministradoras("Rocio Calderon", R.drawable.rocio, "Descripción de Persona 1"));
        personList.add(new PersonasAdministradoras("Marcos Caballero", R.drawable.marcos, "Descripción de Persona 2"));
        personList.add(new PersonasAdministradoras("Matias Parma", R.drawable.mati, "Descripción de Persona 3"));

        // Configura el adaptador
        personAdapter = new PersonasAdministradorasAdapter(personList);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(personAdapter);

        return rootView;
    }
}
