package org.example.learning_spring.dto;

import org.example.learning_spring.entity.BrownDust2.Costumes.PrestigeCostume;

public class BrownDust2PrestigeCostumeDTO {
    String charName;
    String costumeName;
    PrestigeCostume prestigeCostume;

    public BrownDust2PrestigeCostumeDTO() {}

    public BrownDust2PrestigeCostumeDTO(String charName, String costumeName, PrestigeCostume prestigeCostume) {
        this.charName = charName;
        this.costumeName = costumeName;
        this.prestigeCostume = prestigeCostume;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getCostumeName() {
        return costumeName;
    }

    public void setCostumeName(String costumeName) {
        this.costumeName = costumeName;
    }

    public PrestigeCostume getPrestigeCostume() {
        return prestigeCostume;
    }

    public void setPrestigeCostume(PrestigeCostume prestigeCostume) {
        this.prestigeCostume = prestigeCostume;
    }
}
