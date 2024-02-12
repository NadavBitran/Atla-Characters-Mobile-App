package com.example.data;

public final class Character {
    private final String characterName;
    private final String characterDesc;
    private final String characterLongerDesc;
    private final CharacterNation characterNation;
    private final int characterImage;

    public Character(String characterName , String characterDesc , String characterLongerDesc  , int characterImage, CharacterNation characterNation){
        this.characterName = characterName;
        this.characterDesc = characterDesc;
        this.characterLongerDesc = characterLongerDesc;
        this.characterImage = characterImage;
        this.characterNation = characterNation;
    }

    public String getCharacterName() {
        return characterName;
    }
    public String getCharacterDesc() {
        return characterDesc;
    }
    public String getCharacterLongerDesc() {return characterLongerDesc;}
    public int getCharacterImage() {
        return characterImage;
    }
    public CharacterNation getCharacterNation() {
        return characterNation;
    }

}
