package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PersonasAdministradorasAdapter extends RecyclerView.Adapter<PersonasAdministradorasAdapter.PersonViewHolder> {
    private List<PersonasAdministradoras> personList;

    public PersonasAdministradorasAdapter(List<PersonasAdministradoras> personList) {
        this.personList = personList;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_personas_administradoras, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        PersonasAdministradoras person = personList.get(position);
        holder.personImageView.setImageResource(person.getImageResId());
        holder.personNameTextView.setText(person.getName());
        holder.personDescriptionTextView.setText(person.getDescription());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {
        ImageView personImageView;
        TextView personNameTextView;
        TextView personDescriptionTextView;

        PersonViewHolder(View itemView) {
            super(itemView);
            personImageView = itemView.findViewById(R.id.personImageView);
            personNameTextView = itemView.findViewById(R.id.personNameTextView);
            personDescriptionTextView = itemView.findViewById(R.id.personDescriptionTextView);
        }
    }
}
