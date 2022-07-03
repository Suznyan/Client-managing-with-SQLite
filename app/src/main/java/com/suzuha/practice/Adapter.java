package com.suzuha.practice;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private Context context;
    private ArrayList<Client> mAL;

    public Adapter(Context context, ArrayList<Client> clients) {
        this.context = context;
        mAL = clients;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.profile.setImageURI(Uri.parse(mAL.get(position).getUrl()));
        holder.Name.setText(mAL.get(position).getName());
        holder.Email.setText(mAL.get(position).getEmail());
        holder.Gender.setText(mAL.get(position).getGender());
        holder.Nationality.setText(mAL.get(position).getNationality());
    }

    @Override
    public int getItemCount() {
        return mAL.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Name, Email, Gender, Nationality;
        ImageView profile;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.clientName);
            Email = itemView.findViewById(R.id.clientEmail);
            Gender = itemView.findViewById(R.id.clientGender);
            Nationality = itemView.findViewById(R.id.clientNationality);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            profile = itemView.findViewById(R.id.clientProfile);
        }
    }
}
