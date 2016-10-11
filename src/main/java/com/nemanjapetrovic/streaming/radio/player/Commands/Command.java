package com.nemanjapetrovic.streaming.radio.player.Commands;

import com.nemanjapetrovic.streaming.radio.player.Data.Station;
import com.nemanjapetrovic.streaming.radio.player.Data.DataController;
import com.sun.jna.NativeLibrary;
import uk.co.caprica.vlcj.component.AudioMediaListPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

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
        buffer.append("\n\nStreaming radio player commands: \n");
        buffer.append("\tadd [name] [url] ; add a station to a list of stations \n");
        buffer.append("\tremove [name] ; remove a station from a list of stations \n");
        buffer.append("\tlist [name] [url] ; show all saved radio stations \n");
        buffer.append("\tplay [name] ; play a radio station \n");
        buffer.append("\tfav [name] [url] ; add a radio station to favorites \n");
        buffer.append("\tfav ; show all favorites radio stations \n");
        buffer.append("\n*** Created by Nemanja Petrovic github: @nemanjapetrovic ***\n");

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

            Thread thread = new Thread(new Runnable() {
                public void run() {
                    //Get library
                    NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), NATIVE_LIBRARY_SEARCH_PATH);

                    //Create media and audio
                    AudioMediaListPlayerComponent factoryMy = new AudioMediaListPlayerComponent();
                    MediaPlayer mediaPlayer = factoryMy.getMediaPlayer();

                    //Play
                    mediaPlayer.playMedia(station.getUrl());
                }
            });
            thread.start();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}
