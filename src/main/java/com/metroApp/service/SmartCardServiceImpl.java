package com.metroApp.service;

import com.metroApp.config.Configurations;
import com.metroApp.model.Journey;
import com.metroApp.model.Metro;
import com.metroApp.model.SmartCard;
import com.metroApp.model.Station;
import com.metroApp.repository.SmartCardRepository;
import com.metroApp.utils.MetroUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SmartCardServiceImpl implements SmartCardService {

    @Autowired
    private SmartCardRepository smartCardRepository;

    @Autowired
    private MetroUtility metroUtility;

    @Autowired
    Configurations configurations;

    @Autowired
    Metro metro;

    @Override
    public void deductBalance(SmartCard smartCard, Journey journey) {

        if(isBalanceSufficientForJourney(smartCard,journey)) {
            AtomicInteger totalFare = new AtomicInteger(0);
            getTotalFare(journey, totalFare);
            smartCard.setBalance(smartCard.getBalance() - totalFare.get());
        }
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
            if ((smartCard.getBalance()-totalFare.get()) <= 0) {
                return false;
            } else
                return true;
        }

    }

    private void getTotalFare(Journey journey, AtomicInteger totalFare) {
        metro.getStations().forEach(station -> {
            boolean found = false;
            if (station.getStation().equals(journey.getStartStation())) {
                found = true;
            }
            int newValue = found ? totalFare.get() + 1 : totalFare.get();
            totalFare.set(newValue);
            if (found && station.getStation().equals(journey.getEndStation())) {
                found = false;
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
