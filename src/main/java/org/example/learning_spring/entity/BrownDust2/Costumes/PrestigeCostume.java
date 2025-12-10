package org.example.learning_spring.entity.BrownDust2.Costumes;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class PrestigeCostume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;
    private String costumeName;
    private String costumeImgPath;
    private String costumeIdleGifPath;
    private String costumeMotionGifPath;
    private String costumeAnimationPath;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "costume_id", referencedColumnName = "id")
    private CharCostume charCostume;

    public PrestigeCostume() {};

    public PrestigeCostume(int id, String costumeName, String costumeImgPath, String costumeIdleGifPath, String costumeMotionGifPath, String costumeAnimationPath, CharCostume charCostume) {
        this.id = id;
        this.costumeName = costumeName;
        this.costumeImgPath = costumeImgPath;
        this.costumeIdleGifPath = costumeIdleGifPath;
        this.costumeMotionGifPath = costumeMotionGifPath;
        this.costumeAnimationPath = costumeAnimationPath;
        this.charCostume = charCostume;
    }

    public PrestigeCostume(int id, String costumeName, String costumeImgPath, String costumeIdleGifPath, String costumeMotionGifPath) {
        this.id = id;
        this.costumeName = costumeName;
        this.costumeImgPath = costumeImgPath;
        this.costumeIdleGifPath = costumeIdleGifPath;
        this.costumeMotionGifPath = costumeMotionGifPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCostumeName() {
        return costumeName;
    }

    public void setCostumeName(String costumeName) {
        this.costumeName = costumeName;
    }

    public String getCostumeImgPath() {
        return costumeImgPath;
    }

    public void setCostumeImgPath(String costumeImgPath) {
        this.costumeImgPath = costumeImgPath;
    }

    public String getCostumeIdleGifPath() {
        return costumeIdleGifPath;
    }

    public void setCostumeIdleGifPath(String costumeIdleGifPath) {
        this.costumeIdleGifPath = costumeIdleGifPath;
    }

    public String getCostumeMotionGifPath() {
        return costumeMotionGifPath;
    }

    public void setCostumeMotionGifPath(String costumeMotionGifPath) {
        this.costumeMotionGifPath = costumeMotionGifPath;
    }

    public String getCostumeAnimationPath() {
        return costumeAnimationPath;
    }

    public void setCostumeAnimationPath(String costumeAnimationPath) {
        this.costumeAnimationPath = costumeAnimationPath;
    }

    public CharCostume getCharCostume() {
        return charCostume;
    }

    public void setCharCostume(CharCostume charCostume) {
        this.charCostume = charCostume;
    }
}
