package com.seda.karmakutusu.repository;

import com.seda.karmakutusu.model.KarmaEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KarmaEntryRepository extends JpaRepository<KarmaEntry, Long> {
}
