package com.nemanjapetrovic.streaming.radio.player.Data;

import java.io.*;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by nemanja on 10/1/16.
 */
public class DataController {

    //Singleton
    private final static DataController instance = new DataController();

    private DataController() {
    }

    public static DataController getInstance() {
        return instance;
    }

    //Data
    private ArrayList<Station> stations = new ArrayList<Station>();
    private final String _FILE_NAME = "stations.json";

    public void LoadStations() {
        try {
            //Read Data
            File file = new File(_FILE_NAME);
            if (file.exists()) {
                FileInputStream inputStream = new FileInputStream(file);
                byte[] buffer = new byte[(int) file.length()];
                inputStream.read(buffer);
                String data = new String(buffer, "UTF-8");

                //Fill stations with Data
                stations.clear();
                JSONArray json = new JSONArray(data);
                for (int i = 0; i < json.length(); i++) {
                    JSONObject obj = json.getJSONObject(i);
                    stations.add(new Station(obj.getString("name"), obj.getString("url")));
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {

        }
    }

    public void SaveStations() {
        try {
            //Write Data
            FileWriter fileWriter = new FileWriter(_FILE_NAME);
            JSONArray json = new JSONArray(stations);
            fileWriter.write(json.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {

        }
    }

    public void AddStation(Station station) {
        this.stations.add(station);
        this.SaveStations();
    }

    public boolean RemoveStation(Station station) {
        Station removing = null;
        for (Station item : stations) {
            if (item.getName().equals(station.getName())) {
                removing = item;
                break;
            }
        }
        if (removing != null) {
            this.stations.remove(removing);
            this.SaveStations();
            return true;
        }

        return false;
    }

    public void print() {
        for (Station item : stations) {
            System.out.println("Station: " + item.getName() + " ; " + item.getUrl());
        }
    }

    public Station getStation(String name) {
        for (Station item : stations) {
            if (item.getName().equals(name)) {
                {
                    return item;
                }
            }
        }
        return null;
    }
}
