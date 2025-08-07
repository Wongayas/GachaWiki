package org.example.learning_spring;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class WuwaCharsService {
    final private WuwaCharsRepository wuwaCharsRepository;

    @Autowired
    WuwaCharsService(WuwaCharsRepository wuwaCharsRepository) {
        this.wuwaCharsRepository = wuwaCharsRepository;
    }

    //ADD ALL CHARS FROM JSON
    public void injectWuwaChars() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        List<WuwaChar> allWuwaChars = mapper.readValue(new File("src/main/resources/JSONFolder/wuwaChars.json"), new TypeReference<>() {
        });
        wuwaCharsRepository.saveAll(allWuwaChars);
    }

    public List<WuwaChar> getAllWuwaChars() {
        return wuwaCharsRepository.findAll();
    }

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
                    .filter(wuwaChar -> wuwaChar.element.equals(element))
                    .filter(wuwaChar -> wuwaChar.weaponType.equals(weaponType))
                    .toList();
        }
    }

    public List<WuwaChar> getByRarity(String rarity) {
        return getAllWuwaChars().stream().filter(wuwaChar -> wuwaChar.rarity.equals(rarity)).toList();
    }

    public List<WuwaChar> getByName(String name){
        return Collections.singletonList(wuwaCharsRepository.findByName(name).orElse(new WuwaChar()));
    }

    public List<WuwaChar> getByElement(String element){
        return wuwaCharsRepository.findByElement(element);
    }

    public List<WuwaChar> getByWeaponType(String weaponType){return wuwaCharsRepository.findByWeaponType(weaponType);}

    public void deleteWuwaChar(String name){
        wuwaCharsRepository.deleteByName(name);
    }

    public void addWuwaChar(WuwaChar wuwaChar){
        wuwaCharsRepository.save(wuwaChar);
    }
}
