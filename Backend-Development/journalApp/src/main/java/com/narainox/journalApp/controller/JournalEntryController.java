package com.narainox.journalApp.controller;

import com.narainox.journalApp.entity.JournalEntry;
import com.narainox.journalApp.exception.ResourceNotFound;
import com.narainox.journalApp.service.JournalEntryService;
import com.narainox.journalApp.utils.APIResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal") //Added the mapping on the complete class.
public class JournalEntryController {


    @Autowired
    private JournalEntryService journalEntryService;

    @GetMapping
    public ResponseEntity<APIResponse> getAll() {
        APIResponse apiResponse=new APIResponse();
        try {
            List<JournalEntry> journalEntry = journalEntryService.getAllJournalEntry();
            apiResponse.setMesaage("Journal Entry Is Found.");
            apiResponse.setData(journalEntry);
            return new ResponseEntity<>(apiResponse,HttpStatus.FOUND);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping
    public ResponseEntity<APIResponse> createEntry(@RequestBody JournalEntry journalEntry) {
        APIResponse apiResponse=new APIResponse();
        try {
            JournalEntry entry = journalEntryService.saveEntry(journalEntry);
            apiResponse.setMesaage("Journal Entry Is Created.");
            apiResponse.setData(entry);
            return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/id/{myId}")
    public ResponseEntity<APIResponse> getJournalEntryById(@PathVariable("myId") ObjectId myId) {
        APIResponse apiResponse=new APIResponse();
        try {
            JournalEntry entry = journalEntryService.getJournalEntryById(myId);
            apiResponse.setMesaage("Journal Entry Is Found.");
            apiResponse.setData(entry);
            return new ResponseEntity<>(apiResponse,HttpStatus.FOUND);
        }
        catch (ResourceNotFound resourceNotFound)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/id/{myId}")
    public ResponseEntity<APIResponse> deleteJournalEntryById(@PathVariable("myId") ObjectId myId) {
        APIResponse apiResponse=new APIResponse();
        try {
            journalEntryService.deleteJournalEntryById(myId);
            apiResponse.setMesaage("Journal Entry Is Deleted.");
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }
        catch (ResourceNotFound resourceNotFound)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/id/{myId}")
    public ResponseEntity<APIResponse> updateJournalEntryById(@PathVariable("myId") ObjectId myId, @RequestBody JournalEntry journalEntry) {
        APIResponse apiResponse=new APIResponse();
        try {
            JournalEntry entry = journalEntryService.updateJournalEntry(myId, journalEntry);
            apiResponse.setMesaage("Journal Entry Is Updated Successfully.");
            apiResponse.setData(entry);
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        }
        catch (ResourceNotFound resourceNotFound)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception exception)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
