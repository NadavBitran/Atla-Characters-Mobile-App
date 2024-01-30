package com.example.favoriteshowcharacterlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private CharacterList characters;
    private RecyclerView characterListRecyclerView;
    private TextView displayResults;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.characters_page);


        ArrayList<Character> characterList = new ArrayList<>();
        characterListRecyclerView = findViewById(R.id.recyclerView);
        displayResults = findViewById(R.id.displayResults);

        for(int i=0; i<FavoriteShowCharactersData.charactersName.length;i++){
            characterList.add(new Character(FavoriteShowCharactersData.charactersName[i],
                                            FavoriteShowCharactersData.charactersDesc[i],
                                            FavoriteShowCharactersData.charactersImage[i],
                                            FavoriteShowCharactersData.charactersNation[i]));
        }
        characters = new CharacterList(characterList);

        showAllCharacters(null);

        setTagsForFilterByNationButtons();

    }

    public void filterCharacters(View view){
        displayCharacterList(characters.getCharactersFromNation((CharacterNation)view.getTag()));
    }

    public void showAllCharacters(View view){
        displayCharacterList(characters.getAllCharacters());
    }

    private void displayCharacterList(ArrayList<Character> characterList){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        CustomAdapter customAdapter = new CustomAdapter(characterList);

        characterListRecyclerView.setLayoutManager(layoutManager);
        characterListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        characterListRecyclerView.setAdapter(customAdapter);

        displayResults.setText(createDisplayResultsLabel(characterList.size()));
    }

    private void setTagsForFilterByNationButtons(){
        ImageButton airNationFilterButton;
        ImageButton waterNationFilterButton;
        ImageButton earthNationFilterButton;
        ImageButton fireNationFilterButton;

        airNationFilterButton = findViewById(R.id.airNationCharactersButton);
        waterNationFilterButton = findViewById(R.id.waterNationCharactersButton);
        earthNationFilterButton = findViewById(R.id.earthNationCharactersButton);
        fireNationFilterButton = findViewById(R.id.fireNationCharactersButton);

        airNationFilterButton.setTag(CharacterNation.AIR);
        waterNationFilterButton.setTag(CharacterNation.WATER);
        earthNationFilterButton.setTag(CharacterNation.EARTH);
        fireNationFilterButton.setTag(CharacterNation.FIRE);
    }

    @SuppressLint("DefaultLocale")
    private String createDisplayResultsLabel(int resultsCount){
        final String displayResultsFormat = "%d results";

        return String.format(displayResultsFormat , resultsCount);
    }


}