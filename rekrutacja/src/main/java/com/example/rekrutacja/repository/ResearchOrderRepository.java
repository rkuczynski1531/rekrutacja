package com.example.rekrutacja.repository;

import com.example.rekrutacja.model.ResearchOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResearchOrderRepository extends JpaRepository<ResearchOrder, Long> {
}
