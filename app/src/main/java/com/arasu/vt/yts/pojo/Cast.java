package com.arasu.vt.yts.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kyros on 04-10-2017.
 */

public class Cast{
    @SerializedName("name")
    @Expose
    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }
    @SerializedName("character_name")
    @Expose
    private String character_name;

    public String getCharacterName() { return this.character_name; }

    public void setCharacterName(String character_name) { this.character_name = character_name; }
    @SerializedName("url_small_image")
    @Expose
    private String url_small_image;

    public String getUrlSmallImage() { return this.url_small_image; }

    public void setUrlSmallImage(String url_small_image) { this.url_small_image = url_small_image; }
    @SerializedName("imdb_code")
    @Expose
    private String imdb_code;

    public String getImdbCode() { return this.imdb_code; }

    public void setImdbCode(String imdb_code) { this.imdb_code = imdb_code; }
}

