package com.arasu.vt.yts.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kyros on 20-10-2017.
 */

public class ParentalGuides {
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("parental_guide_text")
    @Expose
    private String parental_guide_text;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParental_guide_text() {
        return parental_guide_text;
    }

    public void setParental_guide_text(String parental_guide_text) {
        this.parental_guide_text = parental_guide_text;
    }
}
