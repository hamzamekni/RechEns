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
    private Long etudiant_Id;

    @NonNull
    private Float Montant;

    @NonNull
    private String Section;

    @OneToMany
    @JoinColumn(name = "recommandation_id")
    private List<Recommendation> recommendation;

    @OneToMany
    @JoinColumn(name = "paiementId")
    private List<Paiement> paiements;

    @OneToMany
    @JoinColumn(name = "demandeDeCour_id")
    private List<DemandeDeCour> demandeDeCours;

    @OneToOne
    @JoinColumn(name = "userId")
    private User users;

}