package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<ListElement> mData;
    private LayoutInflater minflater;
    private Context context;

    public ListAdapter(List<ListElement> itemlist, Context context){
        this.minflater=LayoutInflater.from(context);
        this.context=context;
        this.mData=itemlist;

    }
    @Override
    public int getItemCount(){return mData.size();}
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view=minflater.inflate(R.layout.list_element, null);
        return new ListAdapter.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final ListAdapter.ViewHolder holder,final int position){
        holder.bindData(mData.get(position));

    }
    public void setItems(List<ListElement> items){mData=items;}
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView name ,city,status;
        ViewHolder(View itemView){
            super(itemView);
            iconImage=itemView.findViewById(R.id.iconImageView);
            name=itemView.findViewById(R.id.nameTextView);
            city=itemView.findViewById(R.id.cityTextView);
            status=itemView.findViewById(R.id.statusTextview);
        }
        void bindData(final ListElement item){
            iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            name.setText(item.getNombre());
            city.setText(item.getCity());
            status.setText(item.getStatus());
        }




    }
}

