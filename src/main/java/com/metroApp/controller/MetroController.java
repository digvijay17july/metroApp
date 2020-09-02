package com.metroApp.controller;

import com.metroApp.model.Journey;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/metro")
public interface MetroController {

    @PostMapping("/travel/validate")
    ResponseEntity<Object> validEntry(@RequestBody Journey journey);

    @PostMapping("/travel")
    ResponseEntity<Object> travel(@RequestBody Journey journey);
}
