package org.example.learning_spring.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.example.learning_spring.DTOs.WuwaCharLoreDTO;
import org.example.learning_spring.Repositories.WuwaRepos.WuwaCharLoreRepository;
import org.example.learning_spring.Repositories.WuwaRepos.WuwaCharsRepository;
import org.example.learning_spring.TableClasses.Wuwa.WuwaChar;
import org.example.learning_spring.TableClasses.Wuwa.WuwaCharLore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class WuwaCharsService {
    final private WuwaCharsRepository wuwaCharsRepository;
    final private WuwaCharLoreRepository wuwaCharLoreRepository;

    @Autowired
    WuwaCharsService(WuwaCharsRepository wuwaCharsRepository,  WuwaCharLoreRepository wuwaCharLoreRepository) {
        this.wuwaCharsRepository = wuwaCharsRepository;
        this.wuwaCharLoreRepository = wuwaCharLoreRepository;
    }

    //ADD ALL CHARS FROM JSON
    public void injectWuwaChars() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
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
        if(element == null ||element.isEmpty()) {
            return weaponType == null || weaponType.isEmpty()
                    ? getAllWuwaChars()
                    :getByWeaponType(weaponType) ;
        }
        else if (weaponType == null || weaponType.isEmpty()) {
            return getByElement(element);
        }
        else{
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

    public List<WuwaChar> getByName(String name){
        return Collections.singletonList(wuwaCharsRepository.findByName(name).orElse(new WuwaChar()));
    }

    public List<WuwaChar> getByElement(String element){
        return wuwaCharsRepository.findByElement(element);
    }

    public List<WuwaChar> getByWeaponType(String weaponType){return wuwaCharsRepository.findByWeaponType(weaponType);}

    public void getWuwaCharsImgPaths(){
        wuwaCharsRepository.findAll().stream().map(WuwaChar::getImg_path).forEach(System.out::println);
    }

    //UPDATE
    @Transactional
    public void updateWuwaCharsImgPath(String newPath){
        List<WuwaChar> chars = getAllWuwaChars();
        chars.forEach(wuwaChar -> {
            int elements = wuwaChar.getImg_path().split("/").length;
            wuwaChar.setImg_path(newPath+ wuwaChar.getImg_path().split("/")[elements-1]);});
        wuwaCharsRepository.saveAll(chars);
    }

    //ADD
    @Transactional
    public void addWuwaChar(WuwaChar wuwaChar){
        wuwaCharsRepository.save(wuwaChar);
    }

    //DELETE
    @Transactional
    public void deleteWuwaChar(String name){
        wuwaCharsRepository.deleteByName(name);
    }


    //WUWA CHARACTER LORE TABLE
    //GET
    public List<WuwaCharLore> getAllWuwaCharLore(){
        return wuwaCharLoreRepository.findAll();
    }

    public WuwaCharLore getWuwaCharLore(String name){
        return wuwaCharLoreRepository.findByWuwaChar_Name(name);
    }

    public String getWuwaCharLoreSummary(WuwaChar wuwaChar){
        WuwaCharLore wuwaCharLore = getWuwaCharLore(wuwaChar.getName());
        return  wuwaCharLore.getLoreSummary();
    }

    public void getWuwaCharLoreDirectoryPaths(){
        getAllWuwaCharLore().stream().map(WuwaCharLore::getFullLorePath).forEach(System.out::println);
    }

    //UPDATE
    @Transactional
    public void updateWuwaCharLore(WuwaCharLore wuwaCharLore, String name){
        WuwaChar wuwaChar = getByName(name).get(0);
        wuwaCharLore.setWuwaChar(wuwaChar);
        wuwaCharLoreRepository.save(wuwaCharLore);
    }

    @Transactional
    public void updateAllWuwaCharLore(List<WuwaCharLoreDTO> wuwaCharLoreDTOs){
        List<WuwaCharLore> wuwaCharLoreList = new ArrayList<>();
        for(WuwaCharLoreDTO wuwaCharLoreDTO : wuwaCharLoreDTOs){
            WuwaChar wuwaChar = getByName(wuwaCharLoreDTO.getName()).get(0);
            WuwaCharLore wuwaCharLore = wuwaCharLoreDTO.getWuwaCharLoreObject();
            wuwaCharLore.setWuwaChar(wuwaChar);
            wuwaCharLoreList.add(wuwaCharLoreDTO.getWuwaCharLoreObject());
        }
        wuwaCharLoreRepository.saveAll(wuwaCharLoreList);
    }

    @Transactional
    public void updateWuwaFullLorePaths(String newPath){
        List<WuwaCharLore> charLoreList = getAllWuwaCharLore();
        charLoreList.forEach(wuwaChar -> {
            int elements = wuwaChar.getFullLorePath().split("/").length;
            wuwaChar.setFullLorePath(newPath+ wuwaChar.getFullLorePath().split("/")[elements-1]);});
        wuwaCharLoreRepository.saveAll(charLoreList);
    }
}
