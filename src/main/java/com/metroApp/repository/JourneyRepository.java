package com.metroApp.repository;

import com.metroApp.model.Journey;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;


public interface JourneyRepository extends MongoRepository<Journey, UUID> {
}
