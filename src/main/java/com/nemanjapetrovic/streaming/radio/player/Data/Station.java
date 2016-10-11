package com.nemanjapetrovic.streaming.radio.player.Data;

/**
 * Created by nemanja on 10/1/16.
 */
public class Station {

    private String url;
    private String name;

    public Station(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }
}
