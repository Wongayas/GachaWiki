package org.example.learning_spring.Services;

import org.example.learning_spring.Repositories.BrownDust2Repositories.BrownDust2CharRepository;
import org.example.learning_spring.TableClasses.BrownDust2.BrownDust2Char;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrownDust2CharService {
    final private BrownDust2CharRepository brownDust2CharRepository;

    @Autowired
    public BrownDust2CharService(BrownDust2CharRepository brownDust2CharRepository) {
        this.brownDust2CharRepository = brownDust2CharRepository;
    }

    //ADD
    @Transactional
    public void saveAllBrownDust2Chars(List<BrownDust2Char> brownDust2Chars) {
        brownDust2CharRepository.saveAll(brownDust2Chars);
    }

    @Transactional
    public void addBrownDust2Char(BrownDust2Char brownDust2Char) {
        brownDust2CharRepository.save(brownDust2Char);
    }

    //GET
    public BrownDust2Char getBrownDust2CharById(Integer id) {
        return brownDust2CharRepository.findById(id).orElse(null);
    }

    public BrownDust2Char getBrownDust2CharByName(String name) {
        return brownDust2CharRepository.findByName(name).orElse(null);
    }

    //UPDATE
    @Transactional
    public void updateBrowndust2Char(BrownDust2Char brownDust2Char) {
        brownDust2CharRepository.save(brownDust2Char);
    }

    //DELETE
    @Transactional
    public void deleteBrownDust2CharById(Integer id) {
        brownDust2CharRepository.deleteById(id);
    }
}
