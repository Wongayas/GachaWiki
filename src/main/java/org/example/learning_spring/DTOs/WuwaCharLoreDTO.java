package org.example.learning_spring.DTOs;

import org.example.learning_spring.TableClasses.Wuwa.WuwaCharLore;

public class WuwaCharLoreDTO {
    private WuwaCharLore wuwaCharLoreObject;
    private String name;

    public WuwaCharLoreDTO(){};

    public WuwaCharLoreDTO(WuwaCharLore wuwaCharLoreObject, String name) {
        this.wuwaCharLoreObject = wuwaCharLoreObject;
        this.name = name;
    }

    public WuwaCharLore getWuwaCharLoreObject() {
        return wuwaCharLoreObject;
    }

    public void setWuwaCharLoreObject(WuwaCharLore wuwaCharLoreObject) {
        this.wuwaCharLoreObject = wuwaCharLoreObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
