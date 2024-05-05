package com.example.resens.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matiere_Id;

    @NonNull
    private Integer code_etude;

    @OneToOne
    @JoinColumn(name = "demandeDeCour_id")
    private DemandeDeCour demandeDeCour;

    @ManyToOne
    @JoinColumn(name = "niveauEtude_id")
    private NiveauEtude niveauEtude;

    @ManyToMany(mappedBy = "matieres")
    Set<Teacher> teachers;


}
