package org.example.learning_spring.repository.browndust2;

import org.example.learning_spring.entity.BrownDust2.Costumes.PrestigeCostume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestigeCostumeRepository extends JpaRepository<PrestigeCostume,Integer> {
}
