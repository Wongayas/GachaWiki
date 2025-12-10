package org.example.learning_spring.repository.wuwa;

import org.example.learning_spring.entity.Wuwa.WuwaCharLore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WuwaCharLoreRepository extends JpaRepository<WuwaCharLore,Integer> {
    WuwaCharLore findByWuwaChar_Id(int wuwaCharId);
    WuwaCharLore findByWuwaChar_Name(String wuwaCharName);
}
