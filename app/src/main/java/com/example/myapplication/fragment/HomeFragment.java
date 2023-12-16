package com.example.myapplication.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.ProductosAPI;
import com.example.myapplication.activity.DescriptionActivity;
import com.example.myapplication.Adapter.ListAdapter;
import com.example.myapplication.ListElement;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements ProductosAPI.ProductosCallback {
    List<ListElement> elements;
    List<ListElement> elements2;
    View rootView;

    SearchView searchView;

    ListAdapter listAdapter;

    Button botonCarrito, botonLogout;
    ProductosAPI productosAPI;
    RequestQueue requestQueue;


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
        elements2 = new ArrayList<>();
        botonCarrito=rootView.findViewById(R.id.buttonCarrito);
        //botonLogout=rootView.findViewById(R.id.buttonLogout);
        accionBotonCarrito();
        //accionBotonLogout();

        Context context=getActivity();
        requestQueue= Volley.newRequestQueue(context);
        productosAPI=new ProductosAPI();
        productosAPI.makeRequest(requestQueue, context, this);
        //Toast.makeText(context, ""+elements2, Toast.LENGTH_SHORT).show();


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


    @Override
    public void onSuccess(ArrayList<ListElement> listElements) {
        elements2 = listElements;
        listAdapter=new ListAdapter(elements2, getContext(), new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {

                moveToDescription(item);
            }
        });


        RecyclerView recyclerView = rootView.findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listAdapter);
    }

    @Override
    public void onError(String errorMessage) {
        Toast.makeText(getContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();

    }
}