package com.narainox.journalApp.service;

import com.narainox.journalApp.entity.JournalEntry;
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

    public void saveEntry(JournalEntry journalEntry)
    {
        journalEntry.setDate(LocalDate.now());
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAllJournalEntry()
    {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(ObjectId myid)
    {
        return journalEntryRepository.findById(myid);
    }

    public void deleteJournalEntryById(ObjectId myid)
    {
        journalEntryRepository.deleteById(myid);
    }

    public JournalEntry updateJournalEntry(ObjectId myid,JournalEntry journalEntry)
    {
        JournalEntry entry = journalEntryRepository.findById(myid).orElse(null);
        if (entry !=null)
        {
            entry.setContent(journalEntry.getContent());
            entry.setTitle(journalEntry.getTitle());
            journalEntryRepository.save(entry);
            return entry;
        }
        else {
            return entry;
        }
    }

}
