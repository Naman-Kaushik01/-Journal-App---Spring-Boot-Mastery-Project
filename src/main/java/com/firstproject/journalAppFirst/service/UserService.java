package com.firstproject.journalAppFirst.service;

import com.firstproject.journalAppFirst.entity.JournalEntry;
import com.firstproject.journalAppFirst.entity.User;
import com.firstproject.journalAppFirst.repository.JournalEntryRepo;
import com.firstproject.journalAppFirst.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public void SaveEntry(User user){

        userRepo.save(user);
    }
    public List<User> getAll(){
        return  userRepo.findAll();
    }
    public Optional<User> findById(ObjectId Id){

        return  userRepo.findById(Id);
    }
    public void deleteById(ObjectId Id){

        userRepo.deleteById(Id);
    }
    public User FindByUserName(String username){
        return userRepo.findByUserName(username);
    }



}
