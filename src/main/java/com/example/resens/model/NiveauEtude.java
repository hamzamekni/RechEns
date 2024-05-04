package com.example.resens.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NiveauEtude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long niveauEtude_Id;
    @NonNull
    private String text_niveau;
}
