package com.seda.karmakutusu.controller;

import com.seda.karmakutusu.dto.KarmaEntryDTO;
import com.seda.karmakutusu.service.KarmaEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/karmalar") // Updated mapping
public class KarmaEntryController {

    private final KarmaEntryService karmaEntryService; // Renamed service

    @Autowired
    public KarmaEntryController(KarmaEntryService karmaEntryService) { // Renamed service
        this.karmaEntryService = karmaEntryService;
    }

    @PostMapping
    public ResponseEntity<KarmaEntryDTO> createKarmaEntry(@RequestBody KarmaEntryDTO karmaEntryDTO) { // Renamed method and DTO
        KarmaEntryDTO createdKarmaEntry = karmaEntryService.createKarmaEntry(karmaEntryDTO); // Renamed method
        return new ResponseEntity<>(createdKarmaEntry, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<KarmaEntryDTO>> getAllKarmaEntries() { // Renamed method
        List<KarmaEntryDTO> karmaEntries = karmaEntryService.getAllKarmaEntries(); // Renamed method
        return ResponseEntity.ok(karmaEntries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<KarmaEntryDTO> getKarmaEntryById(@PathVariable Long id) { // Renamed method
        return karmaEntryService.getKarmaEntryById(id) // Renamed method
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<KarmaEntryDTO> updateKarmaEntry(@PathVariable Long id, @RequestBody KarmaEntryDTO karmaEntryDetailsDTO) { // Renamed method and DTO
        try {
            KarmaEntryDTO updatedKarmaEntry = karmaEntryService.updateKarmaEntry(id, karmaEntryDetailsDTO); // Renamed method
            return ResponseEntity.ok(updatedKarmaEntry);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKarmaEntry(@PathVariable Long id) { // Renamed method
        try {
            karmaEntryService.deleteKarmaEntry(id); // Renamed method
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
} 