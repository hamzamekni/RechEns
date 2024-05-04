package com.example.resens.model;

import com.example.resens.enumeration.Statut_Etude_Presentiel;
import lombok.*;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    @NonNull
    private Float Montant_NonPaye;

    @Enumerated(EnumType.STRING)
    private Statut_Etude_Presentiel statut_etude_presentiel;

    @NonNull
    private String Detail_Etude_Presentiel;

    private int Numbero;

    @NonNull
    private String Detail_Enseigant;

    @OneToMany(mappedBy = "teacher") // Corrected mappedBy attribute
    private Set<DemandeDeCour> demandeDeCours;
}