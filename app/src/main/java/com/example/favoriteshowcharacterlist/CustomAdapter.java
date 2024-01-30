package com.example.favoriteshowcharacterlist;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    ArrayList<Character> characterList;

    public CustomAdapter(ArrayList<Character> characterList){
        this.characterList = characterList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView characterNameField;
        private final TextView characterDescField;
        private final ImageView characterImageField;
        private final LinearLayout innerCardLayout;

        public MyViewHolder(View view){
            super(view);

            this.characterNameField = view.findViewById(R.id.characterName);
            this.characterDescField = view.findViewById(R.id.characterDesc);
            this.characterImageField = view.findViewById(R.id.characterImage);
            this.innerCardLayout = view.findViewById(R.id.innerCardLayout);
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.character_card , parent , false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.characterNameField.setText(characterList.get(position).getCharacterName());
        holder.characterDescField.setText(characterList.get(position).getCharacterDesc());
        holder.characterImageField.setImageResource(characterList.get(position).getCharacterImage());



        switch(characterList.get(position).getCharacterNation()){

            case AIR:
                holder.innerCardLayout.setBackgroundResource(R.drawable.card_design_air);
                break;

            case WATER:
                holder.innerCardLayout.setBackgroundResource(R.drawable.card_design_water);
                break;

            case EARTH:
                holder.innerCardLayout.setBackgroundResource(R.drawable.card_design_earth);
                break;

            case FIRE:
                holder.innerCardLayout.setBackgroundResource(R.drawable.card_design_fire);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return this.characterList.size();
    }


}
