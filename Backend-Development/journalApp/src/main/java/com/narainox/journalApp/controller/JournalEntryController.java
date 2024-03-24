package com.narainox.journalApp.controller;

import com.narainox.journalApp.entity.JournalEntry;
import com.narainox.journalApp.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal") //Added the mapping on the complete class.
public class JournalEntryController {


    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAllJournalEntry();
    }

    @PostMapping
    public JournalEntry createEntry(@RequestBody JournalEntry journalEntry) {
        journalEntryService.saveEntry(journalEntry);
        return journalEntry;
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable("myId") ObjectId myId) {
        return journalEntryService.getJournalEntryById(myId).orElse(null);
    }

    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable("myId") ObjectId myId) {
        return journalEntryService.deleteJournalEntryById(myId).orElse(null);
    }

    @PutMapping("/id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable("myId") Long myId, @RequestBody JournalEntry journalEntry) {
       return null;
    }
}
