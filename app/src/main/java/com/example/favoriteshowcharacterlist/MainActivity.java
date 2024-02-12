package com.example.favoriteshowcharacterlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.data.Character;
import com.example.data.CharacterList;
import com.example.data.CharacterNation;
import com.example.data.FavoriteShowCharactersData;
import com.example.favoriteshowcharacterlist.card.CharacterCardUtils;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private CharacterList characters;
    private RecyclerView characterListRecyclerView;
    private TextView displayResults;
    private CustomAdapter customAdapter;
    private SearchView charactersSearchBar;
    private Dialog popupCharacterWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.characters_page);

        setCharactersSearchBar();

        initiateCharacterList();
        initiateDialog();
        initiateRecyclerList(characters.getAllCharacters());

        setTagsForFilterByNationButtons();

    }

    public void filterCharactersByCharacterName(String characterName) {
        final String notFoundMessage = "No Characters Have Found";

        List<Character> charactersResults = characters.getCharactersByCharacterName(characterName);

        updateRecyclerList(charactersResults);

        if(charactersResults.isEmpty())
        {
            setToastWithMessage(notFoundMessage);
        }
    }

    public void filterCharactersByNation(View view){
        resetCharactersSearchBarInput();
        updateRecyclerList(characters.getCharactersFromNation((CharacterNation)view.getTag()));
    }

    public void showAllCharacters(View view){
        resetCharactersSearchBarInput();
        updateRecyclerList(characters.getAllCharacters());
    }

    private void initiateCharacterList()
    {
        List<Character> characterList = new ArrayList<>();
        characterListRecyclerView = findViewById(R.id.recyclerView);
        displayResults = findViewById(R.id.displayResults);

        for(int i = 0; i< FavoriteShowCharactersData.charactersName.length; i++){
            characterList.add(new Character(FavoriteShowCharactersData.charactersName[i],
                    FavoriteShowCharactersData.charactersDesc[i],
                    FavoriteShowCharactersData.charactersLongerDesc[i],
                    FavoriteShowCharactersData.charactersImage[i],
                    FavoriteShowCharactersData.charactersNation[i]));
        }
        characters = new CharacterList(characterList);
    }

    private void initiateDialog()
    {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        // setting dialog size height to 60% of the phone's height size
        popupCharacterWindow = CharacterCardUtils.initiatePopupCard(ViewGroup.LayoutParams.MATCH_PARENT , (int)(size.y * 0.8f) ,
                                                               charactersSearchBar , MainActivity.this);
    }

    public void initiateRecyclerList(List<Character> characterList)
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        customAdapter = new CustomAdapter(characterList , popupCharacterWindow);

        characterListRecyclerView.setLayoutManager(layoutManager);
        characterListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        characterListRecyclerView.setAdapter(customAdapter);

        displayResults.setText(createDisplayResultsLabel(characterList.size()));
    }

    private void updateRecyclerList(List<Character> characterList)
    {
        final int POSITION_TOP = 0;

        customAdapter.updateCustomAdapterData(characterList);

        displayResults.setText(createDisplayResultsLabel(characterList.size()));

        characterListRecyclerView.scrollToPosition(POSITION_TOP);
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

    private void setCharactersSearchBar()
    {
        charactersSearchBar = findViewById(R.id.searchBar);

        charactersSearchBar.clearFocus();
        charactersSearchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filterCharactersByCharacterName(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterCharactersByCharacterName(newText);
                return true;
            }
        });
    }

    private void resetCharactersSearchBarInput()
    {
        final String EMPTY = "";
        charactersSearchBar.clearFocus();
        charactersSearchBar.setQuery(EMPTY , false);
    }

    private void setToastWithMessage(String toastMessage)
    {
        Toast.makeText(this , toastMessage , Toast.LENGTH_SHORT).show();
    }


    @SuppressLint("DefaultLocale")
    private String createDisplayResultsLabel(int resultsCount){
        final String displayResultsFormat = "%d results";

        return String.format(displayResultsFormat , resultsCount);
    }


}