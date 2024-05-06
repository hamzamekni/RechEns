package com.example.resens.model;

import com.example.resens.enumeration.Statut_Demande;
import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @Enumerated(EnumType.STRING)
    private Statut_Demande statutDemande;

    @ManyToOne
    @JoinColumn(name = "adress_id", referencedColumnName = "adress_id")
    private Adress adress;

    @ManyToOne
    @JoinColumn(name = "matiere_id", referencedColumnName = "matiere_id")
    private Matiere matiere;


    @ManyToOne
    @JoinColumn(name = "etudiant_id", referencedColumnName = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "teacherId", referencedColumnName = "teacherId")
    private Teacher teacher;

}