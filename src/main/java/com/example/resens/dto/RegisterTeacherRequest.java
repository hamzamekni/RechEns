package com.example.resens.dto;

import com.example.resens.enumeration.Statut_Etude_Presentiel;
import com.example.resens.model.Adress;
import jakarta.persistence.Lob;
import lombok.*;
import com.example.resens.enumeration.Role;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterTeacherRequest {
    private String firstName;
    private String email;
    private float montantNonPaye;
    private Statut_Etude_Presentiel statut_etude_presentiel;
    private String detailEtudePresentiel;
    private String detailEnseigant;
    private Integer phoneNumber;
    private String password;
    private boolean enabled;
    private Role role;
    private MultipartFile file;

}
