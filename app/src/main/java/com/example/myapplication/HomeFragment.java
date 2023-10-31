package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.EventLogTags;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment  {
    List<ListElement> elements;
    List<ListElement> elements2;
    View rootView;

    SearchView searchView;

    ListAdapter listAdapter;


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
        elements.add(new ListElement("1","#565678","Pantalon","Argentina","10000","pantalon","nike"));
        elements.add(new ListElement("2","#ffffff","Remera","bolivia","5000","remera","adidas"));
        elements.add(new ListElement("3","#000000","Ojotas","brasil","2000","ojotas","nike"));
        elements.add(new ListElement("4","#f6567a","Zapatillas","Argentina","20000","zapatillas","adidas"));
        elements.add(new ListElement("5","#d65678","Camisa","Argentina","15000","camisa","nike"));
        elements.add(new ListElement("6","#aa5678","Corbata","Argentina","6500","corbata","adidas"));
        elements.add(new ListElement("7","#565678","Chaqueta","Argentina","14400","chaqueta","Arg"));
        elements.add(new ListElement("8","#565678","Campera","Argentina","30000","campera","nike"));
        elements.add(new ListElement("9","#565678","Medias","Argentina","5500","media","nike"));
        elements.add(new ListElement("10","#565678","Gorro","Argentina","3650","gorro","adidas"));
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
}