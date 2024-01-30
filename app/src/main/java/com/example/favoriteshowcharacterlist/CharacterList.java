package com.example.favoriteshowcharacterlist;


import java.util.ArrayList;

public final class CharacterList {
    private final ArrayList<Character> characterList;
    public CharacterList(ArrayList<Character> characterList){
        this.characterList = characterList;
    }

    public ArrayList<Character> getAllCharacters() {return this.characterList;}

    public ArrayList<Character> getCharactersFromNation(CharacterNation nation){
        ArrayList<Character> charactersFromNation =  new ArrayList<>();

        System.out.println(characterList.toString());

        for(Character character : characterList){
            if(character.getCharacterNation() == nation){
                charactersFromNation.add(character);
            }
        }

        return charactersFromNation;
    }

}
