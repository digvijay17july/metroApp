package com.metroApp.service;

import com.metroApp.config.Configurations;
import com.metroApp.model.Journey;
import com.metroApp.model.Metro;
import com.metroApp.model.SmartCard;
import com.metroApp.repository.JourneyRepository;
import com.metroApp.repository.SmartCardRepository;
import com.metroApp.utils.MetroUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SmartCardServiceImpl implements SmartCardService {

    @Autowired
    private SmartCardRepository smartCardRepository;

    @Autowired
    private JourneyRepository journeyRepository;
    @Autowired
    private MetroUtility metroUtility;

    @Autowired
    Configurations configurations;

    @Autowired
    Metro metro;

    @Override
    public void deductBalance(SmartCard smartCard, Journey journey) {


        AtomicInteger totalFare = new AtomicInteger(0);
        journey.setId(metroUtility.getRandomId());
        journeyRepository.save(journey);
        getTotalFare(journey, totalFare);
        smartCard.setBalance(smartCard.getBalance() - totalFare.get());
        smartCard.setCurrentJourney(journey);
        smartCard.getTravelHistory().add(journey);
        smartCardRepository.save(smartCard);
    }

    @Override
    public boolean isBalanceSufficient(SmartCard smartCard) {

        if (smartCard.getBalance() <= configurations.getMinBalance()) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public boolean isBalanceSufficientForJourney(SmartCard smartCard, Journey journey) {
        if (!isBalanceSufficient(smartCard)) {
            return false;
        } else {
            AtomicInteger totalFare = new AtomicInteger(0);
            getTotalFare(journey, totalFare);
            if ((smartCard.getBalance() - totalFare.get()) <= 0) {
                return false;
            } else
                return true;
        }

    }

    private void getTotalFare(Journey journey, AtomicInteger totalFare) {
        AtomicBoolean found = new AtomicBoolean(false);
        metro.getStations().forEach(station -> {
            int newValue = found.get() ? totalFare.get() + configurations.getFareIncrementStrategy() : totalFare.get();
            totalFare.set(newValue);
            if (station.getStation().equals(journey.getStartStation())) {
                found.set(true);
            }
            if (found.get() && station.getStation().equals(journey.getEndStation())) {
                found.set(false);
            }


        });
    }

    @Override
    public void addBalance(SmartCard smartCard, int money) {

    }

    @Override
    public SmartCard createSmartCard(SmartCard smartCard) throws Exception {
        if (smartCard.getCustomerName() != null && smartCard.getCustomerAddress() != null) {
            smartCard.setId(metroUtility.getRandomId());
            smartCard.setBalance(configurations.getDefaultBalance());
            smartCard.setTravelHistory(new ArrayList<>());
            smartCard = smartCardRepository.save(smartCard);
            return smartCard;
        } else {
            throw new Exception("Please Enter valid customer name por address");
        }
    }

    @Override
    public SmartCard getSmartCard(UUID id) {
        Optional<SmartCard> smartCard = smartCardRepository.findById(id);
        return smartCard.get();
    }
}
