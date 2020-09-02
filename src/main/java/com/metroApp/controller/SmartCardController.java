package com.metroApp.controller;

import com.metroApp.model.SmartCard;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/smartCard")
public interface SmartCardController {

    @PostMapping("/")
    ResponseEntity<Object> createSmartCard(@RequestBody SmartCard smartCard);

    @GetMapping("/{id}")
    ResponseEntity<Object> getSmartCard(@PathVariable("id") UUID id);
}
