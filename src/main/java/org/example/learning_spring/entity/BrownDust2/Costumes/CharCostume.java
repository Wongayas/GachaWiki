package org.example.learning_spring.entity.BrownDust2.Costumes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.example.learning_spring.entity.BrownDust2.BrownDust2Char;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
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
    private String costumeIdleGifPath;
    private String costumeMotionGifPath;
    private String costumeAnimationPath;

//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "charCostume")
//    private List<CostumeRefinements> refinements;

    @OneToOne(mappedBy = "charCostume", cascade = CascadeType.ALL)
    @JsonManagedReference
    private CostumeBond costumeBond;

    @OneToOne(mappedBy = "charCostume", cascade =  CascadeType.ALL)
    @JsonIgnore
    private PrestigeCostume prestigeCostume;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "BrownDust2Char_id", referencedColumnName = "id")
    @JsonIgnore
    private BrownDust2Char brownDust2Char;

    public CharCostume(){};

    public CharCostume(String costumeName, int coolDown, int chain, String pushDirection, String tileRangeImgPath, String attackPosition, String costumeChibiImg,
                       String costumeImgPath, String costumeIdleGifPath, String costumeMotionGifPath, String costumeAnimationPath, CostumeBond costumeBond, PrestigeCostume prestigeCostume, BrownDust2Char brownDust2Char) {
        this.costumeName = costumeName;
        this.coolDown = coolDown;
        this.chain = chain;
        this.pushDirection = pushDirection;
        this.tileRangeImgPath = tileRangeImgPath;
        this.costumeChibiImg = costumeChibiImg;
        this.costumeImgPath = costumeImgPath;
        this.costumeIdleGifPath = costumeIdleGifPath;
        this.costumeAnimationPath = costumeAnimationPath;
        this.costumeBond = costumeBond;
        this.prestigeCostume = prestigeCostume;
        this.brownDust2Char = brownDust2Char;
        this.attackPosition = attackPosition;
        this.costumeMotionGifPath = costumeMotionGifPath;
    }

    public CharCostume(String costumeName, int coolDown, int chain, String pushDirection, String tileRangeImgPath, String attackPosition, String costumeChibiImg, String costumeImgPath, String costumeIdleGifPath,String costumeMotionGifPath, BrownDust2Char brownDust2Char) {
        this.costumeName = costumeName;
        this.coolDown = coolDown;
        this.chain = chain;
        this.pushDirection = pushDirection;
        this.tileRangeImgPath = tileRangeImgPath;
        this.attackPosition = attackPosition;
        this.costumeChibiImg = costumeChibiImg;
        this.costumeImgPath = costumeImgPath;
        this.costumeIdleGifPath = costumeIdleGifPath;
        this.brownDust2Char = brownDust2Char;
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
