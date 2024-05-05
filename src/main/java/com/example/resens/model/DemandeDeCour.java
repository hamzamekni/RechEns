package com.example.resens.model;

import com.example.resens.enumeration.Statut_Demande;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DemandeDeCour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long demandeDeCour_Id;
    @NonNull
    private String titre_demande;
    @NonNull
    private String detail_demande;
    @NonNull
    private String locale;
    @NonNull
    private Float prix_max;
    @NonNull
    private Float prix_min;

    @OneToOne
    private Adress adress;

    @Enumerated(EnumType.STRING)
    private Statut_Demande statutDemande;

    @OneToMany(mappedBy = "demandeDeCour_Etudiant")
    private List<Etudiant> etudiants;

    @OneToOne(mappedBy = "demandeDeCour_Matiere")
    private Matiere matiere;

    @ManyToOne
    private Teacher teacher; // Corrected field type and name

    @ManyToOne
    @JoinColumn(name = "adress")
    private Adress adress_DemandeDeCour;


}