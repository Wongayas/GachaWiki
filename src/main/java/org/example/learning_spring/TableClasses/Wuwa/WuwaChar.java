package org.example.learning_spring.TableClasses.Wuwa;

import jakarta.persistence.*;

@Entity
public class WuwaChar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(unique = true, nullable = false)
    String name;
    int hp;
    int atk;
    int def;
    int maxEnergy;
    String element;
    String weaponType;
    String img_path;
    String rarity;

    @OneToOne(mappedBy = "wuwaChar")
    private WuwaCharLore wuwaCharLore;


    public WuwaChar() {}

    public WuwaChar(String name, int hp, int atk, int def, int maxEnergy, String element, String weaponType,  String img_path, String rarity) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.maxEnergy = maxEnergy;
        this.element = element;
        this.weaponType = weaponType;
        this.img_path = img_path;
        this.rarity = rarity;
    }

    public WuwaChar(int id, String name, int hp, int atk, int def, int maxEnergy, String element, String weaponType, String img_path,String rarity) {
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.maxEnergy = maxEnergy;
        this.element = element;
        this.weaponType = weaponType;
        this.img_path = img_path;
        this.rarity = rarity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    public WuwaCharLore getWuwaCharLore() {
        return wuwaCharLore;
    }

    public void setWuwaCharLore(WuwaCharLore wuwaCharLore) {
        this.wuwaCharLore = wuwaCharLore;
    }
}
