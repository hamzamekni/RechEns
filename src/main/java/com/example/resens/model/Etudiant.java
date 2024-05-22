package com.example.resens.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long etudiantId;

    @NonNull
    private Float Montant;

    @NonNull
    private String Section;

    @OneToOne
    @JoinColumn(name = "userId")
    private User users;

}