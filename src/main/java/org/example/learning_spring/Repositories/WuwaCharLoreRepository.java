package org.example.learning_spring.Repositories;

import org.example.learning_spring.WuwaCharLore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WuwaCharLoreRepository extends JpaRepository<WuwaCharLore,Integer> {
}
