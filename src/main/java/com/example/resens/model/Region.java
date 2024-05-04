package com.example.resens.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @NonNull
    private Ville ville;

    //many to many with Adress
    @ManyToMany(mappedBy = "regions")
    private List<Adress> adresses;

}
