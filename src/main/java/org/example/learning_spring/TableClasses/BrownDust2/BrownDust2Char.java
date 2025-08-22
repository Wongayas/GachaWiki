package org.example.learning_spring.TableClasses.BrownDust2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.example.learning_spring.TableClasses.BrownDust2.Costumes.CharCostume;

import java.util.Set;

@Entity
public class BrownDust2Char {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;
    @Column(unique = true, nullable = false)
    private String name;
    private int atk;
    private int hp;
    private int def;
    private int mRes;
    private int critRate;
    private int critDmg;
    private String element;
    private String damageType;
    private String rarity;

    @OneToMany(mappedBy = "brownDust2Char")
    private Set<CharCostume> charCostumes;

    public BrownDust2Char() {
    }

    public BrownDust2Char(String name, int atk, int def, int mRes, int hp, String element, int critRate, int critDmg, String damageType, String rarity, Set<CharCostume> charCostumes) {
        this.name = name;
        this.atk = atk;
        this.def = def;
        this.mRes = mRes;
        this.hp = hp;
        this.element = element;
        this.critRate = critRate;
        this.critDmg = critDmg;
        this.damageType = damageType;
        this.rarity = rarity;
        this.charCostumes = charCostumes;
    }
    public BrownDust2Char(String name, int atk, int def, int mRes, int hp, int critRate, int critDmg, String element,String damageType, String rarity) {
        this.name = name;
        this.atk = atk;
        this.def = def;
        this.mRes = mRes;
        this.hp = hp;
        this.element = element;
        this.critRate = critRate;
        this.critDmg = critDmg;
        this.damageType = damageType;
        this.rarity = rarity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getmRes() {
        return mRes;
    }

    public void setmRes(int mRes) {
        this.mRes = mRes;
    }

    public int getCritRate() {
        return critRate;
    }

    public void setCritRate(int critRate) {
        this.critRate = critRate;
    }

    public int getCritDmg() {
        return critDmg;
    }

    public void setCritDmg(int critDmg) {
        this.critDmg = critDmg;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public Set<CharCostume> getCharCostumes() {
        return charCostumes;
    }

    public void setCharCostumes(Set<CharCostume> charCostumes) {
        this.charCostumes = charCostumes;
    }
}
