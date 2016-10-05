package com.nemanjapetrovic.streaming.radio.player.Commands;

/**
 * Created by nemanja on 10/3/16.
 */
public class Args {
    //Simple data
    public String command;
    public String name;
    public String url;

    public Args(String command, String name, String url) {
        this.command = command;
        this.name = name;
        this.url = url;
    }

    //Commands
    public enum CommandEnums {
        help, add, remove, list, play
    }
}