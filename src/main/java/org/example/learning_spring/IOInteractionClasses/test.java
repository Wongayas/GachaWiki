package org.example.learning_spring.IOInteractionClasses;

public class test {
    public static void main(String[] args) {
        String path = "src/main/resources/CharacterLore/Carlotta.txt";
//        src/main/resources/GachaGames/Wuwa/Wuwa_char_img/changli-498d3608.png
        System.out.println(path.split("/")[4]);
    }
}
