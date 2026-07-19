package com.example.demo.controller;

import com.example.demo.entity.JournalEntry;
import com.example.demo.entity.User;
import com.example.demo.service.JournalEntryService;
import com.example.demo.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){ //localhost:8081/journal (not found)
        User user =userService.findByuserName(userName);
        List<JournalEntry> all = journalEntryService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//    public List<JournalEntry> getAll(){      //localhost:8081/journal (not found)
//        return journalEntryService.getAll();
//    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
        try {
//            myEntry.setDate(LocalDateTime.now());
            journalEntryService.SaveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//    public JournalEntry createEntry(@RequestBody JournalEntry myEntry){
//        myEntry.setDate(LocalDateTime.now());
//        journalEntryService.SaveEntry(myEntry);
//        return myEntry;
//    }


    @GetMapping("id/{myId}")
    public ResponseEntity<?> getjournalEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry =journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//    public JournalEntry getjournalEntryById(@PathVariable ObjectId myId){
//        return journalEntryService.findById(myId).orElse(null);
//    }
    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deletejournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName){
         journalEntryService.deleteById(myId,userName);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
//    public boolean deletejournalEntryById(@PathVariable ObjectId myId){
//        journalEntryService.deleteById(myId);
//        return true;
//    }
    @PutMapping ("/id/{username}/{myId}")
    public ResponseEntity<?> updatejournalEntryById(@PathVariable ObjectId myId, @RequestBody JournalEntry newEntry,@PathVariable String userName) {
        JournalEntry old = journalEntryService.findById(myId).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
            journalEntryService.SaveEntry(old);//userName not mention in video
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
//    public JournalEntry updatejournalEntryById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
//        JournalEntry old = journalEntryService.findById(id).orElse(null);
//        if (old != null) {
//            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
//            old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
//        }
//        journalEntryService.SaveEntry(old);
//        return old;
//    }
}
