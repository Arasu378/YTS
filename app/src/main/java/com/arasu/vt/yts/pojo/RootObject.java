package com.arasu.vt.yts.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kyros on 03-10-2017.
 */
public class RootObject{
    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }
    @SerializedName("status_message")
    @Expose
    private String status_message;

    public String getStatusMessage() { return this.status_message; }

    public void setStatusMessage(String status_message) { this.status_message = status_message; }
    @SerializedName("data")
    @Expose
    private Data data;

    public Data getData() { return this.data; }

    public void setData(Data data) { this.data = data; }
    @SerializedName("meta")
    @Expose
    private Meta meta;

    public Meta getMeta() { return this.meta; }

    public void setMeta(Meta meta) { this.meta = meta; }
}
