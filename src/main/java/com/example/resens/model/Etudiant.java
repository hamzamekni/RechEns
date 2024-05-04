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

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "matiere",
            joinColumns = @JoinColumn(name = "etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "matiere_id")
    )
    private List<Matiere> matiere;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "niveau",
            joinColumns = @JoinColumn(name = "etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "niveauEtude_Id")
    )
    private List<NiveauEtude> niveau;

    @ManyToOne
    private DemandeDeCour demandeDeCour_Etudiant;

    @OneToMany(mappedBy = "etudiant")
    private List<Paiement> paiement;

    @OneToMany(mappedBy = "etudiant")
    private List<Recommendation> recommendation;

}