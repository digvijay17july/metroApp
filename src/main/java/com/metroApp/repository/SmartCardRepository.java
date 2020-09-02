package com.metroApp.repository;

import com.metroApp.model.SmartCard;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface SmartCardRepository extends MongoRepository<SmartCard, UUID> {
}
