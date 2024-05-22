package com.example.resens.dto;

import com.example.resens.enumeration.Statut_Demande;
import com.example.resens.model.Adress;
import com.example.resens.model.Etudiant;
import com.example.resens.model.Matiere;
import com.example.resens.model.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DemandeRequest {
    private String titre_demande;
    private String detail_demande;
    private String locale;
    private Float prix_max;
    private Float prix_min;
    private Statut_Demande statutDemande;
    private Long adressId;
    private Long matiereId;
    private Long etudiantId;
    private Long teacherId;

}
