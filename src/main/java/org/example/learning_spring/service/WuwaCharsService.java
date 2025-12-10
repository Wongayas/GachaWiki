package org.example.learning_spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.learning_spring.entity.Wuwa.WuwaChar;
import org.example.learning_spring.entity.Wuwa.WuwaCharLore;
import org.example.learning_spring.dto.WuwaCharLoreDTO;
import org.example.learning_spring.repository.wuwa.WuwaCharLoreRepository;
import org.example.learning_spring.repository.wuwa.WuwaCharsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WuwaCharsService {
    ObjectMapper mapper = new ObjectMapper();
    final private WuwaCharsRepository wuwaCharsRepository;
    final private WuwaCharLoreRepository wuwaCharLoreRepository;
    final private String baseImgPath;
    final private String baseLorePath;

    @Autowired
    WuwaCharsService(WuwaCharsRepository wuwaCharsRepository, WuwaCharLoreRepository wuwaCharLoreRepository,
                     @Value("${wuwa.img.base_path}") String baseImgPath, @Value("${wuwa.lore.base_path}") String baseLorePath) {
        this.wuwaCharsRepository = wuwaCharsRepository;
        this.wuwaCharLoreRepository = wuwaCharLoreRepository;
        this.baseImgPath = baseImgPath;
        this.baseLorePath = baseLorePath;
    }

    //ADD ALL CHARS FROM JSON
    public void injectWuwaChars() throws IOException {
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        List<WuwaChar> allWuwaChars = mapper.readValue(new File("src/main/resources/JSONFolder/wuwaChars.json"), new TypeReference<>() {
        });
        wuwaCharsRepository.saveAll(allWuwaChars);
    }

    //GET
    //List all characters
    public List<WuwaChar> getAllWuwaChars() {
        return wuwaCharsRepository.findAll();
    }

    //FILTER CHARACTERS
    public List<WuwaChar> getByElementAndWeapon(String element, String weaponType) {
        if (element == null || element.isEmpty()) {
            return weaponType == null || weaponType.isEmpty()
                    ? getAllWuwaChars()
                    : getByWeaponType(weaponType);
        } else if (weaponType == null || weaponType.isEmpty()) {
            return getByElement(element);
        } else {
            return getAllWuwaChars()
                    .stream()
                    .filter(wuwaChar -> wuwaChar.getElement().equals(element))
                    .filter(wuwaChar -> wuwaChar.getWeaponType().equals(weaponType))
                    .toList();
        }
    }

    public List<WuwaChar> sortByRarity(String rarity) {
        return getAllWuwaChars().stream().filter(wuwaChar -> wuwaChar.getRarity().equals(rarity)).toList();
    }

    public List<WuwaChar> getByName(String name) {
        return Collections.singletonList(wuwaCharsRepository.findByName(name).orElse(new WuwaChar()));
    }

    public List<WuwaChar> getByElement(String element) {
        return wuwaCharsRepository.findByElement(element);
    }

    public List<WuwaChar> getByWeaponType(String weaponType) {
        return wuwaCharsRepository.findByWeaponType(weaponType);
    }

    public void getWuwaCharsImgPaths() {
        wuwaCharsRepository.findAll().stream().map(WuwaChar::getImg_path).forEach(System.out::println);
    }

    //UPDATE
    @Transactional
    public void updateWuwaCharsImgPath(String newPath) {
        List<WuwaChar> chars = getAllWuwaChars();
        chars.forEach(wuwaChar -> {
            int elements = wuwaChar.getImg_path().split("/").length;
            wuwaChar.setImg_path(newPath + wuwaChar.getImg_path().split("/")[elements - 1]);
        });
        wuwaCharsRepository.saveAll(chars);
    }

    //ADD
    @Transactional
    public void addWuwaChar(WuwaChar wuwaChar) throws JsonProcessingException {
        WuwaCharLore wuwaCharLore = wuwaChar.getWuwaCharLore();
        wuwaCharLore.setFullLorePath(baseLorePath + wuwaChar.getName() + ".txt");
        wuwaChar.setImg_path(baseImgPath + wuwaChar.getImg_path()+ ".png");
        if (wuwaChar.getWuwaCharLore() != null) {
            wuwaCharLore.setWuwaChar(wuwaChar);
        }
        wuwaCharsRepository.save(wuwaChar);
    }

    //DELETE
    @Transactional
    public void deleteWuwaChar(String name) {
        wuwaCharsRepository.deleteByName(name);
    }


    //WUWA CHARACTER LORE TABLE
    //GET
    public List<WuwaCharLore> getAllWuwaCharLore() {
        return wuwaCharLoreRepository.findAll();
    }

    public WuwaCharLore getWuwaCharLore(String name) {
        return wuwaCharLoreRepository.findByWuwaChar_Name(name);
    }

    public String getWuwaCharLoreSummary(WuwaChar wuwaChar) {
        WuwaCharLore wuwaCharLore = getWuwaCharLore(wuwaChar.getName());
        return wuwaCharLore.getLoreSummary();
    }

    public void getWuwaCharLoreDirectoryPaths() {
        getAllWuwaCharLore().stream().map(WuwaCharLore::getFullLorePath).forEach(System.out::println);
    }

    //UPDATE
    @Transactional
    public void updateWuwaCharLore(WuwaCharLoreDTO wuwaCharLoreDTO) {
        String name = wuwaCharLoreDTO.getName();
        WuwaCharLore wuwaCharLore = wuwaCharLoreDTO.getWuwaCharLoreObject();
        WuwaChar wuwaChar = getByName(name).get(0);
        wuwaCharLore.setWuwaChar(wuwaChar);
        wuwaCharLoreRepository.save(wuwaCharLore);
    }

    @Transactional
    public void updateAllWuwaCharLore(List<WuwaCharLoreDTO> wuwaCharLoreDTOs) {
        List<WuwaCharLore> wuwaCharLoreList = new ArrayList<>();
        for (WuwaCharLoreDTO wuwaCharLoreDTO : wuwaCharLoreDTOs) {
            WuwaChar wuwaChar = getByName(wuwaCharLoreDTO.getName()).get(0);
            WuwaCharLore wuwaCharLore = wuwaCharLoreDTO.getWuwaCharLoreObject();
            wuwaCharLore.setWuwaChar(wuwaChar);
            wuwaCharLoreList.add(wuwaCharLore);
        }
        wuwaCharLoreRepository.saveAll(wuwaCharLoreList);
    }

    @Transactional
    public void updateWuwaFullLorePaths(String newPath) {
        List<WuwaCharLore> charLoreList = getAllWuwaCharLore();
        charLoreList.forEach(wuwaChar -> {
            int elements = wuwaChar.getFullLorePath().split("/").length;
            wuwaChar.setFullLorePath(newPath + wuwaChar.getFullLorePath().split("/")[elements - 1]);
        });
        wuwaCharLoreRepository.saveAll(charLoreList);
    }
}
