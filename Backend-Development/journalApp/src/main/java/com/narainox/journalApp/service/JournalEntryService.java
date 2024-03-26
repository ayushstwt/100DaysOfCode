package com.narainox.journalApp.service;

import com.narainox.journalApp.entity.JournalEntry;
import com.narainox.journalApp.exception.ResourceNotFound;
import com.narainox.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public JournalEntry saveEntry(JournalEntry journalEntry)
    {
        journalEntry.setDate(LocalDate.now());
        return journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllJournalEntry()
    {
        return journalEntryRepository.findAll();
    }

    public JournalEntry getJournalEntryById(ObjectId myid)
    {
        JournalEntry journalEntry = journalEntryRepository
                .findById(myid)
                .orElseThrow(() -> new ResourceNotFound("JournalEntry", "JournEntryId", myid));
        return journalEntry;
    }

    public void deleteJournalEntryById(ObjectId myid)
    {
        JournalEntry journalEntry = journalEntryRepository
                .findById(myid)
                .orElseThrow(() -> new ResourceNotFound("JournalEntry", "JournEntryId", myid));
        journalEntryRepository.delete(journalEntry);
    }

    public JournalEntry updateJournalEntry(ObjectId myid,JournalEntry journalEntry)
    {
        JournalEntry oldEntry = journalEntryRepository
                .findById(myid)
                .orElseThrow(() -> new ResourceNotFound("JournalEntry", "JournEntryId", myid));
        oldEntry.setTitle(journalEntry.getTitle());
        oldEntry.setContent(journalEntry.getContent());
        journalEntryRepository.save(oldEntry);
        return oldEntry;
    }

}
