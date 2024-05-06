package com.example.resens.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adress_Id;
    @NonNull
    private String road_adress;

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region;


    @ManyToMany
    @JoinTable(
            name = "regions",
            joinColumns = @JoinColumn(name = "adress_id", referencedColumnName = "adress_id"),
            inverseJoinColumns = @JoinColumn(name = "region_id", referencedColumnName = "region_id"))
    Set<Region> regions;

}
