package com.metroApp.service;

import com.metroApp.constants.StationName;
import com.metroApp.model.Journey;
import com.metroApp.model.Station;

import java.util.Deque;

public interface MetroService {

    StationName getCurrentStation() throws Exception;

    Deque<Station> getStations() throws Exception;

    boolean validEntry(Journey journey) throws Exception;
}
