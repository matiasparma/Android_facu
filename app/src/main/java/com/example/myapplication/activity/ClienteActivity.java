package com.example.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.myapplication.Adapter.ClienteAPI;
import com.example.myapplication.Adapter.ClienteAdapter;
import com.example.myapplication.R;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Adapter.controller.ActivityController;
import com.example.myapplication.Adapter.controller.nombreManager;
import com.example.myapplication.modelo.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteActivity extends AppCompatActivity implements ClienteAPI.ClienteCallback{
    RecyclerView recyclerView;
    ClienteAdapter adapter;
    SearchView searchView;
    List<Cliente> elements;
    List<Cliente> elements2;

    RequestQueue requestQueue;

    ClienteAPI clienteAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        init();
    }
    private List listaClientes(){

        elements=new ArrayList<>();
        elements.add(new Cliente("1","23456","comprador"));
        elements.add(new Cliente("2","34567","vendedor"));
        elements.add(new Cliente("3","43344","comprador"));
        return elements;
    }
    private void init(){

        recyclerView=findViewById(R.id.recyclerView);
       /* adapter=new ClienteAdapter(listaClientes(), this, new ClienteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Cliente item) {
                Toast.makeText(ClienteActivity.this, "funciona", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/
        requestQueue=Volley.newRequestQueue(this);
        clienteAPI=new ClienteAPI();
        clienteAPI.makeRequest(requestQueue, this, this);
        filtro();


    }
    private void filtro(){
        searchView = findViewById(R.id.searchView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
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
        List<Cliente> filteredElements = new ArrayList<>();

        for (Cliente element : elements) {
            if (element.getTipoUsuario().toLowerCase().contains(text.toLowerCase())) {
                filteredElements.add(element);
            }
        }
        if (filteredElements.isEmpty()){
            Toast.makeText(this,"no hay datos",Toast.LENGTH_SHORT).show();
        }else{
            adapter.setFilterList(filteredElements);
        }
    }

    @Override
    public void onSuccess(ArrayList<Cliente> listElements) {
        elements2=listElements;
        adapter=new ClienteAdapter(elements2, this, new ClienteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Cliente item) {
                String idCliente=item.getId();

                Toast.makeText(ClienteActivity.this, "id: "+idCliente, Toast.LENGTH_SHORT).show();
                nombreManager manager=new nombreManager(ClienteActivity.this);
                manager.guardarTexto(idCliente);
                ActivityController.abrirMain(ClienteActivity.this);
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onError(String errorMessage) {

    }
}
