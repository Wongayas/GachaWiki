package org.example.learning_spring.controller.wuwa;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.learning_spring.service.WuwaCharsService;
import org.example.learning_spring.entity.Wuwa.WuwaChar;
import org.example.learning_spring.dto.WuwaCharDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/wuwa")
public class WuwaHomeController {

    final private WuwaCharsService wuwaCharsService;
    @Autowired
    public WuwaHomeController(WuwaCharsService wuwaCharsService) {
        this.wuwaCharsService = wuwaCharsService;
    }

    @GetMapping
    public String charsByFilter(@RequestParam(required = false) String element,@RequestParam(required = false) String weaponType, Model model) {
        List<WuwaChar> wuwaChars = (wuwaCharsService.getByElementAndWeapon(element,weaponType)).stream().sorted(Comparator.comparing(WuwaChar::getName,String.CASE_INSENSITIVE_ORDER)).collect(Collectors.toList());
        model.addAttribute("wuwaChars", wuwaChars);
        model.addAttribute("element", element);
        model.addAttribute("weaponType", weaponType);
        return "wuwaIndex";
    }

    @GetMapping("/characters/{name}")
    public String showCharacter(@PathVariable String name, Model model) {
        WuwaChar wuwaChar = wuwaCharsService.getByName(name).get(0);
        String loreSummary = wuwaCharsService.getWuwaCharLoreSummary(wuwaChar);
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
        return "wuwaIndex";
    }

    @GetMapping("/newChar")
    public String charForm(Model model) {
        model.addAttribute("wuwaChar", new WuwaCharDto());
        return "addWuwaChar";
    }

    //TODO:Test this out
    @PostMapping("/newChar")
    public String addWuwaChar(@ModelAttribute WuwaCharDto wuwaCharDto) throws JsonProcessingException {
        WuwaChar wuwaChar = wuwaCharDto.getWuwaChar();
        wuwaChar.setWuwaCharLore(wuwaCharDto.getWuwaCharLore());
        wuwaCharsService.addWuwaChar(wuwaChar);
        return "redirect:/wuwa";
    }
}
