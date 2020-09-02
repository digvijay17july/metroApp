package com.metroApp.utils;


import com.metroApp.config.Configurations;
import com.metroApp.constants.StationName;
import com.metroApp.model.Station;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class MetroUtility {

    private Configurations configurations;

    MetroUtility(Configurations configurations){
        this.configurations=configurations;
    }

    public UUID getRandomId()
    {
        return UUID.randomUUID();
    }

    public Deque<Station> getStations(){
        Deque<Station> stations=new ArrayDeque<>();
        AtomicInteger atomicInteger=new AtomicInteger(0);
        Arrays.asList(StationName.values()).forEach((name)->{
            stations.add(new Station(name,atomicInteger.get()));
            atomicInteger.set(atomicInteger.get()+configurations.getFareIncrementStrategy());
        });
        return stations;
    }

    public boolean checkStationName(Deque<Station> stations,StationName stationName){
        AtomicReference<Boolean> found= new AtomicReference<>(false);
        stations.forEach(station -> {
            if(station.getStation().equals(stationName)){
                found.set(true);
            }
        });
        return  found.get();
    }
}
