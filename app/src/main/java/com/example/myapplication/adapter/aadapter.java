package com.example.myapplication.adapter;

import static java.lang.Math.abs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import java.util.ArrayList;

public class aadapter extends RecyclerView.Adapter<aadapter.myViewHolder> {
    ArrayList<String> city;
    Context context1;

//    public aadapter(ArrayList<String> arrayList, View.OnClickListener context){
//        this.city = arrayList;
//        this.context =context;
//    }
//    public aadapter(ArrayList<String> arrayList, Context context){
//        this.city = arrayList;
//        this.context1 =context;
//    }
public aadapter(ArrayList<String> arrayList,Context context){
        this.city = arrayList;
        this.context1 = context;

    }
    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {
       holder.history.setText(city.get(position));
//        notifyDataSetChanged();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context1,MainActivity.class);
                intent.putExtra("key",holder.history.getText().toString());
                context1.startActivity(intent);

            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                city.remove(city.get(position));
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return city.size();
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        TextView history;
        ImageButton img;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
//            int position = getAbsoluteAdapterPosition();
            history = itemView.findViewById(R.id.hist);
            img = itemView.findViewById(R.id.close);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context1, MainActivity.class);
//                    intent.putExtra("key",history.getText().toString());
//                    context1.startActivity(intent);
//
//                }
//            });
        }
    }
}
