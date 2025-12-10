package org.example.learning_spring.controller.browndust2;

import org.example.learning_spring.service.BrownDust2CharService;
import org.example.learning_spring.entity.BrownDust2.BrownDust2Char;
import org.example.learning_spring.entity.BrownDust2.Costumes.CharCostume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BrownDust2HomeController {
    BrownDust2CharService brownDust2CharService;
    @Autowired
    public BrownDust2HomeController(BrownDust2CharService brownDust2CharService) {
        this.brownDust2CharService = brownDust2CharService;
    }

    @GetMapping("brownDust2")
    public String brownDust2(Model model) {
        List<BrownDust2Char> brownDust2Chars = brownDust2CharService.getAllBrownDust2Chars();
        model.addAttribute("brownDust2Chars", brownDust2Chars);
        BrownDust2Char lathel = brownDust2CharService.getBrownDust2CharByName("Lathel");
        List<CharCostume> costumes = lathel.getCharCostumes();
        for(CharCostume costume : costumes){
            System.out.println(costume.getCostumeName());
        }
        System.out.println("done");
        return "brownDust2Index";
    }
}
