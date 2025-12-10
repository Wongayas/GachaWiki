package org.example.learning_spring.entity.BrownDust2.Costumes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Map;

@Entity
public class CostumeBond {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "bond_stats",
        joinColumns = {@JoinColumn(name = "bond_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "stat_name")
    @Column(name = "stat_value")
    private Map<String,Double> bondStats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "costumeId", referencedColumnName = "id")
    @JsonBackReference
    private CharCostume charCostume;

    public CostumeBond() {};

    public CostumeBond(CharCostume charCostume, Map<String,Double> bondStats) {
        this.charCostume = charCostume;
        this.bondStats = bondStats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, Double> getBondStats() {
        return bondStats;
    }

    public void setBondStats(Map<String, Double> bondStats) {
        this.bondStats = bondStats;
    }

    public CharCostume getCharCostume() {
        return charCostume;
    }

    public void setCharCostume(CharCostume charCostume) {
        this.charCostume = charCostume;
    }
}
