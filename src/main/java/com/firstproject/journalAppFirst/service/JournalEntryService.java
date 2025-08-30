package com.firstproject.journalAppFirst.service;

import com.firstproject.journalAppFirst.entity.JournalEntry;
import com.firstproject.journalAppFirst.entity.User;
import com.firstproject.journalAppFirst.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void SaveEntry(@org.jetbrains.annotations.NotNull JournalEntry journalEntry, String userName){
        try{
            User user=userService.FindByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved=journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(saved);
            userService.SaveEntry(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while saving the entry");
        }



    }
    public void SaveEntry(JournalEntry journalEntry){
        journalEntryRepo.save(journalEntry);
    }
    public List<JournalEntry> getAll(){
        return journalEntryRepo.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepo.findById(id);
    }
    public void deleteById(ObjectId Id, String userName){
        User user=userService.FindByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(Id));
        userService.SaveEntry(user);
        journalEntryRepo.deleteById(Id);
    }


}
