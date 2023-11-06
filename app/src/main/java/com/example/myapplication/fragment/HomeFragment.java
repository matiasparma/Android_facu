package com.example.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.EventLogTags;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.ActivityController;
import com.example.myapplication.ArchivoTXTController;
import com.example.myapplication.DescriptionActivity;
import com.example.myapplication.ListAdapter;
import com.example.myapplication.ListElement;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment  {
    List<ListElement> elements;
    List<ListElement> elements2;
    View rootView;

    SearchView searchView;

    ListAdapter listAdapter;

    Button botonCarrito, botonLogout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        searchView = rootView.findViewById(R.id.searchView);
        searchView.clearFocus();



        init();
        return rootView;
    }
    public void init(){
        elements=new ArrayList<>();
        elements2 = new ArrayList<>(elements);
        botonCarrito=rootView.findViewById(R.id.buttonCarrito);
        botonLogout=rootView.findViewById(R.id.buttonLogout);
        accionBotonCarrito();
        accionBotonLogout();
        elements.add(new ListElement("1","Pantalon","Argentina","10000","pantalon","nike"));
        elements.add(new ListElement("2","Remera","bolivia","5000","remera","adidas"));
        elements.add(new ListElement("3","Ojotas","brasil","2000","ojotas","nike"));
        elements.add(new ListElement("4","Zapatillas","Argentina","20000","zapatillas","adidas"));
        elements.add(new ListElement("5","Camisa","Argentina","15000","camisa","nike"));
        elements.add(new ListElement("6","Corbata","Argentina","6500","corbata","adidas"));
        elements.add(new ListElement("7","Chaqueta","Argentina","14400","chaqueta","Arg"));
        elements.add(new ListElement("8","Campera","Argentina","30000","campera","nike"));
        elements.add(new ListElement("9","Medias","Argentina","5500","media","nike"));
        elements.add(new ListElement("10","Gorro","Argentina","3650","gorro","adidas"));
        Context context=getActivity();
        listAdapter=new ListAdapter(elements, context, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {

                moveToDescription(item);
            }
        });


        RecyclerView recyclerView = rootView.findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(listAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Aquí puedes manejar la acción cuando se envía la búsqueda (puedes omitirlo si no es necesario)
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
             filterList(newText);
             return true;
            }
        });

    }
    private void filterList(String text) {
        List<ListElement> filteredElements = new ArrayList<>();

        for (ListElement element : elements) {
            if (element.getNombre().toLowerCase().contains(text.toLowerCase())) {
                filteredElements.add(element);
            }
        }
        if (filteredElements.isEmpty()){
            Toast.makeText(getContext(),"no hay datos",Toast.LENGTH_SHORT).show();
        }else{
            listAdapter.setFilterList(filteredElements);
        }
    }

    public void moveToDescription(ListElement item){
        Intent intent=new Intent(getContext(), DescriptionActivity.class);
        intent.putExtra("ListElement",item);
        startActivity(intent);
    }
    private void accionBotonCarrito(){
        botonCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CartFragment cartFragment = new CartFragment();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, cartFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

    private void accionBotonLogout(){
        botonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArchivoTXTController.resetearTXT(getActivity());
                ActivityController.abrirLogin(getActivity());
                Toast.makeText(getActivity(), "Logout!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}