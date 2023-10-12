package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    List<ListElement> elements;
    View rootView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        init();
        return rootView;
    }
    public void init(){
        elements=new ArrayList<>();
        elements.add(new ListElement("#565678","pedro","Argentina","Activo"));
        elements.add(new ListElement("#ffffff","Matias","bolivia","Activo"));
        elements.add(new ListElement("#000000","juan","brasil","Activo"));
        elements.add(new ListElement("#f6567a","carlos","Argentina","Activo"));
        elements.add(new ListElement("#d65678","rober","Argentina","Activo"));
        elements.add(new ListElement("#aa5678","paty","Argentina","Activo"));
        elements.add(new ListElement("#565678","javi","Argentina","Activo"));
        elements.add(new ListElement("#565678","pini","Argentina","Activo"));
        elements.add(new ListElement("#565678","tete","Argentina","Activo"));
        elements.add(new ListElement("#565678","jenny","Argentina","Activo"));
        Context context=getActivity();
        ListAdapter listAdapter=new ListAdapter(elements,context);
        RecyclerView recyclerView = rootView.findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(listAdapter);


    }
    public void irSetting(View v){

    }
}