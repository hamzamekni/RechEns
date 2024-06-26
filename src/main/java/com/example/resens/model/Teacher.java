package com.example.resens.model;

import com.example.resens.enumeration.Role;
import com.example.resens.enumeration.Statut_Etude_Presentiel;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;
    @NonNull
    private String firstName;
    @NonNull
    private String email;

    @NonNull
    private float montant_non_paye;

    @Enumerated(EnumType.STRING)
    private Statut_Etude_Presentiel statut_etude_presentiel;

    @NonNull
    @JsonProperty("Detail_Etude_Presentiel")
    private String detailEtudePresentiel;
    @NonNull
    private String detailEnseigant;
    @NonNull
    private String password;
    @NonNull
    private Integer phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean enabled;

    @ElementCollection
    private List<String> uploadedUrls = new ArrayList<>();


    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }




}