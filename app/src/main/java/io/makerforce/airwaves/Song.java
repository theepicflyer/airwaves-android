package io.makerforce.airwaves;

/**
 * Created by jiacheng on 10/15/17.
 */

public class Song {
    private String name;
    private String artist;
    private String album;
    private String hash;
    private String time;

    public Song(String name, String artist, String album, String hash, String time) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.hash = hash;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
