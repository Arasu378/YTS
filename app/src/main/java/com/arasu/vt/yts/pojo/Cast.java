
package com.arasu.vt.yts.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cast {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("character_name")
    @Expose
    private String characterName;
    @SerializedName("imdb_code")
    @Expose
    private String imdbCode;
    @SerializedName("url_small_image")
    @Expose
    private String urlSmallImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public String getImdbCode() {
        return imdbCode;
    }

    public void setImdbCode(String imdbCode) {
        this.imdbCode = imdbCode;
    }

    public String getUrlSmallImage() {
        return urlSmallImage;
    }

    public void setUrlSmallImage(String urlSmallImage) {
        this.urlSmallImage = urlSmallImage;
    }

}
