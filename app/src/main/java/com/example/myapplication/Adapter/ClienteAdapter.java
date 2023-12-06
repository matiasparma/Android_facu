package com.example.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ListElement;
import com.example.myapplication.R;
import com.example.myapplication.modelo.Cliente;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolder> {

    private List<Cliente> mData;
    private LayoutInflater mInflater;
    private Context mContext;
    private OnItemClickListener mListener;



    public interface OnItemClickListener {
        void onItemClick(Cliente item);
    }

    public ClienteAdapter(List<Cliente> itemList, Context context, OnItemClickListener listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mData = itemList;
        this.mListener = listener;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_cliente, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.cv.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_transition));
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Cliente> items) {
        mData = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView razonSocialTextView, tipoUsuarioTextView, id;

        CardView cv;

        ViewHolder(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.personIdTextView);
            razonSocialTextView = itemView.findViewById(R.id.personNameTextView);
            tipoUsuarioTextView = itemView.findViewById(R.id.personDescriptionTextView);
            cv = itemView.findViewById(R.id.cv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        mListener.onItemClick(mData.get(getAdapterPosition()));
                    }
                }
            });
        }

        void bindData(final Cliente item) {
            id.setText(item.getId());
            razonSocialTextView.setText(item.getRazonSocial());
            tipoUsuarioTextView.setText(item.getTipoUsuario());
        }

    }
    public void setFilterList(List<Cliente> filteredList){
        this.mData=filteredList;
        notifyDataSetChanged();
    }
}
