package org.example.learning_spring.service;

import org.example.learning_spring.dto.BrownDust2PrestigeCostumeDTO;
import org.example.learning_spring.repository.browndust2.BrownDust2CharRepository;
import org.example.learning_spring.repository.browndust2.CharCostumeRepository;
import org.example.learning_spring.repository.browndust2.PrestigeCostumeRepository;
import org.example.learning_spring.entity.BrownDust2.BrownDust2Char;
import org.example.learning_spring.entity.BrownDust2.Costumes.CharCostume;
import org.example.learning_spring.entity.BrownDust2.Costumes.PrestigeCostume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BrownDust2CharService {
    final private BrownDust2CharRepository brownDust2CharRepository;
    final private PrestigeCostumeRepository prestigeCostumeRepository;
    final private CharCostumeRepository charCostumeRepository;

    @Autowired
    public BrownDust2CharService(BrownDust2CharRepository brownDust2CharRepository,  PrestigeCostumeRepository prestigeCostumeRepository, CharCostumeRepository charCostumeRepository) {
        this.brownDust2CharRepository = brownDust2CharRepository;
        this.prestigeCostumeRepository = prestigeCostumeRepository;
        this.charCostumeRepository = charCostumeRepository;
    }

    //ADD
    @Transactional
    public void saveAllBrownDust2Chars(List<BrownDust2Char> brownDust2Chars) {
        for (BrownDust2Char brownDust2Char : brownDust2Chars) {
            List<CharCostume> characterList;
            if(brownDust2Char.getCharCostumes()!=null){
                characterList =  brownDust2Char.getCharCostumes();
                for (CharCostume charCostume : characterList) {
                    charCostume.setBrownDust2Char(brownDust2Char);
                }
            }
        }
        brownDust2CharRepository.saveAll(brownDust2Chars);
    }

    @Transactional
    public void addBrownDust2Char(BrownDust2Char brownDust2Char) {
        brownDust2CharRepository.save(brownDust2Char);
    }

    //GET

    public List<BrownDust2Char> getAllBrownDust2Chars() {
        return brownDust2CharRepository.findAll();
    }

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

    //CharCostume
    //ADD
    @Transactional
    public void addPrestigeCostume( BrownDust2PrestigeCostumeDTO brownDust2PrestigeCostumeDTO) {
        String charName =  brownDust2PrestigeCostumeDTO.getCharName();
        System.out.println(charName);
        String costumeName =  brownDust2PrestigeCostumeDTO.getCostumeName();
        PrestigeCostume prestigeCostume = brownDust2PrestigeCostumeDTO.getPrestigeCostume();
        BrownDust2Char brownDust2Char = getBrownDust2CharByName(charName);
        List<CharCostume> characterSet =  brownDust2Char.getCharCostumes();
        for (CharCostume charCostume : characterSet) {
            if(charCostume.getCostumeName().equalsIgnoreCase(costumeName)){
                prestigeCostume.setCharCostume(charCostume);
                charCostume.setPrestigeCostume(prestigeCostume);
            }
        }
    }

    //UPDATE
    @Transactional
    public void updatePaths() {
        List<BrownDust2Char> brownDust2Chars = getAllBrownDust2Chars();
        for (BrownDust2Char brownDust2Char : brownDust2Chars) {
            List<CharCostume> characterSet =  brownDust2Char.getCharCostumes();
            for (CharCostume charCostume : characterSet) {
                charCostume.setTileRangeImgPath(trimmedPath(charCostume.getTileRangeImgPath()));
                charCostume.setCostumeChibiImg(trimmedPath(charCostume.getCostumeChibiImg()));
                charCostume.setCostumeImgPath(trimmedPath(charCostume.getCostumeImgPath()));
                charCostume.setCostumeIdleGifPath(trimmedPath(charCostume.getCostumeIdleGifPath()));
                charCostume.setCostumeMotionGifPath(trimmedPath(charCostume.getCostumeMotionGifPath()));
            }
        }
    }

    public String trimmedPath(String oldPath) {
        if (oldPath == null) return null;
        int i = oldPath.indexOf("GachaGameImages");
        return "/"+oldPath.substring(i).replace("\\","/");
    }

}
