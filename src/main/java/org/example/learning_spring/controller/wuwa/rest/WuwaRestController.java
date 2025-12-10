package org.example.learning_spring.controller.wuwa.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.learning_spring.dto.WuwaCharLoreDTO;
import org.example.learning_spring.entity.Wuwa.WuwaChar;
import org.example.learning_spring.entity.Wuwa.WuwaCharLore;
import org.example.learning_spring.service.WuwaCharsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@RestController
public class WuwaRestController {
    private final WuwaCharsService wuwaCharsService;
    @Autowired
    WuwaRestController(WuwaCharsService wuwaCharsService){
        this.wuwaCharsService = wuwaCharsService;
    }

    @GetMapping("/addAllWuwaChars")
    @ResponseBody
    public void addAll() throws IOException {
        wuwaCharsService.injectWuwaChars();
    }

    @PostMapping("addAllWuwaCharLore")
    @ResponseBody
    public void addAllWuwaCharLore(@RequestBody List<WuwaCharLoreDTO> wuwaCharLoreDTOs){
        wuwaCharsService.updateAllWuwaCharLore(wuwaCharLoreDTOs);
    }

    @PostMapping("/addWuwaChar")
    public void addChar(@RequestBody WuwaChar wuwaChar) throws JsonProcessingException {
        wuwaCharsService.addWuwaChar(wuwaChar);
    }

    @GetMapping("change-paths")
    @ResponseBody
    public String changePaths(){
        wuwaCharsService.updateWuwaFullLorePaths("src/main/resources/GachaGamesLore/WuwaCharacterLore/");
        wuwaCharsService.getWuwaCharLoreDirectoryPaths();
        return "done";
    }

    @GetMapping("/imgPath")
    @ResponseBody
    public String setImgPath(){
        wuwaCharsService.updateWuwaCharsImgPath("/GachaGameImages/Wuwa/Wuwa_char_img/");
        return "done";
    }
    //TEST
    @GetMapping("lore-testing")
    @ResponseBody
    public String loreTesting() throws IOException {
        WuwaCharLore lore = wuwaCharsService.getWuwaCharLore("Aalto");
        try(BufferedReader br = new BufferedReader(new FileReader(lore.getFullLorePath()))) {
            String line;
            while ((line = br.readLine())!= null){
                System.out.println(line);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return "done";
    }
}
