package com.example.resens.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


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


    @JsonBackReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "region",
            joinColumns = @JoinColumn(name = "region_Id"),
            inverseJoinColumns = @JoinColumn(name = "adress_Id")
    )
    private List<Region> regions;

    @OneToOne(mappedBy = "adress")
    private User user;

    @OneToMany(mappedBy = "adress_DemandeDeCour")
    private List<DemandeDeCour> demandeDeCours;

}
