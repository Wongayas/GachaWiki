package org.example.learning_spring.dto;

import org.example.learning_spring.entity.Wuwa.WuwaChar;
import org.example.learning_spring.entity.Wuwa.WuwaCharLore;

public class WuwaCharDto {
    private WuwaChar wuwaChar;
    private WuwaCharLore wuwaCharLore;

    public WuwaCharDto() {}

    public WuwaCharDto(WuwaChar wuwaChar, WuwaCharLore wuwaCharLore) {
        this.wuwaChar = wuwaChar;
        this.wuwaCharLore = wuwaCharLore;
    }

    public WuwaChar getWuwaChar() {
        return  wuwaChar;
    }
    public void setWuwaChar(WuwaChar wuwaChar) {
        this.wuwaChar = wuwaChar;
    }

    public WuwaCharLore getWuwaCharLore() {
        return wuwaCharLore;
    }

    public void setWuwaCharLore(WuwaCharLore wuwaCharLore) {
        this.wuwaCharLore = wuwaCharLore;
    }
}
