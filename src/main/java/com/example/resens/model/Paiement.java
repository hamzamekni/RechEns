package com.example.resens.model;

import com.example.resens.enumeration.Statut_Paiement;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paiementId;

    @NonNull
    private String Signe_Paiement;

    @NonNull
    private Float Montant_Paiement;

    @NonNull
    private LocalDateTime jour_Paiement = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Statut_Paiement statut_paiement;

    @ManyToOne
    @JoinColumn(name = "teacherId", referencedColumnName = "teacherId")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "etudiant_id", referencedColumnName = "etudiant_id")
    private Etudiant etudiant;

}
