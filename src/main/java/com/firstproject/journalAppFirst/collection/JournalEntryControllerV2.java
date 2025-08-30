package com.firstproject.journalAppFirst.collection;


import com.firstproject.journalAppFirst.entity.JournalEntry;
import com.firstproject.journalAppFirst.entity.User;
import com.firstproject.journalAppFirst.service.JournalEntryService;
import com.firstproject.journalAppFirst.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {



    @Autowired
    JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){

        User user=userService.FindByUserName(userName);

        List<JournalEntry> all=user.getJournalEntries();
        if(all!=null){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry>  CreateEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
        try {
//            User user =userService.FindByUserName(userName);
            journalEntryService.SaveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/id/{myID}")
    public ResponseEntity<JournalEntry> getJournalEntryByID(@PathVariable ObjectId myID){
        Optional<JournalEntry> journalEntry= journalEntryService.findById(myID);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }else{
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/id/{userName}/{myID}")
    public ResponseEntity<?> DeleteJournalEntryByID(@PathVariable ObjectId myID,String userName){
         journalEntryService.deleteById(myID,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/id/{userName}/{myID}")
    public ResponseEntity<?> UpdateJournalEntryByID(@PathVariable ObjectId myID,@RequestBody JournalEntry newEntry,@PathVariable String userName){

        JournalEntry old=journalEntryService.findById(myID).orElse(null);
        if(old !=null){
            old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent()!=null && !newEntry.equals("")? newEntry.getContent() : old.getContent());
            journalEntryService.SaveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }









}
