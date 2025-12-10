package org.example.learning_spring.repository.wuwa;

import org.example.learning_spring.entity.Wuwa.WuwaChar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WuwaCharsRepository extends JpaRepository<WuwaChar,Integer> {
    Optional<WuwaChar> findByName(String name);
    List<WuwaChar> findByElement(String element);
    List<WuwaChar> findByWeaponType(String weaponType);
    void deleteByName(String name);
}
