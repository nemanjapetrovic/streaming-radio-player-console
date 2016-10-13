package com.nemanjapetrovic.streaming.radio.player.Commands;

import com.nemanjapetrovic.streaming.radio.player.Data.Station;
import com.nemanjapetrovic.streaming.radio.player.Data.DataController;
import com.sun.jna.NativeLibrary;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import uk.co.caprica.vlcj.component.AudioMediaListPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by nemanja on 10/1/16.
 */
public class Command implements ICommand {

    private static final String NATIVE_LIBRARY_SEARCH_PATH = "/home/vlc";

    public Command() {
        DataController.getInstance().LoadStations();
    }

    public void Help() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("\n\n\nStreaming radio player commands: \n");
        buffer.append("\tadd [name] [url] ; add a station to a list of stations \n");
        buffer.append("\tremove [name] ; remove a station from a list of stations \n");
        buffer.append("\tlist ; show all saved radio stations \n");
        buffer.append("\tplay [name] ; play a radio station \n");
        buffer.append("\n*** Created by Nemanja Petrovic github: @nemanjapetrovic ***\n\n\n");

        System.out.println(buffer.toString());
    }

    public void Add(String name, String url) {
        DataController.getInstance().AddStation(new Station(name, url));
    }

    public void Remove(String name) {
        boolean ret = DataController.getInstance().RemoveStation(new Station(name, ""));
        if (ret) {
            System.out.println("Station successfully removed!");
        } else {
            System.out.println("Station don't exist!");
        }
    }

    public void List() {
        DataController.getInstance().print();
    }

    public void Play(String name) {
        try {
            //Get the station
            final Station station = DataController.getInstance().getStation(name);
            if (station == null) {
                System.out.println("This station does not exist!");
                return;
            }

            //Get library
            NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), NATIVE_LIBRARY_SEARCH_PATH);

            //Create media and audio
            AudioMediaListPlayerComponent factoryMy = new AudioMediaListPlayerComponent();
            MediaPlayer mediaPlayer = factoryMy.getMediaPlayer();

            //Play
            mediaPlayer.playMedia(station.getUrl());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}
