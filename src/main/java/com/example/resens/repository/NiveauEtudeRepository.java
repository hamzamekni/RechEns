package com.example.resens.repository;

import com.example.resens.model.NiveauEtude;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NiveauEtudeRepository extends JpaRepository<NiveauEtude,Long> {
}
