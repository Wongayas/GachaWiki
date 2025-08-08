package org.example.learning_spring;

import jakarta.servlet.http.HttpSession;
import org.example.learning_spring.DTOs.WuwaCharLoreDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


@Controller
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    final private WuwaCharsService wuwaCharsService;

    @Autowired
    public HomeController(WuwaCharsService wuwaCharsService) {
        this.wuwaCharsService = wuwaCharsService;
    }

    @GetMapping("/addAll")
    @ResponseBody
    public void addAll() throws IOException {
        wuwaCharsService.injectWuwaChars();
    }

    @GetMapping("/characters")
    public String charsByFilter(@RequestParam(required = false) String element,@RequestParam(required = false) String weaponType, Model model) {
        List<WuwaChar> wuwaChars = wuwaCharsService.getByElementAndWeapon(element,weaponType);
        model.addAttribute("wuwaChars", wuwaChars);
        model.addAttribute("element", element);
        model.addAttribute("weaponType", weaponType);
        return "index";
    }

    @GetMapping("characters/{name}")
    public String showCharacter(@PathVariable String name, Model model) {
        List<WuwaChar> wuwaChars = wuwaCharsService.getByName(name);
        WuwaChar wuwaChar = wuwaChars.get(0);
        model.addAttribute("wuwaChar", wuwaChar);
        return "character";
    }

    @GetMapping("/{name}")
    public String getWuwaChar(@PathVariable String name, Model model) {
        StringBuilder capitalName = new StringBuilder(name);
        capitalName.setCharAt(0, Character.toUpperCase(capitalName.charAt(0)));
        List<WuwaChar> wuwaChar;
        wuwaChar = wuwaCharsService.getByName(capitalName.toString());
        System.out.println(wuwaChar.get(0).getName());
        model.addAttribute("wuwaChars", wuwaChar);
        return "index";
    }

    @PostMapping("/addChar")
    public void addChar(@RequestBody WuwaChar wuwaChar){
        wuwaCharsService.addWuwaChar(wuwaChar);
    }

    @PostMapping("addLore")
    @ResponseBody
    public void addLore(@RequestBody WuwaCharLoreDTO wuwaCharLoreDTO){
        WuwaCharLore wuwaCharLore = wuwaCharLoreDTO.getWuwaCharLore();
        String name = wuwaCharLoreDTO.getName();
        System.out.println(wuwaCharLore.getLoreSummary()+" "+ wuwaCharLore.getFullLorePath()+ " " + name);
        wuwaCharsService.updateWuwaCharLore(wuwaCharLore,name);
    }
}
