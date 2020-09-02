package com.metroApp.service;

import com.metroApp.model.Journey;
import com.metroApp.model.SmartCard;
import com.metroApp.model.Station;

import java.util.UUID;

public interface SmartCardService {

     void deductBalance(SmartCard smartCard,Journey journey);
     boolean isBalanceSufficient(SmartCard smartCard);
     boolean isBalanceSufficientForJourney(SmartCard smartCard, Journey journey);
     void addBalance(SmartCard smartCard,int money);
     SmartCard createSmartCard(SmartCard smartCard) throws Exception;
     SmartCard getSmartCard(UUID id);
}
