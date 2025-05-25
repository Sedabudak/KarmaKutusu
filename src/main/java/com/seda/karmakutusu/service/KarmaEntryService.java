package com.seda.karmakutusu.service;

import com.seda.karmakutusu.dto.KarmaEntryDTO;
import com.seda.karmakutusu.model.KarmaEntry;
import com.seda.karmakutusu.repository.KarmaEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class KarmaEntryService {

    private final KarmaEntryRepository karmaEntryRepository;

    @Autowired
    public KarmaEntryService(KarmaEntryRepository karmaEntryRepository) {
        this.karmaEntryRepository = karmaEntryRepository;
    }

    private KarmaEntryDTO convertToDTO(KarmaEntry karmaEntry) {
        if (karmaEntry == null) {
            return null;
        }
        return new KarmaEntryDTO(
                karmaEntry.getId(),
                karmaEntry.getMesaj(),
                karmaEntry.getKategori(),
                karmaEntry.getYazar()
        );
    }

    private KarmaEntry convertToEntity(KarmaEntryDTO karmaEntryDTO) {
        if (karmaEntryDTO == null) {
            return null;
        }
        KarmaEntry karmaEntry = new KarmaEntry();
        karmaEntry.setId(karmaEntryDTO.getId());
        karmaEntry.setMesaj(karmaEntryDTO.getMesaj());
        karmaEntry.setKategori(karmaEntryDTO.getKategori());
        karmaEntry.setYazar(karmaEntryDTO.getYazar());
        return karmaEntry;
    }

    public KarmaEntryDTO createKarmaEntry(KarmaEntryDTO karmaEntryDTO) {
        KarmaEntry karmaEntry = convertToEntity(karmaEntryDTO);
        KarmaEntry savedKarmaEntry = karmaEntryRepository.save(karmaEntry);
        return convertToDTO(savedKarmaEntry);
    }

    public List<KarmaEntryDTO> getAllKarmaEntries() {
        return karmaEntryRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<KarmaEntryDTO> getKarmaEntryById(Long id) {
        return karmaEntryRepository.findById(id)
                .map(this::convertToDTO);
    }

    public KarmaEntryDTO updateKarmaEntry(Long id, KarmaEntryDTO karmaEntryDetailsDTO) {
        KarmaEntry karmaEntry = karmaEntryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("KarmaEntry not found with id: " + id));

        karmaEntry.setMesaj(karmaEntryDetailsDTO.getMesaj());
        karmaEntry.setKategori(karmaEntryDetailsDTO.getKategori());
        karmaEntry.setYazar(karmaEntryDetailsDTO.getYazar());
        KarmaEntry updatedKarmaEntry = karmaEntryRepository.save(karmaEntry);
        return convertToDTO(updatedKarmaEntry);
    }

    public void deleteKarmaEntry(Long id) {
        if (!karmaEntryRepository.existsById(id)) {
            throw new RuntimeException("KarmaEntry not found with id: " + id);
        }
        karmaEntryRepository.deleteById(id);
    }
} 