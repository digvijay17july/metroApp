package com.metroApp.model;

import com.metroApp.constants.StationName;
import org.springframework.stereotype.Component;

import java.util.Deque;



public class Metro {

    private Deque<Station> stations;
    private StationName currentStation;

    public Deque<Station> getStations() {
        return stations;
    }

    public void setStations(Deque<Station> stations) {
        this.stations = stations;
    }

    public void setCurrentStation(StationName currentStation) {
        this.currentStation = currentStation;
    }

    public StationName getCurrentStation() {
        return currentStation;
    }

}
