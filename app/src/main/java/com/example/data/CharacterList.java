package com.example.data;


import java.util.List;
import java.util.stream.Collectors;

public final class CharacterList {
    private final List<Character> characterList;
    public CharacterList(List<Character> characterList){
        this.characterList = characterList;
    }

    public List<Character> getAllCharacters() {return this.characterList;}

    public List<Character> getCharactersFromNation(CharacterNation nation){
        return characterList.stream()
                .filter(character -> character.getCharacterNation() == nation)
                .collect(Collectors.toList());
    }

    public List<Character> getCharactersByCharacterName(String characterName){
        return characterList.stream()
                .filter(character -> character.getCharacterName().toLowerCase().contains(characterName.toLowerCase()))
                .collect(Collectors.toList());
    }

}
