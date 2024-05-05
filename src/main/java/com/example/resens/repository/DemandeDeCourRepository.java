package com.example.resens.repository;

import com.example.resens.model.DemandeDeCour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeDeCourRepository extends JpaRepository<DemandeDeCour,Long> {
}
