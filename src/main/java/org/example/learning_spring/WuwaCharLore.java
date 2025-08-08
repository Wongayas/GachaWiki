package org.example.learning_spring;

import jakarta.persistence.*;

@Entity
public class WuwaCharLore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "lore_summary")
    private String loreSummary;
    @Column(name = "full_lore_path")
    private String fullLorePath;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wuwaChar_id", referencedColumnName = "id")
    private WuwaChar wuwaChar;


    public WuwaCharLore(){};
    public WuwaCharLore(String loreSummary, String fullLorePath) {
        this.loreSummary = loreSummary;
        this.fullLorePath = fullLorePath;
    };
    public WuwaCharLore(int id,String loreSummary, String fullLorePath) {
        this.id = id;
        this.loreSummary = loreSummary;
        this.fullLorePath = fullLorePath;
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullLorePath() {
        return fullLorePath;
    }

    public void setFullLorePath(String fullLorePath) {
        this.fullLorePath = fullLorePath;
    }

    public String getLoreSummary() {
        return loreSummary;
    }

    public void setLoreSummary(String loreSummary) {
        this.loreSummary = loreSummary;
    }

    public WuwaChar getWuwaChar() {
        return wuwaChar;
    }

    public void setWuwaChar(WuwaChar wuwaChar) {
        this.wuwaChar = wuwaChar;
    }
}
