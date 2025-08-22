package org.example.learning_spring.TableClasses.BrownDust2.Costumes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.example.learning_spring.TableClasses.BrownDust2.BrownDust2Char;

import java.util.HashMap;
import java.util.List;

@Entity
public class CharCostume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;
    private String costumeName;
    private int coolDown;
    private int chain;
    private String pushDirection;
    private String tileRangeImgPath;
    private String attackPosition;

    private String costumeChibiImg;
    private String costumeImgPath;
    private String costumeGifPath;
    private String costumeAnimationPath;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "charCostume")
//    private List<CostumeRefinements> refinements;

    @OneToOne(mappedBy = "charCostume")
    private CostumeBond costumeBond;

    @OneToOne(mappedBy = "charCostume")
    private PrestigeCostume prestigeCostume;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BrownDust2Char_id", referencedColumnName = "id")
    private BrownDust2Char brownDust2Char;

    public CharCostume(){};

    public CharCostume(int id, String costumeName, int coolDown, int chain, String pushDirection, String tileRangeImgPath, String attackPosition, String costumeChibiImg,
                       String costumeImgPath, String costumeGifPath, String costumeAnimationPath, CostumeBond costumeBond, PrestigeCostume prestigeCostume, BrownDust2Char brownDust2Char) {
        this.id = id;
        this.costumeName = costumeName;
        this.coolDown = coolDown;
        this.chain = chain;
        this.pushDirection = pushDirection;
        this.tileRangeImgPath = tileRangeImgPath;
        this.costumeChibiImg = costumeChibiImg;
        this.costumeImgPath = costumeImgPath;
        this.costumeGifPath = costumeGifPath;
        this.costumeAnimationPath = costumeAnimationPath;
        this.costumeBond = costumeBond;
        this.prestigeCostume = prestigeCostume;
        this.brownDust2Char = brownDust2Char;
        this.attackPosition = attackPosition;
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

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public int getChain() {
        return chain;
    }

    public void setChain(int chain) {
        this.chain = chain;
    }

    public String getPushDirection() {
        return pushDirection;
    }

    public void setPushDirection(String pushDirection) {
        this.pushDirection = pushDirection;
    }

    public String getTileRangeImgPath() {
        return tileRangeImgPath;
    }

    public void setTileRangeImgPath(String tileRangeImgPath) {
        this.tileRangeImgPath = tileRangeImgPath;
    }

    public String getAttackPosition() {
        return attackPosition;
    }

    public void setAttackPosition(String attackPosition) {
        this.attackPosition = attackPosition;
    }

    public String getCostumeChibiImg() {
        return costumeChibiImg;
    }

    public void setCostumeChibiImg(String costumeChibiImg) {
        this.costumeChibiImg = costumeChibiImg;
    }

    public String getCostumeImgPath() {
        return costumeImgPath;
    }

    public void setCostumeImgPath(String costumeImgPath) {
        this.costumeImgPath = costumeImgPath;
    }

    public String getCostumeGifPath() {
        return costumeGifPath;
    }

    public void setCostumeGifPath(String costumeGifPath) {
        this.costumeGifPath = costumeGifPath;
    }

    public String getCostumeAnimationPath() {
        return costumeAnimationPath;
    }

    public void setCostumeAnimationPath(String costumeAnimationPath) {
        this.costumeAnimationPath = costumeAnimationPath;
    }

    public CostumeBond getCostumeBond() {
        return costumeBond;
    }

    public void setCostumeBond(CostumeBond costumeBond) {
        this.costumeBond = costumeBond;
    }

    public PrestigeCostume getPrestigeCostume() {
        return prestigeCostume;
    }

    public void setPrestigeCostume(PrestigeCostume prestigeCostume) {
        this.prestigeCostume = prestigeCostume;
    }

    public BrownDust2Char getBrownDust2Char() {
        return brownDust2Char;
    }

    public void setBrownDust2Char(BrownDust2Char brownDust2Char) {
        this.brownDust2Char = brownDust2Char;
    }
}
