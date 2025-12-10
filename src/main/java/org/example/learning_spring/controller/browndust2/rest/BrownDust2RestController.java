package org.example.learning_spring.controller.browndust2.rest;

import org.example.learning_spring.dto.BrownDust2PrestigeCostumeDTO;
import org.example.learning_spring.service.BrownDust2CharService;
import org.example.learning_spring.entity.BrownDust2.BrownDust2Char;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrownDust2RestController {
    BrownDust2CharService brownDust2CharService;
    @Autowired
    BrownDust2RestController(BrownDust2CharService brownDust2CharService){
        this.brownDust2CharService = brownDust2CharService;
    }

    @PostMapping("/addBrownDustChars")
    public String addBrownDustChars(@RequestBody List<BrownDust2Char> brownDust2Chars){
        brownDust2CharService.saveAllBrownDust2Chars(brownDust2Chars);
        return "Successfully added all characters";
    }

    @PostMapping("/addPrestige")
    public String addPrestigeSkin(@RequestBody BrownDust2PrestigeCostumeDTO brownDust2PrestigeCostumeDTO){
        brownDust2CharService.addPrestigeCostume(brownDust2PrestigeCostumeDTO);
        return "Successfully added prestige skin";
    }

    @GetMapping("updatePath")
    public String updatePath(){
        brownDust2CharService.updatePaths();
        return "Successfully updated path";
    }
}
