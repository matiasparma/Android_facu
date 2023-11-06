package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private static List<ListElement> mData;

    private LayoutInflater minflater;
    private Context context;
    final ListAdapter.OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(ListElement item);
    }
    public void setFilterList(List<ListElement> filteredList){
        this.mData=filteredList;
        notifyDataSetChanged();
    }

    public ListAdapter(List<ListElement> itemlist, Context context,ListAdapter.OnItemClickListener listener){
        this.minflater=LayoutInflater.from(context);
        this.context=context;
        this.mData=itemlist;
        this.listener=listener;

    }

    @Override
    public int getItemCount(){return mData.size();}
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view=minflater.from(parent.getContext()).inflate(R.layout.list_element,parent,false);
        return new ListAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder,final int position){
        holder.cv.setAnimation(AnimationUtils.loadAnimation(context,R.anim.fade_transition));
        holder.bindData(mData.get(position));

    }
    public void setItems(List<ListElement> items){mData=items;}
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView name ,city,status,codigo;
        CardView cv;
        ViewHolder(View itemView){
            super(itemView);
            iconImage=itemView.findViewById(R.id.iconImageView);
            name=itemView.findViewById(R.id.nameTextView);
            city=itemView.findViewById(R.id.cityTextView);
            status=itemView.findViewById(R.id.statusTextview);
            cv=itemView.findViewById(R.id.cv);
            codigo=itemView.findViewById(R.id.codigoTextView);
        }
        void bindData(final ListElement item){
            name.setText(item.getNombre());
            city.setText(item.getCity());
            status.setText("$"+item.getPrecio());
            codigo.setText(item.getCodigo());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }




    }
}

