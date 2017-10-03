package com.arasu.vt.yts.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by kyros on 03-10-2017.
 */

public class Torrent {
    @SerializedName("url")
    @Expose
    private String url;

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }
    @SerializedName("hash")
    @Expose
    private String hash;

    public String getHash() { return this.hash; }

    public void setHash(String hash) { this.hash = hash; }
    @SerializedName("quality")
    @Expose
    private String quality;

    public String getQuality() { return this.quality; }

    public void setQuality(String quality) { this.quality = quality; }
    @SerializedName("seeds")
    @Expose
    private double seeds;

    public double getSeeds() { return this.seeds; }

    public void setSeeds(double seeds) { this.seeds = seeds; }
    @SerializedName("peers")
    @Expose
    private double peers;

    public double getPeers() { return this.peers; }

    public void setPeers(double peers) { this.peers = peers; }
    @SerializedName("size")
    @Expose
    private String size;

    public String getSize() { return this.size; }

    public void setSize(String size) { this.size = size; }
    @SerializedName("size_bytes")
    @Expose
    private double size_bytes;

    public double getSizeBytes() { return this.size_bytes; }

    public void setSizeBytes(double size_bytes) { this.size_bytes = size_bytes; }
    @SerializedName("date_uploaded")
    @Expose
    private String date_uploaded;

    public String getDateUploaded() { return this.date_uploaded; }

    public void setDateUploaded(String date_uploaded) { this.date_uploaded = date_uploaded; }
    @SerializedName("date_uploaded_unix")
    @Expose
    private double date_uploaded_unix;

    public double getDateUploadedUnix() { return this.date_uploaded_unix; }

    public void setDateUploadedUnix(double date_uploaded_unix) { this.date_uploaded_unix = date_uploaded_unix; }
}
