package com.example.favoriteshowcharacterlist.card;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.data.Character;
import com.example.data.CharacterNation;
import com.example.favoriteshowcharacterlist.R;

public class CharacterCardUtils {

    public static void fillCard(CardType cardType, TextView characterNameField, TextView characterDescField, ImageView characterImageField,
                          ImageView characterNationImage, Button characterLearnMoreButton, LinearLayout cardLayout, Character character) {

        characterNameField.setText(character.getCharacterName());
        characterImageField.setImageResource(character.getCharacterImage());

        switch (cardType)
        {
            case POPUP_CARD:
                characterDescField.setText(character.getCharacterLongerDesc());
                cardLayout.setBackgroundResource(getWindowBackgroundResource(character.getCharacterNation()));
                characterNationImage.setImageResource(getNationImageResource(character.getCharacterNation()));
                break;
            case SEARCH_CARD:
                characterDescField.setText(character.getCharacterDesc());
                cardLayout.setBackgroundResource(getCardBackgroundResource(character.getCharacterNation()));
                characterLearnMoreButton.setBackgroundResource(getWindowBackgroundResource(character.getCharacterNation()));
                characterLearnMoreButton.setText(String.format("more about %s...", character.getCharacterName()));
                break;
        }
    }

    public static Dialog initiatePopupCard(int cardWidth , int cardHeight , SearchView characterSearchBar , Context context)
    {
        Dialog popupCharacterWindow = new Dialog(context);
        popupCharacterWindow.setContentView(R.layout.character_popup);
        popupCharacterWindow.setCancelable(true);

        popupCharacterWindow.getWindow().setLayout(cardWidth , cardHeight);

        popupCharacterWindow.findViewById(R.id.popupButtonClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                characterSearchBar.clearFocus();
                popupCharacterWindow.dismiss();
            }
        });

        return popupCharacterWindow;
    }
    public static void openPopupCard(Dialog popupCharacterWindow , Character character)
    {
        TextView popupCharacterName = popupCharacterWindow.findViewById(R.id.popupCharacterName);
        TextView popupCharacterDesc = popupCharacterWindow.findViewById(R.id.popupCharacterDesc);
        ImageView popupCharacterImage = popupCharacterWindow.findViewById(R.id.popupCharacterImage);
        ImageView popupCharacterNation = popupCharacterWindow.findViewById(R.id.popupCharacterNation);
        LinearLayout cardLayout = popupCharacterWindow.findViewById(R.id.innerCardLayout);
        ScrollView scrollViewCharacterDesc = popupCharacterWindow.findViewById(R.id.scrollViewCharacterDesc);

        CharacterCardUtils.fillCard(CardType.POPUP_CARD , popupCharacterName , popupCharacterDesc , popupCharacterImage ,
                popupCharacterNation , null , cardLayout , character);

        scrollViewCharacterDesc.scrollTo(0 , 0);

        popupCharacterWindow.show();
    }


    private static int getWindowBackgroundResource(CharacterNation nation)
    {
        switch (nation) {
            case AIR:
                return R.drawable.window_design_air;
            case WATER:
                return R.drawable.window_design_water;
            case EARTH:
                return R.drawable.window_design_earth;
            case FIRE:
                return R.drawable.window_design_fire;
            default:
                return 0;
        }
    }

    private static int getCardBackgroundResource(CharacterNation nation) {
        switch (nation) {
            case AIR:
                return R.drawable.card_design_air;
            case WATER:
                return R.drawable.card_design_water;
            case EARTH:
                return R.drawable.card_design_earth;
            case FIRE:
                return R.drawable.card_design_fire;
            default:
                return 0;
        }
    }

    private static int getNationImageResource(CharacterNation nation) {
        switch (nation) {
            case AIR:
                return R.drawable.image_nation_air;
            case WATER:
                return R.drawable.image_nation_water;
            case EARTH:
                return R.drawable.image_nation_earth;
            case FIRE:
                return R.drawable.image_nation_fire;
            default:
                return 0;
        }
    }
}
