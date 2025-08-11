package org.example.learning_spring;

import org.example.learning_spring.DTOs.WuwaCharLoreDTO;
import org.example.learning_spring.TableClasses.Wuwa.WuwaChar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@Controller
public class HomeController {

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
        String loreSummary = wuwaCharsService.getLoreSummary(wuwaChar);
        model.addAttribute("wuwaChar", wuwaChar);
        model.addAttribute("loreSummary", loreSummary);
        return "character";
    }

    @GetMapping("/{name}")
    public String getWuwaChar(@PathVariable String name, Model model) {
        StringBuilder capitalName = new StringBuilder(name);
        capitalName.setCharAt(0, Character.toUpperCase(capitalName.charAt(0)));
        List<WuwaChar> wuwaChar;
        wuwaChar = wuwaCharsService.getByName(capitalName.toString());
        model.addAttribute("wuwaChars", wuwaChar);
        return "index";
    }

    @PostMapping("/addChar")
    public void addChar(@RequestBody WuwaChar wuwaChar){
        wuwaCharsService.addWuwaChar(wuwaChar);
    }

    @PostMapping("addLore")
    @ResponseBody
    public void addLore(@RequestBody List<WuwaCharLoreDTO> wuwaCharLoreDTOs){
        wuwaCharsService.updateAllWuwaCharLore(wuwaCharLoreDTOs);
    }

    @GetMapping("change-paths")
    @ResponseBody
    public String seePaths(){
        wuwaCharsService.updatePaths("wuwachar", "/Wuwa", 0);
        wuwaCharsService.getDirectoryPaths();
        return "done";
    }
}
