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



}
