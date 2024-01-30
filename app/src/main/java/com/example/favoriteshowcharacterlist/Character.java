package com.example.favoriteshowcharacterlist;

public final class Character {
    private final String characterName;
    private final String characterDesc;
    private final CharacterNation characterNation;
    private final int characterImage;

    public Character(String characterName , String characterDesc , int characterImage, CharacterNation characterNation){
        this.characterName = characterName;
        this.characterDesc = characterDesc;
        this.characterImage = characterImage;
        this.characterNation = characterNation;
    }

    public String getCharacterName() {
        return characterName;
    }
    public String getCharacterDesc() {
        return characterDesc;
    }
    public int getCharacterImage() {
        return characterImage;
    }
    public CharacterNation getCharacterNation() {
        return characterNation;
    }

}
