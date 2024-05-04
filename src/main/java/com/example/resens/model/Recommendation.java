package com.example.resens.model;


import com.example.resens.enumeration.Recommendation_Score;

import jakarta.persistence.*;
import lombok.*;

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

    @Enumerated(EnumType.STRING)
    private Recommendation_Score recommendationScore;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Etudiant etudiant;

}
