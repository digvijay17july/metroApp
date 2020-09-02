package com.metroApp.controller;

import com.metroApp.model.Journey;
import com.metroApp.model.SmartCard;
import com.metroApp.service.MetroService;
import com.metroApp.service.SmartCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/metro")
public class MetroControllerImpl implements MetroController {

    @Autowired
    MetroService metroService;

    @Autowired
    SmartCardService smartCardService;

    @Override
    public ResponseEntity<Object> validEntry(Journey journey) {
        try {
            boolean result = metroService.validEntry(journey);
            return ResponseEntity.ok(result);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> travel(Journey journey) {
        SmartCard smartCard = null;
        try {
           if(metroService.validEntry(journey))
           {
            smartCard=smartCardService.getSmartCard(journey.getSmartCardId());
            smartCardService.deductBalance(smartCard,journey);
           }
            return ResponseEntity.ok(smartCard);
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

}
