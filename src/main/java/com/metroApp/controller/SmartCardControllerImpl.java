package com.metroApp.controller;

import com.metroApp.model.SmartCard;
import com.metroApp.service.SmartCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/smartCard")
public class SmartCardControllerImpl implements SmartCardController{

    @Autowired
    SmartCardService smartCardService;


    @Override
    public ResponseEntity<Object> createSmartCard(@RequestBody SmartCard smartCard) {
        try {
            smartCard = smartCardService.createSmartCard(smartCard);
            return ResponseEntity.ok(smartCard.getId());
        }catch (Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }
    }

    @Override
    public ResponseEntity<Object> getSmartCard(UUID id) {
        SmartCard smartCard=smartCardService.getSmartCard(id);
        return ResponseEntity.ok(smartCard);
    }
}
