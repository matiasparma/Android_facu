package com.example.myapplication.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.modelo.Carrito;
import com.example.myapplication.datos.DDBBCarrito;
import com.example.myapplication.R;
import com.example.myapplication.fragment.CartFragment;


public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {
    private List<Carrito> elementosCarrito;
    private CartFragment fragment;


    public CarritoAdapter(List<Carrito> elementosCarrito,CartFragment fragment) {
        this.elementosCarrito = elementosCarrito;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.carrito_elements, parent, false);
        return new ViewHolder(view);
    }
    public void setElementosCarrito(List<Carrito> elementosCarrito) {
        this.elementosCarrito = elementosCarrito;
        notifyDataSetChanged(); // Notificar al adaptador de que los datos han cambiado
    }
    public List<Carrito> getItems() {
        return elementosCarrito;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Carrito carrito = elementosCarrito.get(position);
        holder.nameTextView.setText(carrito.getNombre());
        holder.precioTextView.setText(String.valueOf(carrito.getPrecio()));
        holder.cantidadTextview.setText(String.valueOf(carrito.getCantidad()));
        holder.codigoTextView.setText(String.valueOf(carrito.getCodigo()));
        holder.deleteButton.setTag(Integer.parseInt(carrito.getCodigo()));
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int codigo = (int) v.getTag();
                String codigoString=String.valueOf(codigo);
                DDBBCarrito.eliminarArti(v.getContext(), codigoString);
                int adapterPosition = holder.getAdapterPosition(); // Obten la posición del adaptador

                if (adapterPosition != RecyclerView.NO_POSITION) {
                    elementosCarrito.remove(adapterPosition);
                    notifyItemRemoved(adapterPosition);
                    fragment.Carritototal(v);
                    fragment.clientCantid();
                    boolean carrito=DDBBCarrito.tieneElementos(v.getContext());
                    Activity activity=fragment.getActivity();
                    if(!carrito) fragment.abrirMain(activity);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return elementosCarrito.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, precioTextView, cantidadTextview,codigoTextView;
        Button deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            precioTextView = itemView.findViewById(R.id.precioTextView);
            cantidadTextview = itemView.findViewById(R.id.cantidadTextview);
            codigoTextView=itemView.findViewById(R.id.codigoTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
