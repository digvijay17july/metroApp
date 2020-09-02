package com.metroApp.model;

import com.metroApp.constants.StationName;

import java.util.List;
import java.util.UUID;


import com.sun.istack.internal.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("SmartCard")
public class SmartCard {

    @Id
    private UUID id;
    @NotNull
    private String customerName;
    private float customerAge;
    private String customerAddress;
    private int balance;
    private List<List<Station>> travelHistory;
    private Journey currentJourney;

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(@NotNull String customerName) {
        this.customerName = customerName;
    }

    public float getCustomerAge() {
        return customerAge;
    }

    public void setCustomerAge(float customerAge) {
        this.customerAge = customerAge;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Journey getCurrentJourney() {
        return currentJourney;
    }

    public void setCurrentJourney(Journey currentJourney) {
        this.currentJourney = currentJourney;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<List<Station>> getTravelHistory() {
        return travelHistory;
    }

    public void setTravelHistory(List<List<Station>> travelHistory) {
        this.travelHistory = travelHistory;
    }
}
