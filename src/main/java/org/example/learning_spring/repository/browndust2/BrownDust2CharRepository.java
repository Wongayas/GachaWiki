package org.example.learning_spring.repository.browndust2;

import org.example.learning_spring.entity.BrownDust2.BrownDust2Char;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BrownDust2CharRepository extends JpaRepository<BrownDust2Char,Integer> {
    Optional<BrownDust2Char> findByName(String name);
}
