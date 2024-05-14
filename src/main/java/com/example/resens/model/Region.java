package com.example.resens.model;

import com.nimbusds.openid.connect.sdk.claims.Address;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long region_Id;

    @ManyToOne
    @JoinColumn(name = "ville_id", referencedColumnName = "ville_id")
    private Ville villes;



}
