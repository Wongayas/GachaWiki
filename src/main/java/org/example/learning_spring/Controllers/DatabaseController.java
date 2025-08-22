package org.example.learning_spring.Controllers;

import org.example.learning_spring.DTOs.WuwaCharLoreDTO;
import org.example.learning_spring.TableClasses.Wuwa.WuwaChar;
import org.example.learning_spring.TableClasses.Wuwa.WuwaCharLore;
import org.example.learning_spring.Services.WuwaCharsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@RestController
public class DatabaseController {
    private final WuwaCharsService wuwaCharsService;
    @Autowired
    DatabaseController(WuwaCharsService wuwaCharsService){
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
    public void addChar(@RequestBody WuwaChar wuwaChar){
        wuwaCharsService.addWuwaChar(wuwaChar);
    }

    @GetMapping("change-paths")
    @ResponseBody
    public String changePaths(){
        wuwaCharsService.updateWuwaFullLorePaths("src/main/resources/GachaGamesLore/WuwaCharacterLore/");
        wuwaCharsService.getWuwaCharLoreDirectoryPaths();
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
