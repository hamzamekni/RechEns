package com.example.resens.model;

import jakarta.persistence.*;
import lombok.*;

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
    private DemandeDeCour demandeDeCour;

    @ManyToOne
    private NiveauEtude niveauEtude;

    @OneToOne
    private DemandeDeCour demandeDeCour_Matiere;

}
