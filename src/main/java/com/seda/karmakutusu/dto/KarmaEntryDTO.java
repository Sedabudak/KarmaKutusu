package com.seda.karmakutusu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KarmaEntryDTO {
    private Long id;
    private String mesaj;
    private String kategori;
    private String yazar;
} 