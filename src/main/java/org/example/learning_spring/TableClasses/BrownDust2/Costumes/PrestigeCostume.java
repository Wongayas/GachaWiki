package org.example.learning_spring.TableClasses.BrownDust2.Costumes;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class PrestigeCostume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;
    private String costumeName;
    private String CostumeImgPath;
    private String costumeGifPath;
    private String CostumeAnimationPath;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "costume_id", referencedColumnName = "id")
    private CharCostume charCostume;

    public PrestigeCostume() {};

    public PrestigeCostume(String costumeName, String costumeImgPath, String costumeGifPath, String  costumeAnimationPath, CharCostume charCostume) {
        this.costumeName = costumeName;
        this.CostumeImgPath = costumeImgPath;
        this.costumeGifPath = costumeGifPath;
        this.CostumeAnimationPath = costumeAnimationPath;
        this.charCostume = charCostume;
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
        return CostumeImgPath;
    }

    public void setCostumeImgPath(String costumeImgPath) {
        CostumeImgPath = costumeImgPath;
    }

    public String getCostumeGifPath() {
        return costumeGifPath;
    }

    public void setCostumeGifPath(String costumeGifPath) {
        this.costumeGifPath = costumeGifPath;
    }

    public String getCostumeAnimationPath() {
        return CostumeAnimationPath;
    }

    public void setCostumeAnimationPath(String costumeAnimationPath) {
        CostumeAnimationPath = costumeAnimationPath;
    }

    public CharCostume getCharCostume() {
        return charCostume;
    }

    public void setCharCostume(CharCostume charCostume) {
        this.charCostume = charCostume;
    }
}
