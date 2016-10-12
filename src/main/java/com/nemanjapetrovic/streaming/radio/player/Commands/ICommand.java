package com.nemanjapetrovic.streaming.radio.player.Commands;

/**
 * Created by nemanja on 10/2/16.
 */
public interface ICommand {

    //Help - prints all the help
    void Help();

    //Add a station
    void Add(String name, String url);

    //Remove a radio station
    void Remove(String name);

    //Prints all saved stations
    void List();

    //Play a radio station
    void Play(String name);

}
