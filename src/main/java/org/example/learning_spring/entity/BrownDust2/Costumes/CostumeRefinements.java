package org.example.learning_spring.entity.BrownDust2.Costumes;

import jakarta.persistence.*;

@Entity
public class CostumeRefinements {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int refinementNumber;
    private int SP;
    private String costumeDescription;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "charCostume_id", referencedColumnName = "id")
    private CharCostume charCostume;

    public CostumeRefinements() {};
    public CostumeRefinements(int refinementNumber, int SP, String costumeDescription,  CharCostume charCostume) {
        this.refinementNumber = refinementNumber;
        this.SP = SP;
        this.costumeDescription = costumeDescription;
        this.charCostume = charCostume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRefinementNumber() {
        return refinementNumber;
    }

    public void setRefinementNumber(int refinementNumber) {
        this.refinementNumber = refinementNumber;
    }

    public int getSP() {
        return SP;
    }

    public void setSP(int SP) {
        this.SP = SP;
    }

    public String getCostumeDescription() {
        return costumeDescription;
    }

    public void setCostumeDescription(String costumeDescription) {
        this.costumeDescription = costumeDescription;
    }

    public CharCostume getCharCostume() {
        return charCostume;
    }

    public void setCharCostume(CharCostume charCostume) {
        this.charCostume = charCostume;
    }
}
