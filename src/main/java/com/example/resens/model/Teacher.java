package com.example.resens.model;

import com.example.resens.enumeration.Statut_Etude_Presentiel;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;
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


    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "matieres",
            joinColumns = @JoinColumn(name = "teacherId", referencedColumnName = "teacherId"),
            inverseJoinColumns = @JoinColumn(name = "matiere_id", referencedColumnName = "matiere_id"))
    Set<Matiere> matieres;

}