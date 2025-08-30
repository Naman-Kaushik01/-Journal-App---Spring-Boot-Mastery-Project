package com.firstproject.journalAppFirst.repository;

import com.firstproject.journalAppFirst.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepo extends MongoRepository <JournalEntry, ObjectId>{


}
