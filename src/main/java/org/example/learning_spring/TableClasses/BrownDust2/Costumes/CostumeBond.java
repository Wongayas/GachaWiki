package org.example.learning_spring.TableClasses.BrownDust2.Costumes;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;

@Entity
public class CostumeBond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "bond_stats",
        joinColumns = {@JoinColumn(name = "bond_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "stat_name")
    @Column(name = "stat_value")
    private HashMap<String,Integer> bondStats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "costumeId", referencedColumnName = "id")
    private CharCostume charCostume;

    public CostumeBond() {};

    public CostumeBond(CharCostume charCostume, HashMap<String,Integer> bondStats) {
        this.charCostume = charCostume;
        this.bondStats = bondStats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String, Integer> getBondStats() {
        return bondStats;
    }

    public void setBondStats(HashMap<String, Integer> bondStats) {
        this.bondStats = bondStats;
    }

    public CharCostume getCharCostume() {
        return charCostume;
    }

    public void setCharCostume(CharCostume charCostume) {
        this.charCostume = charCostume;
    }
}
