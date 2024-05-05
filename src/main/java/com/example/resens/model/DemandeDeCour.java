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

    @OneToOne
    @JoinColumn(name = "adress_id")
    private Adress adress;

    @OneToOne
    @JoinColumn(name = "matiere_id")
    private Matiere matiere;

    @OneToMany
    @JoinColumn(name = "supportCour_id")
    private List<SupportCour> supportCour;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "teacherId")
    private Teacher teacher;

}