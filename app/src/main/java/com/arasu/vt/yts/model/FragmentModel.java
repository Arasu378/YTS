package com.arasu.vt.yts.model;

/**
 * Created by kyros on 05-10-2017.
 */

public class FragmentModel {
    private String runtime;
    private String language;
    private String mpa_rating;
    private String p720;
    private String p1080;
    private String quality;
    private int peers;
    private int seeds;
    private String size;
    private static final FragmentModel holder=new FragmentModel();
    private String torrentZlist;
    private String largePictureList;
    public static FragmentModel getHolder(){
        return holder;
    }
    public FragmentModel(){

    }

    public String getTorrentZlist() {
        return torrentZlist;
    }

    public void setTorrentZlist(String torrentZlist) {
        this.torrentZlist = torrentZlist;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMpa_rating() {
        return mpa_rating;
    }

    public void setMpa_rating(String mpa_rating) {
        this.mpa_rating = mpa_rating;
    }

    public String getP720() {
        return p720;
    }

    public void setP720(String p720) {
        this.p720 = p720;
    }

    public String getP1080() {
        return p1080;
    }

    public void setP1080(String p1080) {
        this.p1080 = p1080;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getPeers() {
        return peers;
    }

    public void setPeers(int peers) {
        this.peers = peers;
    }

    public int getSeeds() {
        return seeds;
    }

    public void setSeeds(int seeds) {
        this.seeds = seeds;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getLargePictureList() {
        return largePictureList;
    }

    public void setLargePictureList(String largePictureList) {
        this.largePictureList = largePictureList;
    }
}
