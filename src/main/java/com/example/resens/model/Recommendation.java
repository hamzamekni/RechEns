package com.example.resens.model;


import com.example.resens.enumeration.Recommendation_Score;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recommendationId;

    @NonNull
    private String recommandation;

    @ManyToOne
    @JoinColumn(name = "etudiantId", referencedColumnName = "etudiantId")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "teacherId", referencedColumnName = "teacherId")
    private Teacher teacher;
}
