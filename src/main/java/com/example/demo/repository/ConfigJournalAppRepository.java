package com.example.demo.repository;

import com.example.demo.entity.ConfigJournalAppEntity;
import com.example.demo.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId>{


}
