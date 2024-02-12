package com.example.favoriteshowcharacterlist;


import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.data.Character;
import com.example.favoriteshowcharacterlist.card.CardType;
import com.example.favoriteshowcharacterlist.card.CharacterCardUtils;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private List<Character> characterList;
    private final Dialog popupCharacterWindow;


    public void updateCustomAdapterData(List<Character> updatedCharacterList)
    {
        this.characterList = updatedCharacterList;
        notifyDataSetChanged();
    }

    public CustomAdapter(List<Character> characterList , Dialog popupCharacterWindow){
        this.characterList = characterList;
        this.popupCharacterWindow = popupCharacterWindow;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        private final TextView characterNameField;
        private final TextView characterDescField;
        private final ImageView characterImageField;
        private final LinearLayout innerCardLayout;
        private final Button characterLearnMoreField;

        public MyViewHolder(View view){
            super(view);

            this.characterNameField = view.findViewById(R.id.characterName);
            this.characterDescField = view.findViewById(R.id.characterDesc);
            this.characterImageField = view.findViewById(R.id.characterImage);
            this.innerCardLayout = view.findViewById(R.id.innerCardLayout);
            this.characterLearnMoreField = view.findViewById(R.id.characterLearnMoreBtn);
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

        CharacterCardUtils.fillCard(CardType.SEARCH_CARD , holder.characterNameField , holder.characterDescField ,
                 holder.characterImageField , null , holder.characterLearnMoreField ,
                 holder.innerCardLayout , characterList.get(holder.getAdapterPosition()));


        holder.characterLearnMoreField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharacterCardUtils.openPopupCard(popupCharacterWindow , characterList.get(holder.getAdapterPosition()));
            }
        });



    }

    @Override
    public int getItemCount() {
        return this.characterList.size();
    }


}
