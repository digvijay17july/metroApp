package com.metroApp.service;

import com.metroApp.constants.StationName;
import com.metroApp.model.Journey;
import com.metroApp.model.Metro;
import com.metroApp.model.SmartCard;
import com.metroApp.model.Station;
import com.metroApp.utils.MetroUtility;
import org.springframework.stereotype.Service;

import java.util.Deque;

@Service
public class MetroServiceImpl implements MetroService {


    private SmartCardService smartCardService;
    private Metro metro;
    private MetroUtility metroUtility;

    public MetroServiceImpl(SmartCardService smartCardService, Metro metro,MetroUtility metroUtility) {
        this.metroUtility=metroUtility;
        this.smartCardService = smartCardService;
        this.metro = metro;
    }

    @Override
    public StationName getCurrentStation() throws Exception {
        return metro.getCurrentStation();
    }

    @Override
    public Deque<Station> getStations() throws Exception {
        return metro.getStations();
    }

    @Override
    public boolean validEntry(Journey journey) throws Exception {
        if (isStartEndValid(journey) && isBalanceSufficient(journey))
            return true;
        else return false;
    }

    private boolean isStartEndValid(Journey journey) {
        if (metroUtility.checkStationName(metro.getStations(),journey.getStartStation()) && metroUtility.checkStationName(metro.getStations(),journey.getEndStation())) {
            return true;
        } else
            return false;
    }

    private boolean isBalanceSufficient(Journey journey) {
        SmartCard smartCard = smartCardService.getSmartCard(journey.getSmartCardId());
        if (smartCardService.isBalanceSufficientForJourney(smartCard,journey)) {
            return true;
        } else
            return false;
    }
}
