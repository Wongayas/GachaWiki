package org.example.learning_spring.DTOs;

import org.example.learning_spring.WuwaCharLore;

public class WuwaCharLoreDTO {
    private WuwaCharLore wuwaCharLore;
    private String name;
    WuwaCharLoreDTO(){};
    WuwaCharLoreDTO(WuwaCharLore wuwaCharLore, String name) {
        this.wuwaCharLore = wuwaCharLore;
        this.name = name;
    }

    public WuwaCharLore getWuwaCharLore() {
        return wuwaCharLore;
    }

    public void setWuwaCharLore(WuwaCharLore wuwaCharLore) {
        this.wuwaCharLore = wuwaCharLore;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
