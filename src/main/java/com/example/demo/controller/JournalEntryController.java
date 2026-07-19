//package com.example.demo.controller;
//
//import com.example.demo.entity.JournalEntry;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/_journal")
//public class JournalEntryController {
//
//    private Map<Long,JournalEntry> journalEntries = new HashMap();
//
//    @GetMapping
//    public List<JournalEntry> getAll(){      //localhost:8081/journal (not found)
//        return new ArrayList<>(journalEntries.values());
//    }
//
////    @PostMapping
////    public boolean createEntry(@RequestBody JournalEntry myEntry){
////        journalEntries.put(myEntry.getId(), myEntry);
////        return true;
////
////    }
//
//    @GetMapping("id/{myId}")
//    public JournalEntry getjournalEntryById(@PathVariable Long myId){
//        return journalEntries.get(myId);
//    }
//    @DeleteMapping("id/{myId}")
//    public JournalEntry deletejournalEntryById(@PathVariable Long myId){
//        return journalEntries.remove(myId);
//    }
//
//    @PutMapping ("/id/{Id}")
//    public JournalEntry updatejournalEntryById(@PathVariable Long Id,@RequestBody JournalEntry myEntry){
//        return journalEntries.put(Id,myEntry);
//    }
//
//}
