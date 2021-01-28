package com.example.tesstapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tesstapp.R;
import com.example.tesstapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<User> mUsers = new ArrayList<>();

//    public RecyclerAdapter(List<User> mUsers) {
//        this.mUsers = mUsers;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(mUsers.get(position).getName());
        holder.forname.setText(mUsers.get(position).getForname());
        holder.middlename.setText(mUsers.get(position).getMiddle_name());
        holder.phone_number.setText(mUsers.get(position).getPhone_number());
        holder.age.setText(String.valueOf(mUsers.get(position).getAge()));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView name, forname, middlename, phone_number, age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            forname= itemView.findViewById(R.id.forname);
            middlename= itemView.findViewById(R.id.middlename);
            phone_number= itemView.findViewById(R.id.phonenumber);
            age= itemView.findViewById(R.id.age);
        }
    }

    public void setUsers(List<User> users){
        this.mUsers = users;
        notifyDataSetChanged();
    }
}
