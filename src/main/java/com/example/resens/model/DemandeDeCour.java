package com.example.resens.model;

import com.example.resens.enumeration.Statut_Demande;
import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "adressId", referencedColumnName = "adressId")
    private Adress adress;

    @ManyToOne
    @JoinColumn(name = "matiereId", referencedColumnName = "matiereId")
    private Matiere matiere;


    @ManyToOne
    @JoinColumn(name = "etudiantId", referencedColumnName = "etudiantId")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "teacherId", referencedColumnName = "teacherId")
    private Teacher teacher;

}