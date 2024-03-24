package com.narainox.journalApp.controller;

import com.narainox.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal") //Added the mapping on the complete class.
public class JournalEntryController {

    private Map<Long,JournalEntry> journalEntries= new HashMap<>();
    @GetMapping
    public List<JournalEntry> getAll()
    {
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping
    public void createEntry(@RequestBody JournalEntry journalEntry)
    {
        journalEntries.put(journalEntry.getId(),journalEntry);
    }

    @GetMapping("/id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable("myId") Long myId)
    {
        return journalEntries.get(myId);
    }
    @DeleteMapping("/id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable("myId") Long myId)
    {
        return journalEntries.remove(myId);
    }
    @PutMapping("/id/{myId}")
    public JournalEntry updateJournalEntryById(@PathVariable("myId") Long myId,@RequestBody JournalEntry journalEntry)
    {
        return journalEntries.put(myId,journalEntry);
    }
}
