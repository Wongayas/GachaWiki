package org.example.learning_spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.learning_spring.entity.Wuwa.WuwaCharLore;

public class WuwaCharLoreDTO {
    @JsonProperty("wuwaCharLore")
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
