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
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();

    }


    @PostMapping
    public void createUser(@RequestBody User user){

        userService.SaveEntry(user);
    }

    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String userName){
        User userInDb= userService.FindByUserName(userName);
        if(userInDb !=null){
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.SaveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }










}
