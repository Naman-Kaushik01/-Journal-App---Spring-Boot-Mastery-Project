package com.firstproject.journalAppFirst.repository;

import com.firstproject.journalAppFirst.entity.JournalEntry;
import com.firstproject.journalAppFirst.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository <User, ObjectId>{

    User findByUserName(String userName);


}
